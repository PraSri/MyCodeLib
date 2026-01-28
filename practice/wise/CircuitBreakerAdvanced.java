package wise;


import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Circuit Breaker with sophisticated failure detection.
 * Enhanced with failure types, response time tracking, and error rate calculation.
 */
public class CircuitBreakerAdvanced {

    // Configuration parameters
    private final String serviceName;
    private final int timeWindowMinutes;
    private final int failureThreshold;
    private final int coolOffPeriodMinutes;
    private final int halfOpenTestLimit;
    // New: Advanced detection parameters
    private final long slowResponseThreshold;  // Response time threshold in ms
    private final double errorRateThreshold;   // Error rate percentage (0-100)
    private final boolean treatSlowAsFailure;  // Treat slow responses as failures
    // State variables
    private volatile CircuitState currentState;
    private volatile long circuitOpenedTime;
    // Thread-safe collections
    private ConcurrentLinkedDeque<ApiCall> apiCallHistory;
    private AtomicInteger currentFailureCount;
    private AtomicInteger halfOpenTestCount;
    // New: Track response time statistics
    private AtomicInteger totalResponseTime;
    private AtomicInteger totalCallsInWindow;

    /**
     * Constructor with default advanced parameters
     */
    public CircuitBreakerAdvanced(String serviceName, int timeWindowMinutes,
                                  int failureThreshold, int coolOffPeriodMinutes) {
        this(serviceName, timeWindowMinutes, failureThreshold,
                coolOffPeriodMinutes, 3, 5000, 50.0, false);
    }

    /**
     * Enhanced constructor with advanced failure detection
     */
    public CircuitBreakerAdvanced(String serviceName, int timeWindowMinutes,
                                  int failureThreshold, int coolOffPeriodMinutes,
                                  int halfOpenTestLimit, long slowResponseThreshold,
                                  double errorRateThreshold, boolean treatSlowAsFailure) {
        this.serviceName = serviceName;
        this.timeWindowMinutes = timeWindowMinutes;
        this.failureThreshold = failureThreshold;
        this.coolOffPeriodMinutes = coolOffPeriodMinutes;
        this.halfOpenTestLimit = halfOpenTestLimit;
        this.slowResponseThreshold = slowResponseThreshold;
        this.errorRateThreshold = errorRateThreshold;
        this.treatSlowAsFailure = treatSlowAsFailure;

        this.currentState = CircuitState.CLOSED;
        this.apiCallHistory = new ConcurrentLinkedDeque<>();
        this.currentFailureCount = new AtomicInteger(0);
        this.halfOpenTestCount = new AtomicInteger(0);
        this.totalResponseTime = new AtomicInteger(0);  // New
        this.totalCallsInWindow = new AtomicInteger(0); // New
        this.circuitOpenedTime = 0;
    }

    /**
     * Enhanced API call recording with response time and failure type
     */
    public synchronized void recordApiCall(boolean success, long responseTime, FailureType failureType) {
        long currentTime = System.currentTimeMillis();

        // New: Check if slow response should be treated as failure
        boolean isSlowFailure = treatSlowAsFailure && responseTime > slowResponseThreshold;
        boolean shouldCountAsFailure = !success || isSlowFailure;

        // Record the API call with enhanced details
        ApiCall apiCall = new ApiCall(currentTime, success && !isSlowFailure, responseTime, failureType);
        apiCallHistory.addLast(apiCall);

        // Update statistics
        totalResponseTime.addAndGet((int) responseTime);
        totalCallsInWindow.incrementAndGet();

        if (shouldCountAsFailure) {
            currentFailureCount.incrementAndGet();
        }

        // Clean old records
        cleanupOldRecords(currentTime);

        // Handle based on state
        if (currentState == CircuitState.HALF_OPEN) {
            handleHalfOpenState(success && !isSlowFailure, currentTime);
            return;
        }

        updateCircuitState(currentTime);
    }

    /**
     * Overloaded method for backward compatibility
     */
    public synchronized void recordApiCall(boolean success) {
        recordApiCall(success, 0, success ? FailureType.NONE : FailureType.UNKNOWN);
    }

    /**
     * Enhanced cleanup with statistics update
     */
    private void cleanupOldRecords(long currentTime) {
        long windowMillis = timeWindowMinutes * 60 * 1000L;

        ApiCall oldestCall;
        while ((oldestCall = apiCallHistory.peekFirst()) != null) {
            if (currentTime - oldestCall.getTimestamp() <= windowMillis) {
                break;
            }

            ApiCall removedCall = apiCallHistory.pollFirst();
            if (removedCall != null) {
                // Update statistics when removing old records
                totalResponseTime.addAndGet((int) -removedCall.getResponseTime());
                totalCallsInWindow.decrementAndGet();

                // Only decrement failure count if it was counted as failure
                boolean wasSlowFailure = treatSlowAsFailure &&
                        removedCall.getResponseTime() > slowResponseThreshold;
                boolean wasCountedFailure = !removedCall.isSuccess() || wasSlowFailure;

                if (wasCountedFailure) {
                    currentFailureCount.decrementAndGet();
                }
            }
        }
    }

