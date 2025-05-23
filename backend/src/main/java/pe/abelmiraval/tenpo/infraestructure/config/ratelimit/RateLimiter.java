package pe.abelmiraval.tenpo.infraestructure.config.ratelimit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RateLimiter {
    TimeUnit timeUnit() default TimeUnit.MINUTES;
    long timeValue() default 60;
    long restriction() default 500;
}