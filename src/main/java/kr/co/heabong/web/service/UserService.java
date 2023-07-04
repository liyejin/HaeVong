package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.User;

@Service
public interface UserService {
	List<User> getList();

	void setUser(User user);

	User getUserName(int id);

	User findByUserPhoto(int id);

	User getByUid(String uid);

	boolean isValid(String uid, String pwd);

	// kakao login
	boolean isValid(String uid);

	// My page
	User getUserInfoById(int id);

	User getUserName(String name);

}
