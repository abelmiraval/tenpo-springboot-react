package pe.abelmiraval.tenpo.infraestructure.config.ratelimit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class RateLimiterKey implements Serializable {
    private String userId;
    private String[] uri;
}