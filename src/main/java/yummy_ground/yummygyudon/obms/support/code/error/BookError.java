package yummy_ground.yummygyudon.obms.support.code.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum BookError implements ErrorCode {
    // 400
    INVALID_SORT_CRITERIA(HttpStatus.BAD_REQUEST, "정렬 기준 요청값이 올바르지 않습니다."),

    // 404
    NOT_FOUND_BOOK(HttpStatus.NOT_FOUND, "해당하는 도서를 찾을 수 없습니다.")

    ;
    private final HttpStatus status;
    private final String message;
}
