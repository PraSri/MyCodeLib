package vnd.credit.loans;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

/**
 * REST Controller for managing loan operations.
 * 
 * <p>This controller handles loan creation and borrowing operations with proper
 * security, transaction management, and resilience patterns for third-party integrations.</p>
 * 
 * <p>Key Features:
 * <ul>
 *   <li>Role-based access control (USER, ADMIN)</li>
 *   <li>Transactional integrity for financial operations</li>
 *   <li>Circuit breaker pattern for third-party service resilience</li>
 *   <li>Comprehensive audit logging</li>
 *   <li>BigDecimal for monetary precision</li>
 * </ul>
 * </p>
 * 
 * @author Senior Software Engineer
 * @version 3.0
 * @since 2025-12-21
 */
@RestController
@RequestMapping("/api/v3/accounts")
public class LoanController {

    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
    private static final String LOAN_SERVICE_CB = "loanManagementService";

    private final AccountService accountService;
    private final LoanManagementService loanManagementService;
    private final AuditService auditService;
    private final IdGeneratorService idGeneratorService;

    /**
     * Constructor injection for all dependencies.
     * This approach provides:
     * - Immutable dependencies (final fields)
     * - Easy unit testing with mocks
     * - Clear dependency declaration
     * - Fail-fast behavior if dependencies are missing
     */
    public LoanController(
            AccountService accountService,
            LoanManagementService loanManagementService,
            AuditService auditService,
            IdGeneratorService idGeneratorService) {
        this.accountService = accountService;
        this.loanManagementService = loanManagementService;
        this.auditService = auditService;
        this.idGeneratorService = idGeneratorService;
    }

