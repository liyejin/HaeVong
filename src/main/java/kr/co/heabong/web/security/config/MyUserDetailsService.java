package kr.co.heabong.web.security.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.UserService;

public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username을 보고 기관인지 유저인지 판단
		if (username.matches("\\d+") == true) {
			Org org = orgService.getByRegNum(username);
			MyUserDetails userDetails = new MyUserDetails();
			userDetails.setUsername(username);
			userDetails.setName(org.getName());
			userDetails.setId(org.getId());
			userDetails.setEmail(org.getEmail());
			userDetails.setPassword(org.getPwd());
			List<GrantedAuthority> autorities = new ArrayList<>();
			autorities.add(new SimpleGrantedAuthority("ROLE_ORG"));
			userDetails.setAuthorities(autorities);
			return userDetails;
		} else {
			User user = userService.getByUid(username);
			MyUserDetails userDetails = new MyUserDetails();
			userDetails.setUsername(username);
			userDetails.setId(user.getId());
			userDetails.setEmail(user.getEmail());
			userDetails.setPassword(user.getPwd());
			List<GrantedAuthority> autorities = new ArrayList<>();
			autorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			userDetails.setAuthorities(autorities);
			return userDetails;
		}
	}

}
