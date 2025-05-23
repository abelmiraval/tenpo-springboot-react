package pe.abelmiraval.tenpo.infraestructure.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.bucket4j.ConsumptionProbe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BucketRateLimiterException extends RuntimeException{
    private final ConsumptionProbe consumptionProbe;
    public BucketRateLimiterException(String message, ConsumptionProbe consumptionProbe) throws JsonProcessingException {
        super(message);
        this.consumptionProbe = consumptionProbe;
        log.error("getNanosToWaitForReset: {} , getNanosToWaitForRefill: {}, getRemainingTokens: {}", this.consumptionProbe.getNanosToWaitForReset(), this.consumptionProbe.getNanosToWaitForRefill(), this.consumptionProbe.getRemainingTokens());
    }
}