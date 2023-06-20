package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getList() {
		List<User> list = repository.findAll();

		return list;
	}

	@Override
	public User getUserName(int id) {
		User userName = repository.findByUserName(id);
		return userName;
	}

	@Override
	public User findByUserPhoto(int id) {
		User profilePhoto = repository.findByUserPhoto(id);
		return profilePhoto;
	}

	@Override
	public void setUser(User user) {
		repository.save(user);
	}

	@Override
	public boolean isValid(String uid, String pwd) {
		User user = repository.findByUid(uid);

		if (user == null)
			return false;
		else if (!user.getPwd().equals(pwd))
			return false;

		return true;
	}

	@Override
	public User getByUid(String uid) {
		User user = repository.findByUid(uid);
		return user;
	}

	// My page
	@Override
	public User getUserInfoById(int id) {
		User user = repository.findUserInfoById(id);
		return user;
	}

}
