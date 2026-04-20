package microsoft.ratelimiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateLimiter {
    private final Map<String, Limiter> limiters;
    private final Limiter defaultLimiter;

    public RateLimiter(List<Map<String, Object>> configs, Map<String, Object> defaultConfig) {
        this.limiters = new HashMap<>();
        LimiterFactory factory = new LimiterFactory();

        for (Map<String, Object> config : configs) {
            String endpoint = (String) config.get("endpoint");
            if (endpoint == null) {
                continue;
            }
            Limiter limiter = factory.create(config);
            limiters.put(endpoint, limiter);
        }

        this.defaultLimiter = factory.create(defaultConfig);
    }

    public RateLimitResult allow(String clientId, String endpoint) {
        Limiter limiter = limiters.getOrDefault(endpoint, defaultLimiter);
        return limiter.allow(clientId);
    }
}
