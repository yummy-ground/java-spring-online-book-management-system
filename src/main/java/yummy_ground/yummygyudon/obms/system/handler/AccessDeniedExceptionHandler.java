package yummy_ground.yummygyudon.obms.system.handler;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;

@Component
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseResponse<?> errorResponse = BaseResponse.of(GlobalError.ACCESS_DENIED_REQUEST.getMessage());
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(GlobalError.ACCESS_DENIED_REQUEST.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

}
