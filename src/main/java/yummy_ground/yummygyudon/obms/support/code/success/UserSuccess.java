package yummy_ground.yummygyudon.obms.support.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.SuccessCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum UserSuccess implements SuccessCode {
    // 200
    GET_USER(HttpStatus.OK, "사용자 조회를 성공했습니다."),
    GET_USERS(HttpStatus.OK, "사용자 목록 조회를 성공했습니다."),

    // 201
    REGISTER_USER(HttpStatus.CREATED, "사용자 등록을 성공했습니다."),

    ;
    private final HttpStatus status;
    private final String message;
}
