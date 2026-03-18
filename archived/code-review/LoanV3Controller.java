/**
 * https://leetcode.com/discuss/post/6963982/interview-experience-tide-engineer-backe-scfa/
 * Code review interview context
 * <p>
 * 1	You need to review the REST API written in Java.
 * You need to perform the code review in 40 mins. For simplicity, the PR is shared in a Google doc.
 * 2	Consider that this PR is raised by a very junior developer.
 * Hence, assume that this code has lots of issues and improvement areas.
 * 3	Like any real PR, there are some minor and some major issues.
 * Major issues will have more weightage in the scoring of the code review round. Please prioritise
 * 4	You can do whatever you would normally do while performing code review like:
 * 1	Asking a question.
 * 2	Suggesting improvements / alternate ways of writing code.
 * 3	Share a link to specs, documentation, guideline, blog, video, etc.
 * 5	You can use the internet like you would do while performing a code review.
 * However, please be mindful of the 40 mins time limit.
 * We live in an age where most of the new code will soon be AI generated.
 * That's why we believe having engineers capable of performing great Code Reviews is even more
 * important than before. This is the reason we discourage the use of AI tools during this task
 * 6	You can assume that the code compiles and runs, all of the usings are there, etc.
 * 7	Pro tip: Try to add comments as you go instead of adding all the comments towards the end.
 */


package vnd.credit.loans;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Creating a new version of the borrowing functionality that registers the loan in a 3rd party Loan Management System (wrapped in feign client service). Since we get a lot of errors from the 3rd party I've added a functionality to let our staff move the money on behalf of the user if they give us a call that something's not working properly!!!
 */
/*
 * ============================================================================================
 * CODE REVIEW COMMENTS - SENIOR ENGINEER REVIEW
 * Reviewer: Senior Software Engineer (10+ years Java/Fintech experience)
 * Date: 2025-12-21
 * Severity Levels: [CRITICAL] [MAJOR] [MINOR] [SUGGESTION]
 * ============================================================================================
 */

/*
 * [CRITICAL] SECURITY - Missing import for security annotations.
 * This endpoint handles financial transactions and MUST be secured.
 * Add: @PreAuthorize or @Secured annotations at method/class level.
 * Missing imports:
 *   import org.springframework.security.access.prepost.PreAuthorize;
 *   import org.springframework.web.bind.annotation.*;
 *   import javax.validation.constraints.*;
 *   import javax.validation.Valid;
 */

/*
 * [MAJOR] API VERSIONING - Inconsistent versioning strategy.
 * The class is named "V3Controller" but the endpoint path contains "/v2/".
 * This creates confusion for API consumers and breaks RESTful conventions.
 * Recommendation: Align versioning - use either /v3/ in path OR rename class.
 */
@RequestMapping("/v3/accounts/")
@RestController
public class LoanV3Controller {

    /*
     * [MINOR] LOGGING - Logger should be private and final for immutability.
     * Convention: private static final Logger LOGGER = ...
     * Also consider using @Slf4j Lombok annotation to reduce boilerplate.
     */
    public static final Logger logger = LoggerFactory.getLogger(LoanV3Controller.class);

    /*
     * [MAJOR] DEPENDENCY INJECTION - Field injection is discouraged.
     * Problems with @Autowired field injection:
     * 1. Makes unit testing difficult (requires reflection or Spring context)
     * 2. Hides dependencies from the class signature
     * 3. Allows for null fields if Spring context is not initialized
     * 4. Fields should be private, not public!
     *
     * RECOMMENDATION: Use constructor injection:
     *
     * private final AccountService accountService;
     * private final LoanManagementService loanManagementService;
     *
     * public LoanV3Controller(AccountService accountService, LoanManagementService loanManagementService) {
     *     this.accountService = accountService;
     *     this.loanManagementService = loanManagementService;
     * }
     */
    @Autowired
    public AccountService accountService;
    @Autowired
    public LoanManagementService loanManagementService;

    /*
     * [CRITICAL] REST SEMANTICS - PUT is incorrect HTTP method for this operation.
     * PUT should be idempotent (same request = same result). Borrowing money is NOT idempotent.
     * Each call creates a NEW loan transaction.
     * RECOMMENDATION: Use POST for creating new resources/transactions.
     *
     * Also: The URL structure "/new/{accountId}/v2/loans/borrow" is confusing and non-RESTful.
     * Better: POST /v3/accounts/{accountId}/loans
     */

