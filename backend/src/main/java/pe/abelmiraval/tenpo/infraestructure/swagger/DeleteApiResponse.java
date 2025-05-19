package pe.abelmiraval.tenpo.infraestructure.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specific annotation for DELETE endpoints
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@BaseApiResponse
@ApiResponse(responseCode = "204", description = "Resource successfully deleted", content = @Content)
public @interface DeleteApiResponse {
}