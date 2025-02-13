package yummy_ground.yummygyudon.obms.system.handler;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseResponse<?> errorResponse = BaseResponse.of(GlobalError.UNAUTHORIZED_REQUEST.getMessage());
        String jsonResponse = objectMapper.writeValueAsString(errorResponse);

        response.setStatus(GlobalError.UNAUTHORIZED_REQUEST.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

}
