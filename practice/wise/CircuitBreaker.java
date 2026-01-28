package wise;

import java.util.ArrayList;
import java.util.List;

/**
 * Circuit Breaker is microservice pattern, which prevents cascading failures
 * and improve the resilience of the system.
 * When a service experiences high number of repeated failures or high latency,
 * circuit breaker trips and temporarily stops requests to the failing service, allowing it time to recover.
 * Circuit Breaker implementation for service-to-service communication.
 * Monitors API failures and opens/closes circuit based on thresholds.
 * <p>
 * In production systems :
 * <p>
 * Use thread-safe collections
 * <p>
 * Add HALF_OPEN state for gradual recovery
 * <p>
 * Use a more efficient data structure for sliding window
 * <p>
 * Add more sophisticated failure detection
 * <p>
 * Consider using established libraries like Resilience4j or Hystrix
 */
public class CircuitBreaker {

    // Service identifier
    private final String serviceName;
    // Configuration parameters
    private final int timeWindowMinutes;  // X minutes - monitoring window
    private final int failureThreshold;   // Y failures - threshold to open circuit
    private final int coolOffPeriodMinutes; // Z minutes - cool off period
    // State variables
    private CircuitState currentState;
    private long circuitOpenedTime;
    private List<ApiCall> apiCallHistory;

    /**
     * Constructor to initialize Circuit Breaker for a service
     *
     * @param serviceName          Name of the service being monitored
     * @param timeWindowMinutes    Time window (X minutes) to monitor failures
     * @param failureThreshold     Failure count (Y) to trigger circuit opening
     * @param coolOffPeriodMinutes Cool off period (Z minutes) before circuit closes
     */
    public CircuitBreaker(String serviceName, int timeWindowMinutes,
                          int failureThreshold, int coolOffPeriodMinutes) {
        this.serviceName = serviceName;
        this.timeWindowMinutes = timeWindowMinutes;
        this.failureThreshold = failureThreshold;
        this.coolOffPeriodMinutes = coolOffPeriodMinutes;
        this.currentState = CircuitState.CLOSED;
        this.apiCallHistory = new ArrayList<>();
        this.circuitOpenedTime = 0;
    }

    /**
     * Records an API call result (success or failure)
     *
     * @param success true if API call succeeded, false if failed
     */
    public void recordApiCall(boolean success) {
        long currentTime = System.currentTimeMillis();
        ApiCall apiCall = new ApiCall(currentTime, success);
        apiCallHistory.add(apiCall);

        // Clean old records older than time window
        cleanupOldRecords(currentTime);

        // Update circuit state based on current conditions
        updateCircuitState(currentTime);
    }

    /**
     * Removes API call records older than the monitoring time window
     *
     * @param currentTime Current timestamp in milliseconds
     */
    private void cleanupOldRecords(long currentTime) {
        long windowMillis = timeWindowMinutes * 60 * 1000L;
        List<ApiCall> recentCalls = new ArrayList<>();

        for (ApiCall call : apiCallHistory) {
            if (currentTime - call.getTimestamp() <= windowMillis) {
                recentCalls.add(call);
            }
        }

        apiCallHistory = recentCalls;
    }

    /**
     * Updates the circuit state based on current conditions
     *
     * @param currentTime Current timestamp in milliseconds
     */
    private void updateCircuitState(long currentTime) {
        switch (currentState) {
            case CLOSED:
                // Check if failure threshold is reached
                if (shouldOpenCircuit()) {
                    openCircuit(currentTime);
                }
                break;

            case OPEN:
                // Check if cool off period has passed
                if (shouldCloseCircuit(currentTime)) {
                    closeCircuit();
                }
                break;
        }
    }

    /**
     * Determines if circuit should be opened based on failure count
     *
     * @return true if failure threshold is reached in the time window
     */
    private boolean shouldOpenCircuit() {
        int failureCount = 0;

        for (ApiCall call : apiCallHistory) {
            if (!call.isSuccess()) {
                failureCount++;
            }
        }

        return failureCount >= failureThreshold;
    }