    /**
     * Handle HALF_OPEN state
     */
    private void handleHalfOpenState(boolean success, long currentTime) {
        if (success) {
            int testCount = halfOpenTestCount.incrementAndGet();
            if (testCount >= halfOpenTestLimit) {
                closeCircuit();
            }
        } else {
            openCircuit(currentTime);
            halfOpenTestCount.set(0);
        }
    }

    /**
     * Enhanced state update with multiple detection strategies
     */
    private void updateCircuitState(long currentTime) {
        if (currentState == CircuitState.CLOSED && shouldOpenCircuit()) {
            openCircuit(currentTime);
        } else if (currentState == CircuitState.OPEN && shouldCloseCircuit(currentTime)) {
            transitionToHalfOpen();
        }
    }

    /**
     * Enhanced: Multiple strategies to determine if circuit should open
     */
    private boolean shouldOpenCircuit() {
        // Strategy 1: Simple failure count (original requirement)
        boolean failureCountExceeded = currentFailureCount.get() >= failureThreshold;

        // Strategy 2: Error rate threshold (new)
        double errorRate = calculateErrorRate();
        boolean errorRateExceeded = errorRate >= errorRateThreshold;

        // Strategy 3: Response time degradation (new)
        boolean responseTimeDegraded = isResponseTimeDegraded();

        // Open circuit if any condition is met
        return failureCountExceeded || errorRateExceeded || responseTimeDegraded;
    }

    /**
     * New: Calculate error rate percentage
     */
    private double calculateErrorRate() {
        int totalCalls = totalCallsInWindow.get();
        if (totalCalls == 0) return 0.0;

        double errorRate = (currentFailureCount.get() * 100.0) / totalCalls;
        return errorRate;
    }

    /**
     * New: Check if response time is degraded
     */
    private boolean isResponseTimeDegraded() {
        int totalCalls = totalCallsInWindow.get();
        if (totalCalls == 0) return false;

        double avgResponseTime = totalResponseTime.get() / (double) totalCalls;
        return avgResponseTime > slowResponseThreshold * 2;  // Degraded if > 2x threshold
    }

    /**
     * Opens the circuit with enhanced logging
     */
    private void openCircuit(long currentTime) {
        currentState = CircuitState.OPEN;
        circuitOpenedTime = currentTime;
        halfOpenTestCount.set(0);

        // Enhanced logging with detection details
        String reason = getOpenReason();
        System.out.println("Circuit OPENED for service: " + serviceName +
                " - Reason: " + reason +
                " (Failures: " + currentFailureCount.get() +
                ", Error Rate: " + String.format("%.1f", calculateErrorRate()) + "%" +
                ", Avg Response: " + String.format("%.1f", getAverageResponseTime()) + "ms)");
    }

    /**
     * New: Determine why circuit opened
     */
    private String getOpenReason() {
        if (currentFailureCount.get() >= failureThreshold) {
            return "Failure count threshold exceeded";
        } else if (calculateErrorRate() >= errorRateThreshold) {
            return "Error rate threshold exceeded";
        } else if (isResponseTimeDegraded()) {
            return "Response time degraded";
        }
        return "Unknown";
    }

    /**
     * Determines if circuit should transition from OPEN
     */
    private boolean shouldCloseCircuit(long currentTime) {
        long coolOffMillis = coolOffPeriodMinutes * 60 * 1000L;
        return (currentTime - circuitOpenedTime) >= coolOffMillis;
    }

    /**
     * Transitions to HALF_OPEN
     */
    private void transitionToHalfOpen() {
        currentState = CircuitState.HALF_OPEN;
        halfOpenTestCount.set(0);
        System.out.println("Circuit HALF_OPEN for service: " + serviceName);
    }

    /**
     * Closes the circuit
     */
    private void closeCircuit() {
        currentState = CircuitState.CLOSED;
        apiCallHistory.clear();
        currentFailureCount.set(0);
        totalResponseTime.set(0);      // Reset statistics
        totalCallsInWindow.set(0);     // Reset statistics
        halfOpenTestCount.set(0);
        System.out.println("Circuit CLOSED for service: " + serviceName);
    }

    /**
     * Checks if request should be allowed
     */
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        updateCircuitState(currentTime);

        if (currentState == CircuitState.HALF_OPEN) {
            return halfOpenTestCount.get() < halfOpenTestLimit;
        }

