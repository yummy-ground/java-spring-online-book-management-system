package yummy_ground.yummygyudon.obms.system.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.util.ApiResponseUtil;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<?>> catchRuntimeException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage());
        return ApiResponseUtil.fail(GlobalError.INTERNAL_SERVER_ERROR_IN_RUNTIME);
    }

}
