package yummy_ground.yummygyudon.obms.support.constant;

import java.util.Set;

public final class RequestConstant {
    private static final String API_PREFIX = "/api/v1";

    public static final String AUTH_URI_PREFIX = API_PREFIX + "/auth";
    public static final String REISSUE_URI_PREFIX = API_PREFIX + "/auth/refresh";
    public static final String API_DOCS_URI_PREFIX = "/api-docs";
    public static final String ACTUATOR_URI_PREFIX = "/actuator";
    public static final String ERROR_URI_PREFIX = "/error";
    public static final Set<String> WHITE_URI_PREFIX = Set.of(
            AUTH_URI_PREFIX, REISSUE_URI_PREFIX, API_DOCS_URI_PREFIX, ACTUATOR_URI_PREFIX, ERROR_URI_PREFIX
    );
}
