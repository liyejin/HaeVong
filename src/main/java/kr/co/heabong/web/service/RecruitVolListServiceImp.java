package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.repository.UserRepository;
@Service
public class RecruitVolListServiceImp implements RecruitVolListService{
	@Autowired
	UserRepository repository; 
	
	@Override
	public List<User> getlist() {
		List<User>list = null;
		return list;
	}

	@Override
	public User get() {
		
		return null;
	}

}
