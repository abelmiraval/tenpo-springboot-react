package pe.abelmiraval.tenpo.application.wrapper;

import lombok.*;
import pe.abelmiraval.tenpo.application.exceptions.FieldValidationError;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BaseResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private List<FieldValidationError> errors;

}