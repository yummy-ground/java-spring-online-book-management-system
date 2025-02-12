package yummy_ground.yummygyudon.obms.support.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import yummy_ground.yummygyudon.obms.support.code.SuccessCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum RentalSuccess implements SuccessCode {
    // 200
    GET_RENTAL_STATUS(HttpStatus.OK, "사용자 조회를 성공했습니다."),

    // 201
    REGISTER_RENTAL(HttpStatus.CREATED, "사용자 조회를 성공했습니다."),
    ;
    private final HttpStatus status;
    private final String message;
}
