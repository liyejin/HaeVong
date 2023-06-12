package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.User;
@Service
public interface UserService {
	List<User> getlist();
	User getUserName(int id);
	User findByUserPhoto(int id);
	
	

}
