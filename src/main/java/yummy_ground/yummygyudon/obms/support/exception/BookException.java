package yummy_ground.yummygyudon.obms.support.exception;

import yummy_ground.yummygyudon.obms.support.code.error.BookError;

public class BookException extends BaseException {
    public BookException(BookError errorCode) {
        super(errorCode);
    }
}
