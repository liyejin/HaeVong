package kr.co.heabong.web.security.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class CustomLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 실패 메시지 가져오기
        String errorMessage = getErrorMessage(exception);
        // 실패 메시지를 로그에 기록하거나 추가적인 실패 처리 로직 수행

        // 실패 메시지를 클라이언트에게 전달
        response.sendRedirect("/user_signin?error");
    }

    private String getErrorMessage(AuthenticationException exception) {
        String errorMessage;

        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            errorMessage = "Invalid username or password";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Your account has been disabled";
        } else {
            errorMessage = "Authentication failed";
        }

        return errorMessage;
    }

	
}