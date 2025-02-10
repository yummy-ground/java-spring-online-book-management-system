package yummy_ground.yummygyudon.obms.support.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import yummy_ground.yummygyudon.obms.support.code.ErrorCode;
import yummy_ground.yummygyudon.obms.support.code.SuccessCode;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class ApiResponseUtil {
    public static <T> ResponseEntity<BaseResponse<?>> success(SuccessCode code, T data) {
        return ResponseEntity
                .status(code.getStatus())
                .body(BaseResponse.of(code.getMessage(), data));
    }
    public static ResponseEntity<BaseResponse<?>> success(SuccessCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(BaseResponse.of(code.getMessage()));
    }
    public static ResponseEntity<BaseResponse<?>> fail(ErrorCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(BaseResponse.of(code.getMessage()));
    }
}
