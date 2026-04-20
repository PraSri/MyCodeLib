package microsoft.ratelimiter;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketLimiter implements Limiter {
    private final int capacity;
    private final int refillRatePerSecond;
    private final Map<String, TokenBucket> buckets = new HashMap<>();

    public TokenBucketLimiter(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public RateLimitResult allow(String key) {
        TokenBucket bucket = buckets.computeIfAbsent(key, k -> new TokenBucket(capacity, System.currentTimeMillis()));

        long now = System.currentTimeMillis();
        long elapsed = now - bucket.lastRefillTime;
        double tokensToAdd = (elapsed * refillRatePerSecond) / 1000.0;
        bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
        bucket.lastRefillTime = now;

        if (bucket.tokens >= 1) {
            bucket.tokens -= 1;
            int remaining = (int) Math.floor(bucket.tokens);
            return new RateLimitResult(true, remaining, null);
        }

        double tokensNeeded = 1 - bucket.tokens;
        long retryAfterMs = (long) Math.ceil((tokensNeeded * 1000) / refillRatePerSecond);
        return new RateLimitResult(false, 0, retryAfterMs);
    }

    private static class TokenBucket {
        double tokens;
        long lastRefillTime;

        TokenBucket(double initialTokens, long time) {
            this.tokens = initialTokens;
            this.lastRefillTime = time;
        }
    }
}
