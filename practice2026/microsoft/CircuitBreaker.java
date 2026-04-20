package microsoft;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Supplier;

public class CircuitBreaker {
    private final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private final AtomicInteger successCount = new AtomicInteger(0);
    private final int failureThreshold;    // e.g., 5 failures
    private final long retryPeriod;        // e.g., 10000ms
    private final int halfOpenLimit;       // e.g., 3 successes to close
    private long lastFailureTime;
    public CircuitBreaker(int threshold, long retryPeriod, int halfOpenLimit) {
        this.failureThreshold = threshold;
        this.retryPeriod = retryPeriod;
        this.halfOpenLimit = halfOpenLimit;
    }

    public <T> T execute(Supplier<T> action, Function<Throwable, T> fallback) {
        updateState();

        if (state.get() == State.OPEN) {
            return fallback.apply(new RuntimeException("Circuit is OPEN"));
        }

        try {
            T result = action.get();
            onSuccess();
            return result;
        } catch (Throwable t) {
            onFailure();
            return fallback.apply(t);
        }
    }

    private void updateState() {
        if (state.get() == State.OPEN &&
                (System.currentTimeMillis() - lastFailureTime) >= retryPeriod) {
            state.set(State.HALF_OPEN);
        }
    }

    private void onSuccess() {
        if (state.get() == State.HALF_OPEN) {
            if (successCount.incrementAndGet() >= halfOpenLimit) {
                reset();
            }
        } else if (state.get() == State.CLOSED) {
            failureCount.set(0); // Reset failures on clean success
        }
    }

    private void onFailure() {
        failureCount.incrementAndGet();
        lastFailureTime = System.currentTimeMillis();

        if (state.get() == State.CLOSED && failureCount.get() >= failureThreshold) {
            state.set(State.OPEN);
        } else if (state.get() == State.HALF_OPEN) {
            state.set(State.OPEN); // Immediate trip back to OPEN
        }
    }

    private void reset() {
        state.set(State.CLOSED);
        failureCount.set(0);
        successCount.set(0);
    }

    enum State {CLOSED, OPEN, HALF_OPEN}
}