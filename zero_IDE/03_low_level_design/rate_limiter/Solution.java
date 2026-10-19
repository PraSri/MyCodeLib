import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Low-Level Design: Multi-Strategy Rate Limiter (Grey Orange Problem)
 *
 * Implements:
 * 1. Token Bucket Rate Limiter (Bursty traffic support)
 * 2. Sliding Window Log Rate Limiter (Strict sliding window accuracy)
 */
public class Solution {

    public interface RateLimiter {
        boolean allowRequest(String clientId);
    }

    /**
     * 1. Token Bucket Rate Limiter
     * Tokens refill continuously over time up to capacity.
     */
    public static class TokenBucketRateLimiter implements RateLimiter {
        private final long capacity;
        private final double refillRatePerSecond;

        private static class Bucket {
            double tokens;
            long lastRefillTimestampNanos;
            final ReentrantLock lock = new ReentrantLock();

            Bucket(long capacity) {
                this.tokens = capacity;
                this.lastRefillTimestampNanos = System.nanoTime();
            }
        }

        private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

        public TokenBucketRateLimiter(long capacity, double refillRatePerSecond) {
            this.capacity = capacity;
            this.refillRatePerSecond = refillRatePerSecond;
        }

        @Override
        public boolean allowRequest(String clientId) {
            Bucket bucket = buckets.computeIfAbsent(clientId, k -> new Bucket(capacity));
            bucket.lock.lock();
            try {
                long now = System.nanoTime();
                double elapsedSeconds = (now - bucket.lastRefillTimestampNanos) / 1_000_000_000.0;
                bucket.tokens = Math.min(capacity, bucket.tokens + elapsedSeconds * refillRatePerSecond);
                bucket.lastRefillTimestampNanos = now;

                if (bucket.tokens >= 1.0) {
                    bucket.tokens -= 1.0;
                    return true;
                }
                return false;
            } finally {
                bucket.lock.unlock();
            }
        }
    }

    /**
     * 2. Sliding Window Log Rate Limiter
     * Tracks timestamps of requests within a sliding window.
     */
    public static class SlidingWindowLogRateLimiter implements RateLimiter {
        private final int maxRequests;
        private final long windowMillis;

        private static class ClientLog {
            final Deque<Long> timestamps = new ArrayDeque<>();
            final ReentrantLock lock = new ReentrantLock();
        }

        private final Map<String, ClientLog> logs = new ConcurrentHashMap<>();

        public SlidingWindowLogRateLimiter(int maxRequests, long windowMillis) {
            this.maxRequests = maxRequests;
            this.windowMillis = windowMillis;
        }

        @Override
        public boolean allowRequest(String clientId) {
            ClientLog clientLog = logs.computeIfAbsent(clientId, k -> new ClientLog());
            clientLog.lock.lock();
            try {
                long now = System.currentTimeMillis();
                long windowStart = now - windowMillis;

                // Evict timestamps older than current sliding window
                while (!clientLog.timestamps.isEmpty() && clientLog.timestamps.peekFirst() <= windowStart) {
                    clientLog.timestamps.pollFirst();
                }

                if (clientLog.timestamps.size() < maxRequests) {
                    clientLog.timestamps.offerLast(now);
                    return true;
                }
                return false;
            } finally {
                clientLog.lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter tokenBucket = new TokenBucketRateLimiter(2, 1.0); // 2 capacity, 1 token/sec
        System.out.println("TB Req 1: " + tokenBucket.allowRequest("userA")); // true
        System.out.println("TB Req 2: " + tokenBucket.allowRequest("userA")); // true
        System.out.println("TB Req 3 (throttled): " + tokenBucket.allowRequest("userA")); // false
        Thread.sleep(1100);
        System.out.println("TB Req 4 (refilled): " + tokenBucket.allowRequest("userA")); // true
    }
}
