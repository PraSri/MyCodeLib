package microsoft.ratelimiter;

public interface Limiter {
    RateLimitResult allow(String key);
}
