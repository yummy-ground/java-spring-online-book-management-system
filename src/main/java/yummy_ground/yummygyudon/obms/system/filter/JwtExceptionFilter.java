package yummy_ground.yummygyudon.obms.system.filter;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import yummy_ground.yummygyudon.obms.support.code.ErrorCode;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.exception.BaseException;


@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch(BaseException e) {
            ObjectMapper objectMapper = new ObjectMapper();
            BaseResponse<?> errorResponse = getFailureResponse(e.getErrorCode());
            String jsonResponse = objectMapper.writeValueAsString(errorResponse);

            response.setStatus(e.getErrorCode().getStatus().value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        }
    }

    private BaseResponse<?> getFailureResponse(ErrorCode errorCode) {
        return BaseResponse.of(errorCode.getMessage());
    }
}
