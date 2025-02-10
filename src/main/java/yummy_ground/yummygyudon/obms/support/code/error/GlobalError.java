package yummy_ground.yummygyudon.obms.support.code.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum GlobalError implements ErrorCode {
    INTERNAL_SERVER_ERROR_IN_FILTER(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.(필터)"),

    ;
    private final HttpStatus status;
    private final String message;
}
