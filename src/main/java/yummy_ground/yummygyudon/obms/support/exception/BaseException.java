package yummy_ground.yummygyudon.obms.support.exception;

import lombok.Getter;
import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

@Getter
public abstract class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
