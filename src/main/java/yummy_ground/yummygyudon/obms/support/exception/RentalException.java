package yummy_ground.yummygyudon.obms.support.exception;

import yummy_ground.yummygyudon.obms.support.code.error.RentalError;

public class RentalException extends BaseException {
    public RentalException(RentalError errorCode) {
        super(errorCode);
    }
}
