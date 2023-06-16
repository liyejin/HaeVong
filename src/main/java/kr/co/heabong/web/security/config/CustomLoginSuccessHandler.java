package kr.co.heabong.web.security.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			if ("ROLE_USER".equals(authority.getAuthority())) {
				response.sendRedirect("/");
				return;
			} else if ("ROLE_ORG".equals(authority.getAuthority())) {
				response.sendRedirect("/org/main");
				return;
			}
		}
		// 기타 권한이 없는 경우 처리 로직
		response.sendRedirect("/");
	}

}
