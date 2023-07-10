package kr.co.heabong.web.security.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.repository.UserRepository;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	@Autowired
	UserRepository userRepository ;
	@Autowired
	HttpSession httpSession;
	
	@Override
	public CustomOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		// TODO Auto-generated method stub
//	
//		
//		
//		OAuth2UserService delegate = new DefaultOAuth2UserService();
//		
//		CustomOAuth2User oAuth2User = (CustomOAuth2User) delegate.loadUser(userRequest);
//		
//		String registrationId = userRequest.getClientRegistration().getRegistrationId();
//		
//		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//		
//		String email = null;
//		String name = null;
//		Map<String , Object> response = oAuth2User.getAttributes();
//		
//		CustomOAuth2User oAuth2User = new CustomOAuth2User(response);
//		
//		if(registrationId.equals("naver")) {
//			Map<String , Object> hash = (Map<String , Object>) response.get("response");
//			name = (String)  hash.get("name");
//			email = (String) hash.get("email");
//		}else if(registrationId.equals("google")) {
//			email = (String) response.get("email");
//			name = (String)  response.get("name");
//		}else {
//			System.out.println("zkzkdh");
//		}
//		
//		User user;
//		
////		Optional <User> optionalUser = Optional.of(userRepository.findByEmail(email));
//	
//		System.out.println("Email : "  + email);
//		System.out.println("name : "  + name);
//		System.out.println("----------------------------");
//		System.out.println(response);
//		user = userRepository.findByEmail(email);
//		
//		if(user == null) {
//			user = new User();
//			oAuth2User.setEmail(email);
//			oAuth2User.setName(name);
//			user.setEmail(email);
//			user.setName(name);
//			userRepository.save(user);
//		}
//		httpSession.setAttribute("user", user);
//		
//		
//		List<GrantedAuthority> autorities = new ArrayList<>();
//		autorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		oAuth2User.setAuthorities(autorities);
//		return oAuth2User;
		
		 // TODO Auto-generated method stub
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        String email = null;
        String name = null;
        Map<String, Object> response = oAuth2User.getAttributes();
        System.out.println(response);
        if (registrationId.equals("naver")) {
        	
        	Map<String, Object> hash = (Map<String, Object>) response.get("response");
            System.out.println(hash);
            name = (String) hash.get("name");
            email = (String) hash.get("email");
        } else if (registrationId.equals("google")) {
        	
            email = (String) response.get("email");
            name = (String) response.get("name");
        } else {
            System.out.println("카카오");
        }

        System.out.println("name = "+ name);
        System.out.println("email = "+ email);
        
        User user = null;

        user = userRepository.findByEmail(email);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        }
        httpSession.setAttribute("user", user);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomOAuth2User(response, authorities);
	}

	

}