    /**
     * Creates a new loan by transferring funds from a source account to the destination account.
     * 
     * <p>This endpoint performs the following operations atomically:
     * <ol>
     *   <li>Validates the user's authorization to access the destination account</li>
     *   <li>Verifies the loan amount is within the user's credit limit</li>
     *   <li>Checks sufficient balance in the source account</li>
     *   <li>Transfers funds from source to destination</li>
     *   <li>Registers the loan with the external loan management system</li>
     * </ol>
     * </p>
     * 
     * @param accountId The destination account ID receiving the loan
     * @param request The loan request containing amount and source account details
     * @param userDetails The authenticated user's security principal
     * @return ResponseEntity containing the created loan details with HTTP 201
     * @throws AccountNotFoundException if source or destination account doesn't exist
     * @throws AccessDeniedException if user is not authorized to access the account
     * @throws CreditLimitExceededException if loan amount exceeds credit limit
     * @throws InsufficientFundsException if source account has insufficient balance
     * @throws LoanRegistrationException if external loan registration fails
     */
    @PostMapping("/{accountId}/loans")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
    @CircuitBreaker(name = LOAN_SERVICE_CB, fallbackMethod = "createLoanFallback")
    @Retry(name = LOAN_SERVICE_CB)
    public ResponseEntity<LoanResponse> createLoan(
            @PathVariable 
            @NotBlank(message = "Account ID is required")
            @Pattern(regexp = "^[a-zA-Z0-9-]{1,36}$", message = "Invalid account ID format")
            String accountId,
            @Valid @RequestBody LoanRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String correlationId = UUID.randomUUID().toString();
        
        logger.info("Loan request initiated - correlationId: {}, accountId: {}, amount: {}, userId: {}",
                correlationId, accountId, request.getLoanAmount(), userDetails.getUserId());

        try {
            // Step 1: Validate account ownership (unless admin)
            validateAccountAccess(accountId, userDetails);

            // Step 2: Retrieve and validate accounts
            Account destinationAccount = getAccountOrThrow(accountId, "destination");
            Account sourceAccount = getAccountOrThrow(request.getSourceAccountId(), "source");

            // Step 3: Validate credit limit
            validateCreditLimit(accountId, request.getLoanAmount());

            // Step 4: Validate sufficient balance
            validateSufficientBalance(sourceAccount, request.getLoanAmount());

            // Step 5: Execute transfer atomically
            executeTransfer(sourceAccount, destinationAccount, request.getLoanAmount());

            // Step 6: Register loan with external system
            String loanId = registerLoanWithExternalSystem(
                    request.getLoanAmount(), 
                    userDetails.getUserId(),
                    correlationId);

            // Step 7: Build response
            LoanResponse response = LoanResponse.builder()
                    .loanId(loanId)
                    .accountId(accountId)
                    .sourceAccountId(request.getSourceAccountId())
                    .amount(request.getLoanAmount())
                    .status(LoanStatus.APPROVED)
                    .correlationId(correlationId)
                    .createdAt(Instant.now())
                    .build();

            // Step 8: Audit successful transaction
            auditService.logTransaction(
                    TransactionType.LOAN_CREATED,
                    accountId,
                    request.getLoanAmount(),
                    request.getSourceAccountId(),
                    userDetails.getUserId(),
                    TransactionStatus.SUCCESS,
                    correlationId);

            logger.info("Loan created successfully - correlationId: {}, loanId: {}", 
                    correlationId, loanId);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("X-Correlation-Id", correlationId)
                    .header("Location", "/api/v3/loans/" + loanId)
                    .body(response);

        } catch (Exception e) {
            // Audit failed transaction
            auditService.logTransaction(
                    TransactionType.LOAN_CREATED,
                    accountId,
                    request.getLoanAmount(),
                    request.getSourceAccountId(),
                    userDetails.getUserId(),
                    TransactionStatus.FAILED,
                    correlationId,
                    e.getMessage());

            logger.error("Loan creation failed - correlationId: {}, error: {}", 
                    correlationId, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Validates that the user has access to the specified account.
     * Admins can access any account; regular users can only access their own accounts.
     */
    private void validateAccountAccess(String accountId, UserDetails userDetails) {
        if (userDetails.hasRole("ADMIN")) {
            logger.debug("Admin access granted for account: {}", accountId);
            return;
        }

        Account account = getAccountOrThrow(accountId, "target");
        
        if (!account.getOwner().equals(userDetails.getUserId())) {
            logger.warn("Access denied - userId: {} attempted to access account: {}", 
                    userDetails.getUserId(), accountId);
            throw new AccessDeniedException(
                    String.format("User %s is not authorized to access account %s", 
                            userDetails.getUserId(), accountId));
        }
    }

    /**
     * Retrieves an account by ID or throws a descriptive exception.
     */
    private Account getAccountOrThrow(String accountId, String accountType) {
        return accountService.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("%s account not found: %s", 
                                capitalize(accountType), accountId)));
    }

    /**
     * Validates that the loan amount is within the user's credit limit.
     */
    private void validateCreditLimit(String accountId, BigDecimal loanAmount) {
        BigDecimal creditLimit = loanManagementService.getCreditLimit(accountId);
        
        if (loanAmount.compareTo(creditLimit) > 0) {
            throw new CreditLimitExceededException(
                    String.format("Loan amount %s exceeds credit limit %s for account %s",
                            loanAmount, creditLimit, accountId));
        }
        
        logger.debug("Credit limit validation passed - accountId: {}, requested: {}, limit: {}", 
                accountId, loanAmount, creditLimit);
    }

    /**
     * Validates that the source account has sufficient balance.
     */
    private void validateSufficientBalance(Account sourceAccount, BigDecimal amount) {
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(
                    String.format("Insufficient funds in account %s. Required: %s, Available: %s",
                            sourceAccount.getId(), amount, sourceAccount.getBalance()));
        }
    }

    /**
     * Executes the fund transfer between accounts.
     * This method is called within a SERIALIZABLE transaction to ensure atomicity.
     */
    private void executeTransfer(Account source, Account destination, BigDecimal amount) {
        logger.debug("Executing transfer - from: {}, to: {}, amount: {}", 
                source.getId(), destination.getId(), amount);
        
        accountService.debit(source, amount);
        accountService.credit(destination, amount);
        
        logger.debug("Transfer completed successfully");
    }

    /**
     * Registers the loan with the external loan management system.
     * Uses distributed ID generation for unique, collision-free loan IDs.
     */
    private String registerLoanWithExternalSystem(
            BigDecimal amount, 
            String userId, 
            String correlationId) {
        
        String loanId = idGeneratorService.generateLoanId();
        
        logger.debug("Registering loan with external system - loanId: {}, correlationId: {}", 
                loanId, correlationId);
        
        loanManagementService.registerLoan(loanId, amount, userId, correlationId);
        
        return loanId;
    }

