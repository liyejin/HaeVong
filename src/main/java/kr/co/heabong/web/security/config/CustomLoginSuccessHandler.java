package kr.co.heabong.web.security.config;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		clearSession(request);

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		String prevPage = (String) request.getSession().getAttribute("prevPage");
		if (prevPage != null) {
			request.getSession().removeAttribute("prevPage");
		}

		// 기본 URI
		String uri = "/";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			System.out.println("0 : " + authority.getAuthority());
			if (savedRequest != null) {

				uri = savedRequest.getRedirectUrl();

			} else if (prevPage != null && !prevPage.equals("")) {
				// 회원가입 - 로그인으로 넘어온 경우 "/"로 redirect
				// if (prevPage.contains("/login") || prevPage.contains("/user_signup")) {

				System.out.println("1 : " + authority.getAuthority());
				if ("ROLE_USER".equals(authority.getAuthority())) {
					System.out.println("2 : " + authority.getAuthority());
					response.sendRedirect("/");
					return;
				} else if ("ROLE_ORG".equals(authority.getAuthority())) {
					System.out.println("2 : " + authority.getAuthority());
					response.sendRedirect("/org/main");
					return;
				}
				// }
				else {
					uri = prevPage;
				}
			}

		}
		redirectStrategy.sendRedirect(request, response, uri);

	}

	// 로그인 실패 후 성공 시 남아있는 에러 세션 제거
	protected void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
}