    /*
     * [CRITICAL] SECURITY - Missing input validation annotations.
     * Financial APIs MUST validate all inputs to prevent injection attacks and invalid data.
     * Add: @Valid, @NotNull, @NotBlank, @Positive, @Min, @Max annotations.
     *
     * Example:
     * public ResponseEntity<LoanResponse> borrowMoney(
     *     @PathVariable @NotBlank @Pattern(regexp = "^[a-zA-Z0-9-]+$") String accountId,
     *     @RequestParam @NotNull Boolean isAdminAgent,
     *     @RequestParam @Positive @Max(1000000) BigDecimal loanAmount,
     *     @RequestParam @NotBlank String sourceAccountId)
     */

    /*
     * [MAJOR] RETURN TYPE - void return type is poor API design.
     * Clients have no way to know:
     * 1. If the loan was successfully created
     * 2. The loan ID for future reference
     * 3. Transaction details for reconciliation
     *
     * RECOMMENDATION: Return ResponseEntity<LoanResponse> with proper HTTP status codes:
     * - 201 CREATED on success with Location header
     * - 400 BAD_REQUEST for validation errors
     * - 403 FORBIDDEN for authorization failures
     * - 409 CONFLICT for insufficient funds
     * - 502 BAD_GATEWAY for 3rd party service errors
     */

