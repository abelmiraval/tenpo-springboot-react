package pe.abelmiraval.tenpo.infraestructure.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Base annotation that defines common responses for all API endpoints
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
@ApiResponse(responseCode = "404", description = "Resource Not Found", content = @Content)
@ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content)
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
@ApiResponse(responseCode = "503", description = "Service Unavailable", content = @Content)
public @interface BaseApiResponse {
    // Base annotation for common responses
}