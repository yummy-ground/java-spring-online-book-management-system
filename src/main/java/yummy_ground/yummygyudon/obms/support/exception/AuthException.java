package yummy_ground.yummygyudon.obms.support.exception;

import yummy_ground.yummygyudon.obms.support.code.ErrorCode;

public class AuthException extends BaseException {

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
