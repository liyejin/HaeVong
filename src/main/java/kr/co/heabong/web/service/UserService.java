package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.User;

public interface UserService {
	List<User> getList();
	
	void setUser(User user);
	boolean isValid(String uid, String pwd);
	
	
}
