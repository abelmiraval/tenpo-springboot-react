package pe.abelmiraval.tenpo.application.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.abelmiraval.tenpo.application.wrapper.BaseResponse;
import pe.abelmiraval.tenpo.domain.constants.Message;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "pe.abelmiraval.tenpo")
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");
    private static final String LOG_DETAILS = "Detail: {} ";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public BaseResponse<String> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        logger.info("Error: MethodArgumentNotValidException: {}", methodArgumentNotValidException.getMessage());

        List<FieldValidationError> fieldValidationErrorList = methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());

        BaseResponse<String> baseResponse = BaseResponse.<String>builder()
                .success(Boolean.FALSE)
                .message(Message.CODE_400)
                .errors(fieldValidationErrorList)
                .build();

        logger.info(LOG_DETAILS, methodArgumentNotValidException.getMessage());
        return baseResponse;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public BaseResponse<String> constraintViolationException(ConstraintViolationException constraintViolationException) {
        logger.info("Error: ConstraintViolationException: {}", constraintViolationException.getMessage());

        List<FieldValidationError> fieldValidationErrorList = constraintViolationException.getConstraintViolations().stream()
                .map(fieldError -> new FieldValidationError(fieldError.getPropertyPath().toString(), fieldError.getMessage())).collect(
                        Collectors.toList());

        BaseResponse<String> baseResponse = BaseResponse.<String>builder()
                .success(Boolean.FALSE)
                .message(Message.CODE_400)
                .errors(fieldValidationErrorList)
                .build();

        logger.info(LOG_DETAILS, constraintViolationException.getMessage());
        return baseResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class})
    @ResponseBody
    public BaseResponse<String> badRequestException(Exception badRequestException) {
        logger.info("Error: badRequestException: {}", badRequestException.getMessage());

        BaseResponse<String> baseResponse = BaseResponse.<String>builder()
                .success(Boolean.FALSE)
                .message(Message.CODE_400)
                .build();

        logger.info(LOG_DETAILS, baseResponse);
        return baseResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DatabaseException.class})
    @ResponseBody
    public BaseResponse<String> databaseException(DatabaseException databaseException) {
        logger.info("Error: DatabaseException: {}", databaseException.getMessage());

        BaseResponse<String> baseResponse = BaseResponse.<String>builder()
                .success(Boolean.FALSE)
                .message(Message.CODE_500)
                .build();

        logger.info(LOG_DETAILS, baseResponse);
        return baseResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public BaseResponse<String> businessException(BusinessException businessException) {
        logger.info("Error: BaseException: {}", businessException.getMessage());

        List<FieldValidationError> fieldValidationErrorList = List.of(new FieldValidationError(businessException.getField(), businessException.getMessage()));

        BaseResponse<String> baseResponse = BaseResponse.<String>builder()
                .success(Boolean.FALSE)
                .message(Message.CODE_400)
                .errors(fieldValidationErrorList)
                .build();

        logger.info(LOG_DETAILS, baseResponse);
        return baseResponse;
    }

}