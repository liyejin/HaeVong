package kr.co.heabong.web.security.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomOAuth2User implements OAuth2User {
    

	
	private int id;
	private String name;
	private String email;	
	private String username; // userID, 고정일수밖에없다.
	
	private Map<String, Object> attributes;
	private List <GrantedAuthority> authorities;
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
    public CustomOAuth2User(Map<String, Object> attributes, List<GrantedAuthority> authorities2) {
        this.attributes = attributes;
        this.authorities = authorities2;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한 정보를 반환합니다.
        // 필요한 경우 커스텀 로직으로 구현합니다.
        return authorities;
    }
    @Override
    public Map<String, Object> getAttributes() {
        // 사용자의 속성 정보를 반환합니다.
        return attributes;
    }
    
    @Override
    public String getName() {
        // 사용자의 이름을 반환합니다.
    	String res = null;
    	if((String) attributes.get("name") != null) {
    		res = (String) attributes.get("name");
    	}else {
    		
	    	Map<String, Object> hash = (Map<String, Object>) attributes.get("response");
	        System.out.println(hash);
	        res = (String) hash.get("name");
	        
    	}
    	
    	return res;
    }
    
    // 추가적인 메소드나 필드를 정의할 수도 있습니다.
    // 필요에 따라 커스텀 로직을 구현합니다.
}