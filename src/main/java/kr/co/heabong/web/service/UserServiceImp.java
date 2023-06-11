package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.repository.UserRepository;
@Service
public class UserServiceImp implements UserService{
	@Autowired
		private UserRepository repository; 

	@Override
	public List<User> getList() {
		List<User>list =null;
		return list;
	}

	@Override
	public void setUser(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
	}

}
