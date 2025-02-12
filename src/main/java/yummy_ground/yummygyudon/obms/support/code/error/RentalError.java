package yummy_ground.yummygyudon.obms.support.code.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;

import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum RentalError implements ErrorCode {
    // 404
    NOT_FOUND_RENTAL(HttpStatus.NOT_FOUND, "해당하는 대여 이력을 찾을 수 없습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
