package yummy_ground.yummygyudon.obms.support.code.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum AuthError implements ErrorCode {
    // 400
    NO_ACCESS_TOKEN_IN_HEADER(HttpStatus.BAD_REQUEST, "액세스 토큰이 존재하지 않습니다."),
    NO_REFRESH_TOKEN_IN_COOKIES(HttpStatus.BAD_REQUEST, "갱신 토큰이 존재하지 않습니다."),

    // 401
    UNINTENDED_PRINCIPAL(HttpStatus.UNAUTHORIZED, "액세스 토큰 내에 의도한 값이 없습니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "액세스 토큰이 만료되었습니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "갱신 토큰이 만료되었습니다."),
    INVALID_FORMAT_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "액세스 토큰의 형식이 유효하지 않습니다."),
    INVALID_FORMAT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "갱신 토큰의 형식이 유효하지 않습니다."),
    UNSUPPORTED_FORMAT_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 액세스 토큰의 형식입니다."),
    UNSUPPORTED_FORMAT_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "지원하지 않는 갱신 토큰의 형식입니다."),
    EMPTY_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "액세스 토큰이 비어있습니다."),
    EMPTY_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "갱신 토큰이 비어있습니다."),
    INVALID_SIGNATURE_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "액세스 토큰의 서명이 유효하지 않습니다."),
    INVALID_SIGNATURE_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "갱신 토큰의 서명이 유효하지 않습니다."),

    ;
    private final HttpStatus status;
    private final String message;
}
