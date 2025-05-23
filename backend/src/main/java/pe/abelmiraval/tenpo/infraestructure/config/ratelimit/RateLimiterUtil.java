package pe.abelmiraval.tenpo.infraestructure.config.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.ConfigurationBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class RateLimiterUtil {
    public static String getUniqueKeyName(HttpServletRequest httpServletRequest){
        if(httpServletRequest != null){
            return MessageFormatter.arrayFormat("{}:{}",
                    new Object[] {
                            StringUtils.hasText(httpServletRequest.getRemoteAddr()) ? httpServletRequest.getRemoteAddr() : "0.0.0.0",
                            StringUtils.hasText(httpServletRequest.getRemoteUser()) ? httpServletRequest.getRemoteUser() : "anonymous"
                    }
            ).getMessage();
        }
        return "0.0.0.0:anonymous:NONE_REQUEST_HOST:NONE_REQUEST_PORT";
    }
    public static BucketConfiguration rateLimiterAnnotationsToBucketConfiguration(List<RateLimiter> rateLimiters) {
        ConfigurationBuilder configBuilder  = new ConfigurationBuilder();
        rateLimiters.forEach(limiter -> configBuilder.addLimit(buildBandwidth(limiter)));
        return  configBuilder.build();
    }

    public  static Optional<List<RateLimiter>> getRateLimiters(HandlerMethod handlerMethod) {
        RateLimiters  rateLimitersAnnotation  = handlerMethod.getMethodAnnotation(RateLimiters.class);
        if(rateLimitersAnnotation !=  null) {
            return  Optional.of(Arrays.asList(rateLimitersAnnotation.value()));
        }
        RateLimiter  rateLimiterAnnotation  = handlerMethod.getMethodAnnotation(RateLimiter.class);
        if(rateLimiterAnnotation !=  null) {
            return  Optional.of(List.of(rateLimiterAnnotation));
        }
        return  Optional.empty();
    }

    private static Bandwidth buildBandwidth(RateLimiter rateLimiter) {
        TimeUnit timeUnit  = rateLimiter.timeUnit();
        long  timeValue  = rateLimiter.timeValue();
        long  restriction  = rateLimiter.restriction();
        return Bandwidth.builder()
                .capacity(restriction)
                .refillGreedy(restriction, convert(timeValue, timeUnit))
                .build();
    }
    private static Duration convert(long dur, TimeUnit timeUnit) {
        return Duration.of(dur, timeUnit.toChronoUnit());
    }
}
