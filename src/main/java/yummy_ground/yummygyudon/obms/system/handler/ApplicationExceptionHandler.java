package yummy_ground.yummygyudon.obms.system.handler;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.util.ApiResponseUtil;
import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;
import yummy_ground.yummygyudon.obms.support.exception.AuthException;
import yummy_ground.yummygyudon.obms.support.exception.BookException;
import yummy_ground.yummygyudon.obms.support.exception.UserException;
import yummy_ground.yummygyudon.obms.support.exception.RentalException;
import yummy_ground.yummygyudon.obms.support.exception.GlobalException;

@Slf4j
@Hidden
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage());
        return ApiResponseUtil.fail(GlobalError.INTERNAL_SERVER_ERROR_IN_RUNTIME);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(AuthException authException) {
        log.error("[Auth Exception] " + authException.getMessage());
        return ApiResponseUtil.fail(authException.getErrorCode());
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(BookException bookException) {
        log.error("[Book Exception] " + bookException.getMessage());
        return ApiResponseUtil.fail(bookException.getErrorCode());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(UserException userException) {
        log.error("[User Exception] " + userException.getMessage());
        return ApiResponseUtil.fail(userException.getErrorCode());
    }

    @ExceptionHandler(RentalException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(RentalException rentalException) {
        log.error("[Rental Exception] " + rentalException.getMessage());
        return ApiResponseUtil.fail(rentalException.getErrorCode());
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(GlobalException globalException) {
        log.error("[Global / System Exception] " + globalException.getMessage());
        return ApiResponseUtil.fail(globalException.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> catchArgumentException(MethodArgumentNotValidException argumentValidException) {
        List<String> errorMessages = argumentValidException.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errorMessages);
        log.error(errorMessage);
        return ApiResponseUtil.fail(argumentValidException.getMessage());
    }
    private String getErrorMessage(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return String.format("[%s]은 필수 값입니다.", ((FieldError) objectError).getField());
        } else {
            return objectError.getDefaultMessage();
        }
    }

}