    /*
     * [CRITICAL] DATA TYPE - Using 'double' for monetary values is DANGEROUS!
     * double/float suffer from floating-point precision errors.
     * Example: 0.1 + 0.2 = 0.30000000000000004
     *
     * In fintech, this can lead to:
     * - Incorrect balances
     * - Audit discrepancies
     * - Regulatory compliance issues
     *
     * MUST USE: java.math.BigDecimal for ALL monetary calculations.
     * See: https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
     */
    @PutMapping(value = "/new/{accountId}/v2/loans/borrow")
    public void borrowMoney(@PathVariable String accountId,
                            @RequestParam boolean isAdminAgent,
                            @RequestParam double loanAmount,  // [CRITICAL] Use BigDecimal!
                            @RequestParam String sourceAccountId) {

        /*
         * [MAJOR] LOGGING - No entry logging for this critical financial operation.
         * For audit trails and debugging, log entry with sanitized request details.
         * Example: logger.info("Loan request initiated: accountId={}, amount={}, source={}",
         *                      accountId, loanAmount, sourceAccountId);
         */

        /*
         * [CRITICAL] SECURITY VULNERABILITY - Broken Access Control (OWASP Top 10 - A01:2021).
         * Passing isAdminAgent as a REQUEST PARAMETER is extremely dangerous!
         * ANY user can set isAdminAgent=true and bypass ownership checks.
         *
         * Admin status MUST be determined server-side from:
         * 1. JWT token claims
         * 2. Security context (SecurityContextHolder)
         * 3. Role-based access control (RBAC) service
         *
         * CRITICAL FIX:
         * boolean isAdmin = AuthContext.getCurrentUser().hasRole("ADMIN");
         * Or use @PreAuthorize("hasRole('ADMIN')") for the admin-only endpoint.
         */
        if (!isAdminAgent) {
            // for admins we don't need to check the ownership
            Account acc = accountService.getAccount(accountId);

            /*
             * [MAJOR] NULL SAFETY - acc could be null if account doesn't exist.
             * This will throw NullPointerException on getOwner() call.
             * Add null check or use Optional pattern consistently.
             *
             * Example:
             * Account acc = Optional.ofNullable(accountService.getAccount(accountId))
             *     .orElseThrow(() -> new AccountNotFoundException(accountId));
             */

            /*
             * [MAJOR] COMPARISON - Using != for object comparison may fail.
             * If getOwner() and getCurrentUserID() return wrapper types (Long, Integer),
             * != compares references, not values. Use .equals() instead.
             *
             * Also: Consider using Objects.equals() for null-safety:
             * if (!Objects.equals(acc.getOwner(), AuthContext.getCurrentUserID()))
             */
            if (acc.getOwner() != AuthContext.getCurrentUserID()) {

                /*
                 * [CRITICAL] ERROR HANDLING - Wrong exception type.
                 * Unauthorized access should throw:
                 * - 403 Forbidden (AccessDeniedException)
                 * - NOT 500 Internal Server Error!
                 *
                 * InternalServerError (500) should only be used for unexpected server failures.
                 * This masks authorization failures and breaks HTTP semantics.
                 *
                 * Also: No error message provided for debugging/logging.
                 * throw new ForbiddenException("User " + AuthContext.getCurrentUserID() +
                 *     " is not authorized to access account " + accountId);
                 */
                throw new InternalServerError();
            }
        }

        /*
         * [MAJOR] BUSINESS LOGIC - Credit limit check logic is INVERTED!
         * Currently: loanAmount < creditLimit (allows borrow if under limit)
         * This seems correct, BUT the condition name suggests otherwise.
         *
         * Clearer approach:
         * BigDecimal creditLimit = loanManagementService.getCreditLimit(accountId);
         * if (loanAmount.compareTo(creditLimit) > 0) {
         *     throw new CreditLimitExceededException("Requested amount " + loanAmount +
         *         " exceeds credit limit " + creditLimit);
         * }
         *
         * Also: Consider <= vs < - can user borrow exactly at their limit?
         */
        // make sure the user is allowed to borrow this amount
        if (loanAmount < loanManagementService.getCreditLimit(accountId)) {

            Account sourceAccount = accountService.getAccount(sourceAccountId);
            Account destinationAccount = accountService.getAccount(accountId);

            /*
             * [MINOR] CODE SMELL - Inconsistent null handling.
             * You're checking sourceAccount for null but not destinationAccount.
             * Both could be null if accounts don't exist.
             *
             * Also: orElseThrow() without a supplier throws NoSuchElementException,
             * which is not meaningful for API consumers.
             * Use: .orElseThrow(() -> new AccountNotFoundException(sourceAccountId));
             */
            Optional.ofNullable(sourceAccount).orElseThrow();
            double balance = sourceAccount.getBalance();  // [CRITICAL] Use BigDecimal!

            /*
             * [CRITICAL] CONCURRENCY - No transaction management!
             * Financial operations MUST be atomic. Current code can lead to:
             * 1. Race conditions (two threads debit same account simultaneously)
             * 2. Partial failures (debit succeeds, credit fails = money vanishes!)
             * 3. Inconsistent state if 3rd party registration fails
             *
             * MUST HAVE:
             * @Transactional(isolation = Isolation.SERIALIZABLE)
             * Or use distributed locks for critical sections.
             *
             * Also consider: Saga pattern or 2PC for distributed transactions with 3rd party.
             */

            /*
             * [MAJOR] COMPARISON - Using > for monetary comparison with double is unsafe.
             * Floating-point comparisons should use epsilon tolerance or BigDecimal.compareTo()
             *
             * if (balance.compareTo(loanAmount) >= 0) { ... }
             */
            if (balance > loanAmount) {

                /*
                 * [CRITICAL] ATOMICITY - Debit and credit must be atomic!
                 * If credit() fails after debit() succeeds, money is lost from sourceAccount.
                 *
                 * Pattern needed:
                 * try {
                 *     accountService.transfer(sourceAccount, destinationAccount, loanAmount);
                 *     // OR wrap in try-catch with rollback
                 * } catch (Exception e) {
                 *     // Compensating transaction or rollback
                 *     throw new TransferFailedException("Failed to complete transfer", e);
                 * }
                 */
                accountService.debit(sourceAccount, loanAmount);
                accountService.credit(destinationAccount, loanAmount);

                /*
                 * [CRITICAL] SECURITY & RELIABILITY - new Random().nextInt() for loan ID!
                 * MULTIPLE ISSUES:
                 * 1. Random IDs can COLLIDE - duplicate loan IDs will corrupt data
                 * 2. Random() is not cryptographically secure
                 * 3. ID generation should be delegated to database (auto-increment/UUID)
                 *    or the 3rd party loan management system
                 * 4. What if registerLoan() fails? Money is already transferred!
                 *
                 * RECOMMENDATION:
                 * - Use UUID.randomUUID() at minimum for uniqueness
                 * - Better: Let the loan management service generate the ID
                 * - Best: Use database sequence or distributed ID generator (Snowflake)
                 *
                 * Also: Consider idempotency keys to prevent duplicate loan registration
                 * on retries.
                 */

                /*
                 * [MAJOR] ERROR HANDLING - No exception handling for 3rd party call.
                 * The class comment mentions "we get a lot of errors from the 3rd party".
                 * Yet there's no try-catch, no retry logic, no circuit breaker!
                 *
                 * RECOMMENDATION:
                 * - Add try-catch with proper exception handling
                 * - Implement retry with exponential backoff (Spring Retry)
                 * - Add circuit breaker pattern (Resilience4j)
                 * - Log failures and trigger alerts for monitoring
                 *
                 * Example:
                 * @Retryable(value = LoanServiceException.class, maxAttempts = 3,
                 *            backoff = @Backoff(delay = 1000, multiplier = 2))
                 */
                loanManagementService.registerLoan(new Random().nextInt(1000000),
                        loanAmount, AuthContext.getCurrentUserID());

                /*
                 * [MAJOR] AUDIT - No audit trail for this financial transaction.
                 * Fintech regulations (PCI-DSS, SOX, GDPR) require comprehensive audit logs.
                 *
                 * Log: WHO did WHAT, WHEN, from WHERE, with WHAT result.
                 * Consider: Separate audit service or event sourcing pattern.
                 *
                 * Example:
                 * auditService.logTransaction(TransactionType.LOAN,
                 *     accountId, loanAmount, sourceAccountId, userId, TransactionStatus.SUCCESS);
                 */

            } else {
                /*
                 * [MAJOR] ERROR HANDLING - Wrong exception for insufficient funds.
                 * This is a business validation failure (4xx), not server error (5xx).
                 *
                 * throw new InsufficientFundsException("Source account " + sourceAccountId +
                 *     " has insufficient balance. Required: " + loanAmount + ", Available: " + balance);
                 *
                 * Return HTTP 409 Conflict or 422 Unprocessable Entity.
                 */
                throw new InternalServerError();
            }
        }
        /*
         * [MAJOR] SILENT FAILURE - If credit limit check fails, nothing happens!
         * The method completes successfully (200 OK) even though loan was NOT processed.
         * Client has no idea their request was rejected.
         *
         * MUST add else block:
         * else {
         *     throw new CreditLimitExceededException("Loan amount " + loanAmount +
         *         " exceeds your credit limit");
         * }
         */
    }

}

