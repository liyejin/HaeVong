package kr.co.heabong.web.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.co.heabong.web.service.UserService;

@Configuration
@EnableWebSecurity
public class HeabongConfig {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserService userService;
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;
	@Autowired
	private CustomLoginFailHandler customLoginFailHandler;
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/user/**").hasAnyRole("USER")
						.requestMatchers("/org/**").hasAnyRole("ORG")
						.anyRequest().permitAll())
				.formLogin(form -> form
						.loginPage("/user_signin")
						.successHandler(customLoginSuccessHandler)
						.failureHandler(customLoginFailHandler))
				.oauth2Login(oauth2 -> oauth2 // OAuth2기반의 로그인인 경우
						.loginPage("/user_signin") // 인증이 필요한 URL에 접근하면 /loginForm으로 이동
						.userInfoEndpoint(user -> user // 로그인 성공 후 사용자정보를 가져온다
						.userService(customOAuth2UserService) // 사용자정보를 처리할 때 사용한다
						)
						.successHandler(customLoginSuccessHandler) // 로그인 성공하면 "/" 으로 이동
						.failureHandler(customLoginFailHandler) // 로그인 실패 시 /loginForm으로 이동
				).logout(logout -> logout.logoutUrl("/logout")
						.clearAuthentication(true)
			            .invalidateHttpSession(true)
			            .deleteCookies("JSESSIONID")
						.logoutSuccessHandler((request, response, authentication) -> {
							String redirectUrl;
							if (authentication != null && authentication.getAuthorities().stream()
									.anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
								redirectUrl = "/user_signin";
							} else if (authentication != null && authentication.getAuthorities().stream()
									.anyMatch(authority -> authority.getAuthority().equals("ROLE_ORG"))) {
								redirectUrl = "/user_signin";
							} else {
								redirectUrl = "/error";
							}
							response.setStatus(HttpStatus.OK.value());
							response.sendRedirect(redirectUrl);
						}));

		// 하위폴더까지 전부 다 "member/**"
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}

}
