package yummy_ground.yummygyudon.obms.support.exception;

import yummy_ground.yummygyudon.obms.support.code.error.AuthError;

public class AuthException extends BaseException {

    public AuthException(AuthError errorCode) {
        super(errorCode);
    }
}