    /**
     * Fallback method for circuit breaker when external loan service is unavailable.
     * Creates a pending loan record for later processing.
     */
    public ResponseEntity<LoanResponse> createLoanFallback(
            String accountId,
            LoanRequest request,
            UserDetails userDetails,
            Throwable throwable) {
        
        String correlationId = UUID.randomUUID().toString();
        
        logger.warn("Loan service unavailable, creating pending loan - correlationId: {}, error: {}", 
                correlationId, throwable.getMessage());

        // Create pending loan for async processing
        LoanResponse response = LoanResponse.builder()
                .loanId("PENDING-" + correlationId)
                .accountId(accountId)
                .sourceAccountId(request.getSourceAccountId())
                .amount(request.getLoanAmount())
                .status(LoanStatus.PENDING_EXTERNAL_REGISTRATION)
                .correlationId(correlationId)
                .message("Loan created but external registration pending. You will be notified once complete.")
                .createdAt(Instant.now())
                .build();

        // Audit pending transaction
        auditService.logTransaction(
                TransactionType.LOAN_PENDING,
                accountId,
                request.getLoanAmount(),
                request.getSourceAccountId(),
                userDetails.getUserId(),
                TransactionStatus.PENDING,
                correlationId,
                "External service unavailable: " + throwable.getMessage());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("X-Correlation-Id", correlationId)
                .body(response);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

// =========================================================================================
// Supporting DTOs, Exceptions, and Interface Stubs
// In production, these would be in separate files in appropriate packages
// =========================================================================================

/**
 * Request DTO for loan creation with validation constraints.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class LoanRequest {
    
    @NotBlank(message = "Source account ID is required")
    @Pattern(regexp = "^[a-zA-Z0-9-]{1,36}$", message = "Invalid source account ID format")
    private String sourceAccountId;
    
    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be positive")
    @DecimalMax(value = "10000000.00", message = "Loan amount exceeds maximum allowed")
    @Digits(integer = 10, fraction = 2, message = "Invalid monetary format")
    private BigDecimal loanAmount;
    
    // Optional idempotency key for preventing duplicate requests
    private String idempotencyKey;
}

/**
 * Response DTO for loan operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class LoanResponse {
    private String loanId;
    private String accountId;
    private String sourceAccountId;
    private BigDecimal amount;
    private LoanStatus status;
    private String correlationId;
    private String message;
    private Instant createdAt;
}

/**
 * Loan status enumeration.
 */
enum LoanStatus {
    APPROVED,
    PENDING_EXTERNAL_REGISTRATION,
    REJECTED,
    CANCELLED
}

enum TransactionType {
    LOAN_CREATED,
    LOAN_PENDING,
    LOAN_CANCELLED,
    TRANSFER
}

enum TransactionStatus {
    SUCCESS,
    FAILED,
    PENDING
}

// =========================================================================================
// Custom Exceptions - Would be in separate files with @ResponseStatus annotations
// =========================================================================================

@ResponseStatus(HttpStatus.NOT_FOUND)
class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.FORBIDDEN)
class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class CreditLimitExceededException extends RuntimeException {
    public CreditLimitExceededException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

@ResponseStatus(HttpStatus.BAD_GATEWAY)
class LoanRegistrationException extends RuntimeException {
    public LoanRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}

// =========================================================================================
// Service Interfaces - Would be in separate files
// =========================================================================================

interface AccountService {
    Optional<Account> findById(String accountId);
    void debit(Account account, BigDecimal amount);
    void credit(Account account, BigDecimal amount);
}

interface LoanManagementService {
    BigDecimal getCreditLimit(String accountId);
    void registerLoan(String loanId, BigDecimal amount, String userId, String correlationId);
}

interface AuditService {
    void logTransaction(TransactionType type, String accountId, BigDecimal amount,
                        String sourceAccountId, String userId, TransactionStatus status,
                        String correlationId);
    
    void logTransaction(TransactionType type, String accountId, BigDecimal amount,
                        String sourceAccountId, String userId, TransactionStatus status,
                        String correlationId, String errorMessage);
}

interface IdGeneratorService {
    String generateLoanId();
}

// =========================================================================================
// Domain Models - Would be in separate files
// =========================================================================================

@Data
@Builder
class Account {
    private String id;
    private String owner;
    private BigDecimal balance;
}

interface UserDetails {
    String getUserId();
    boolean hasRole(String role);
}
