package yummy_ground.yummygyudon.obms.support.exception;

import yummy_ground.yummygyudon.obms.support.code.ErrorCode;
import yummy_ground.yummygyudon.obms.support.code.error.UserError;

public class UserException extends BaseException {
    public UserException(UserError errorCode) {
        super(errorCode);
    }
}