    /**
     * Opens the circuit (stops allowing requests)
     *
     * @param currentTime Time when circuit is opened
     */
    private void openCircuit(long currentTime) {
        currentState = CircuitState.OPEN;
        circuitOpenedTime = currentTime;
        System.out.println("Circuit OPENED for service: " + serviceName +
                " at timestamp: " + currentTime);
    }

    /**
     * Determines if circuit should be closed after cool off period
     *
     * @param currentTime Current timestamp
     * @return true if cool off period has elapsed
     */
    private boolean shouldCloseCircuit(long currentTime) {
        long coolOffMillis = coolOffPeriodMinutes * 60 * 1000L;
        return (currentTime - circuitOpenedTime) >= coolOffMillis;
    }

    /**
     * Closes the circuit (allows requests to flow again)
     */
    private void closeCircuit() {
        currentState = CircuitState.CLOSED;
        // Clear history to start fresh monitoring period
        apiCallHistory.clear();
        System.out.println("Circuit CLOSED for service: " + serviceName);
    }

    /**
     * Checks if request should be allowed or failed fast
     *
     * @return true if request should be allowed, false for fail-fast
     */
    public boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Update state based on current time
        updateCircuitState(currentTime);

        return currentState != CircuitState.OPEN;
    }

    /**
     * Gets current circuit state
     *
     * @return current state of the circuit
     */
    public CircuitState getCurrentState() {
        return currentState;
    }

    /**
     * Gets failure count in the current time window
     *
     * @return number of failures in the monitoring window
     */
    public int getCurrentFailureCount() {
        int count = 0;
        for (ApiCall call : apiCallHistory) {
            if (!call.isSuccess()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets total API call count in the current time window
     *
     * @return total number of API calls in monitoring window
     */
    public int getTotalApiCallsInWindow() {
        return apiCallHistory.size();
    }

    // Circuit states
    private enum CircuitState {
        CLOSED,     // Normal operation, requests allowed
        OPEN,       // Circuit open, fail-fast mode
        HALF_OPEN   // Testing if service is recovering (not fully implemented in basic version)
    }

    // Inner class to represent API Call records
    private static class ApiCall {
        private long timestamp;
        private boolean success;

        public ApiCall(long timestamp, boolean success) {
            this.timestamp = timestamp;
            this.success = success;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}

/**
 * Demo class to test the Circuit Breaker implementation
 */
class CircuitBreakerDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create a circuit breaker with:
        // - 5 minute monitoring window
        // - 3 failure threshold
        // - 2 minute cool off period
        CircuitBreaker circuitBreaker = new CircuitBreaker("PaymentService", 5, 3, 2);

        System.out.println("=== Testing Circuit Breaker ===");

        // Simulate API calls
        System.out.println("\n1. Making successful calls...");
        for (int i = 0; i < 2; i++) {
            circuitBreaker.recordApiCall(true);
            System.out.println("Call " + (i + 1) + ": Allowed=" + circuitBreaker.allowRequest() +
                    ", State=" + circuitBreaker.getCurrentState());
        }

        System.out.println("\n2. Simulating failures to trigger circuit opening...");
        for (int i = 0; i < 3; i++) {
            circuitBreaker.recordApiCall(false); // Record failure
            System.out.println("Call " + (i + 1) + ": Allowed=" + circuitBreaker.allowRequest() +
                    ", State=" + circuitBreaker.getCurrentState() +
                    ", Failures=" + circuitBreaker.getCurrentFailureCount());
        }

        System.out.println("\n3. Circuit should be OPEN now - testing fail-fast behavior...");
        for (int i = 0; i < 3; i++) {
            System.out.println("Request " + (i + 1) + ": Allowed=" + circuitBreaker.allowRequest() +
                    ", State=" + circuitBreaker.getCurrentState());
        }

        System.out.println("\n4. Waiting for cool off period...");
        Thread.sleep(3 * 60 * 1000); // Wait 3 minutes (more than cool off period)

        System.out.println("5. After cool off period...");
        circuitBreaker.recordApiCall(true); // Record a success
        System.out.println("Request: Allowed=" + circuitBreaker.allowRequest() +
                ", State=" + circuitBreaker.getCurrentState());

        System.out.println("\n=== Demo Complete ===");
    }
}