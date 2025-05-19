package pe.abelmiraval.tenpo.infraestructure.swagger;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specific annotation for PUT/PATCH endpoints
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@BaseApiResponse
@ApiResponse(responseCode = "200", description = "Resource successfully updated")
public @interface PutApiResponse {
}