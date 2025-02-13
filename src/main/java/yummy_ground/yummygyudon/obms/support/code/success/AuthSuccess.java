package yummy_ground.yummygyudon.obms.support.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import yummy_ground.yummygyudon.obms.support.code.SuccessCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum AuthSuccess implements SuccessCode {
    AUTHORIZE(HttpStatus.CREATED, "인증 및 인가에 성공했습니다."),
    REISSUE(HttpStatus.CREATED, "재인증 및 재인가에 성공했습니다."),
    ;
    private final HttpStatus status;
    private final String message;
}