        return currentState != CircuitState.OPEN;
    }

    /**
     * New: Get current statistics
     */
    public synchronized Statistics getStatistics() {
        return new Statistics(
                getCurrentState(),
                currentFailureCount.get(),
                totalCallsInWindow.get(),
                calculateErrorRate(),
                getAverageResponseTime(),
                getFailureDistribution()
        );
    }

    /**
     * New: Calculate average response time
     */
    private double getAverageResponseTime() {
        int totalCalls = totalCallsInWindow.get();
        if (totalCalls == 0) return 0.0;
        return totalResponseTime.get() / (double) totalCalls;
    }

    /**
     * New: Get distribution of failure types
     */
    private String getFailureDistribution() {
        // Simplified distribution - in real implementation would track counts per type
        return "Not implemented in minimal version";
    }

    // Getters (existing)
    public CircuitState getCurrentState() {
        return currentState;
    }

    public int getCurrentFailureCount() {
        return currentFailureCount.get();
    }

    public int getTotalApiCallsInWindow() {
        return apiCallHistory.size();
    }

    public int getHalfOpenTestCount() {
        return halfOpenTestCount.get();
    }

    // New: Classify different types of failures
    enum FailureType {
        NONE,           // No failure
        TIMEOUT,        // Request timeout
        SERVER_ERROR,   // 5xx errors
        CLIENT_ERROR,   // 4xx errors
        NETWORK_ERROR,  // Connection issues
        UNKNOWN         // Other failures
    }

    // Circuit states
    private enum CircuitState {
        CLOSED, OPEN, HALF_OPEN
    }

    // Enhanced ApiCall with more details
    private static class ApiCall {
        private long timestamp;
        private boolean success;
        private long responseTime;  // New: Track response time
        private FailureType failureType;  // New: Classify failures

        public ApiCall(long timestamp, boolean success, long responseTime, FailureType failureType) {
            this.timestamp = timestamp;
            this.success = success;
            this.responseTime = responseTime;
            this.failureType = failureType;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public boolean isSuccess() {
            return success;
        }

        public long getResponseTime() {
            return responseTime;
        }

        public FailureType getFailureType() {
            return failureType;
        }
    }

    /**
     * New: Statistics DTO
     */
    public static class Statistics {
        public final CircuitState state;
        public final int failureCount;
        public final int totalCalls;
        public final double errorRate;
        public final double avgResponseTime;
        public final String failureDistribution;

        public Statistics(CircuitState state, int failureCount, int totalCalls,
                          double errorRate, double avgResponseTime, String failureDistribution) {
            this.state = state;
            this.failureCount = failureCount;
            this.totalCalls = totalCalls;
            this.errorRate = errorRate;
            this.avgResponseTime = avgResponseTime;
            this.failureDistribution = failureDistribution;
        }
    }
}

/**
 * Demo class
 */
class CircuitBreakerAdvancedDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Testing Sophisticated Failure Detection ===");

        // Create circuit breaker with advanced detection
        CircuitBreakerAdvanced circuitBreaker = new CircuitBreakerAdvanced(
                "PaymentService", 5, 3, 2, 3, 2000, 30.0, true);

        System.out.println("\n1. Testing slow response detection...");

        // Make calls with varying response times
        circuitBreaker.recordApiCall(true, 1500, CircuitBreakerAdvanced.FailureType.NONE); // OK
        circuitBreaker.recordApiCall(true, 3000, CircuitBreakerAdvanced.FailureType.NONE); // Slow - treated as failure
        circuitBreaker.recordApiCall(true, 3500, CircuitBreakerAdvanced.FailureType.NONE); // Slow - treated as failure

        System.out.println("After slow responses:");
        System.out.println("State: " + circuitBreaker.getCurrentState());
        System.out.println("Failures: " + circuitBreaker.getCurrentFailureCount());

        System.out.println("\n2. Testing error rate detection...");

        // Reset for demo
        circuitBreaker = new CircuitBreakerAdvanced(
                "PaymentService", 5, 10, 2, 3, 5000, 50.0, false);

        // Make 10 calls with 6 failures (60% error rate > 50% threshold)
        for (int i = 0; i < 4; i++) {
            circuitBreaker.recordApiCall(true, 100, CircuitBreakerAdvanced.FailureType.NONE);
        }
        for (int i = 0; i < 6; i++) {
            circuitBreaker.recordApiCall(false, 100, CircuitBreakerAdvanced.FailureType.SERVER_ERROR);
        }

        System.out.println("State: " + circuitBreaker.getCurrentState());

        System.out.println("\n3. Testing statistics...");
        CircuitBreakerAdvanced.Statistics stats = circuitBreaker.getStatistics();
        System.out.println("Error Rate: " + String.format("%.1f", stats.errorRate) + "%");
        System.out.println("Total Calls: " + stats.totalCalls);

        System.out.println("\n=== Demo Complete ===");
    }
}
