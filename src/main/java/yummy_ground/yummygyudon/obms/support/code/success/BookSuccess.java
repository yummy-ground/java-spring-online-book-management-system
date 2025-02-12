package yummy_ground.yummygyudon.obms.support.code.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.SuccessCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum BookSuccess implements SuccessCode {
    // 200
    GET_BOOK(HttpStatus.OK, "도서 상세 조회를 성공했습니다."),
    GET_BOOKS(HttpStatus.OK, "도서 목록 조회를 성공했습니다."),

    // 201
    REGISTER_BOOK(HttpStatus.CREATED, "도서 등록을 성공했습니다."),
    ;
    private final HttpStatus status;
    private final String message;
}
