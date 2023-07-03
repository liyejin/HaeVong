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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
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
//	private PasswordEncoder passwordEncoder;

	// 필터를 사용하려면?필요한 설정
	// 1. 사용자 정보 설정
	// 2. 접근 가능한 도메인 설정

	// 객체 생성 방법
//	@Bean
//	public Menu menu() {
//		return new Menu();
//	}
//	
	// 권한을 위한 필터객체
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/user/**").hasAnyRole("USER")
						.requestMatchers("/org/**").hasAnyRole("ORG")
						.anyRequest().permitAll()
						)
				.formLogin(form -> form
						.loginPage("/user_signin")		
						.successHandler(customLoginSuccessHandler)
						.failureHandler(customLoginFailHandler))
				.logout(logout -> logout.logoutUrl("/logout")
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

	// @Bean
	public UserDetailsService jdbcOrgDetailsService() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		System.out.println(dataSource);

		manager.setUsersByUsernameQuery("select reg_num username, pwd password, 1 enabled from org where reg_num=?");// username,
																														// password,
																														// enabled(컬럼명
																														// 반드시
																														// 일치시켜줄것)

		manager.setAuthoritiesByUsernameQuery(
				"select o.reg_num username, r.name authority from role r join org o on o.role_id=r.id where o.reg_num=?");// username,
																															// authority
		System.out.println("기관 필터");

		return manager;
	}

	// 2. JDBC를 이용한 사용자 정보
	// @Bean
	public UserDetailsService jdbcUserDetailsService() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		System.out.println(dataSource);

//		manager.setUsersByUsernameQuery("select reg_num username, pwd password, 1 enabled from org where reg_num=?");//username, password, enabled(컬럼명 반드시 일치시켜줄것)
//		
//		manager.setAuthoritiesByUsernameQuery("select o.reg_num username, r.name authority from role r join org o on o.role_id=r.id where o.reg_num=?");//username, authority
		manager.setUsersByUsernameQuery("select uid username, pwd password, 1 enabled from user where uid=?");// username,
																												// password,
																												// enabled(컬럼명
																												// 반드시
																												// 일치시켜줄것)

		manager.setAuthoritiesByUsernameQuery(
				"select u.uid username, r.name authority from role r join user u on u.role_id=r.id where u.uid=?");// username,
																													// authority

		System.out.println("유저 필터");
		return manager;
	}

	// 3. 디렉토리 서비스를 이용한 사용자 정보

}
