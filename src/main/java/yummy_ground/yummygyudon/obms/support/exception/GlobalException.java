package yummy_ground.yummygyudon.obms.support.exception;

import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;

public class GlobalException extends BaseException{
    public GlobalException(GlobalError errorCode) {
        super(errorCode);
    }
}
