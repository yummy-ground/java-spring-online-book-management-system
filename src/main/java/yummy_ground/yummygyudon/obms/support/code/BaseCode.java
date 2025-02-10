package yummy_ground.yummygyudon.obms.support.code;

import org.springframework.http.HttpStatus;

interface BaseCode {
    HttpStatus getStatus();
    String getMessage();
}
