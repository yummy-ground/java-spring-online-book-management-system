package yummy_ground.yummygyudon.obms.support.constant;

import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class JwtConstant {
    public static final String TYPE = "JWT";
    public static final String ALGORITHM = "HS256";
}
