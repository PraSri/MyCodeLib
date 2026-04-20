package microsoft.ratelimiter;

import java.util.Collections;
import java.util.Map;

public class LimiterFactory {
    @SuppressWarnings("unchecked")
    public Limiter create(Map<String, Object> config) {
        String algorithm = (String) config.get("algorithm");
        Map<String, Object> algoConfig = (Map<String, Object>) config.get("algoConfig");
        if (algoConfig == null) {
            algoConfig = Collections.emptyMap();
        }

        if ("TokenBucket".equals(algorithm)) {
            int capacity = ((Number) algoConfig.getOrDefault("capacity", 0)).intValue();
            int refillRate = ((Number) algoConfig.getOrDefault("refillRatePerSecond", 0)).intValue();
            return new TokenBucketLimiter(capacity, refillRate);
        }

        if ("SlidingWindowLog".equals(algorithm)) {
            int maxRequests = ((Number) algoConfig.getOrDefault("maxRequests", 0)).intValue();
            long windowMs = ((Number) algoConfig.getOrDefault("windowMs", 0)).longValue();
            return new SlidingWindowLogLimiter(maxRequests, windowMs);
        }

        throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
    }
}
