package microsoft.ratelimiter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindowLogLimiter implements Limiter {
    private final int maxRequests;
    private final long windowMs;
    private final Map<String, RequestLog> logs = new HashMap<>();

    public SlidingWindowLogLimiter(int maxRequests, long windowMs) {
        this.maxRequests = maxRequests;
        this.windowMs = windowMs;
    }

    @Override
    public RateLimitResult allow(String key) {
        RequestLog log = logs.computeIfAbsent(key, k -> new RequestLog());

        long now = System.currentTimeMillis();
        long cutoff = now - windowMs;

        while (!log.timestamps.isEmpty() && log.timestamps.peekFirst() < cutoff) {
            log.timestamps.pollFirst();
        }

        if (log.timestamps.size() < maxRequests) {
            log.timestamps.addLast(now);
            int remaining = maxRequests - log.timestamps.size();
            return new RateLimitResult(true, remaining, null);
        }

        long oldestTimestamp = log.timestamps.peekFirst();
        long retryAfterMs = (oldestTimestamp + windowMs) - now;
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    private static class RequestLog {
        private final Deque<Long> timestamps = new ArrayDeque<>();
    }
}
