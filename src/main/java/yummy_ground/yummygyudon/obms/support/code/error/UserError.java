package yummy_ground.yummygyudon.obms.support.code.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum UserError implements ErrorCode {
    // 403


    // 404
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당하는 유저를 찾을 수 없습니다."),
    NOT_FOUND_ROLE(HttpStatus.NOT_FOUND, "해당하는 권한을 찾을 수 없습니다."),
    ;
    private final HttpStatus status;
    private final String message;
}
