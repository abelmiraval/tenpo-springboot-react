package pe.abelmiraval.tenpo.infraestructure.config.ratelimit;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RateLimiterHeader {
    RATE_LIMITER_WAIT_FOR_REFILL("X-Rate-Limiter-Wait_For_Refill"), RATE_LIMITER_RESET("X-Rate-Limiter-Reset"), RATE_LIMITER_REMAINING("X-Rate-Limiter-Remaining");
    public final String name;
}