/*
 * ============================================================================================
 * SUMMARY OF CRITICAL ISSUES (Must Fix Before Merge):
 * ============================================================================================
 *
 * 1. SECURITY - isAdminAgent passed as request parameter (BROKEN ACCESS CONTROL)
 * 2. SECURITY - No input validation or sanitization
 * 3. DATA INTEGRITY - Using 'double' for money instead of BigDecimal
 * 4. ATOMICITY - No transaction management for debit/credit operations
 * 5. DATA INTEGRITY - Random loan IDs can collide
 * 6. RELIABILITY - No error handling for 3rd party service calls
 * 7. API DESIGN - Void return type with potential silent failures
 * 8. HTTP SEMANTICS - PUT method for non-idempotent operation
 *
 * ============================================================================================
 * MAJOR ISSUES (Should Fix):
 * ============================================================================================
 *
 * 1. Null pointer risks on account lookups
 * 2. Inconsistent API versioning (v3 class, v2 path)
 * 3. Field injection instead of constructor injection
 * 4. InternalServerError used for business logic failures
 * 5. No audit logging for financial transactions
 * 6. Silent failure when credit limit exceeded
 * 7. Object comparison with != instead of .equals()
 *
 * ============================================================================================
 * SUGGESTED REFACTORED SIGNATURE:
 * ============================================================================================
 *
 * @PostMapping("/{accountId}/loans")
 * @Transactional(isolation = Isolation.SERIALIZABLE)
 * @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
 * public ResponseEntity<LoanResponse> createLoan(
 *     @PathVariable @NotBlank @ValidAccountId String accountId,
 *     @RequestBody @Valid LoanRequest loanRequest) {
 *     // Implementation with proper validation, security, and error handling
 * }
 *
 * ============================================================================================
 * RECOMMENDED READING:
 * ============================================================================================
 *
 * - OWASP Top 10: https://owasp.org/Top10/
 * - Spring Security Best Practices: https://docs.spring.io/spring-security/reference/
 * - RESTful API Design: https://restfulapi.net/
 * - Java Money API (JSR 354): https://javamoney.github.io/
 * - Spring Transaction Management: https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction
 * - Resilience4j Circuit Breaker: https://resilience4j.readme.io/docs/circuitbreaker
 *
 * ============================================================================================
 */