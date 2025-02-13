package yummy_ground.yummygyudon.obms.support.code.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum GlobalError implements ErrorCode {
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "인증되지 않은 요청입니다."),
    ACCESS_DENIED_REQUEST(HttpStatus.FORBIDDEN, "권한이 충족되지 않은 요청입니다."),
    INTERNAL_SERVER_ERROR_IN_FILTER(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다. (필터)"),
    INTERNAL_SERVER_ERROR_IN_RUNTIME(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다. (어플리케이션)"),
    ;
    private final HttpStatus status;
    private final String message;
}
