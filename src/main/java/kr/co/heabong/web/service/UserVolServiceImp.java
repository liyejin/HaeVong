package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.repository.UserVolRepository;

@Service
public class UserVolServiceImp implements UserVolService {

	@Autowired
	UserVolRepository repository;

	@Override
	public List<UserVol> getList() {
		List<UserVol> list = repository.findAll();
		return list;
	}

	@Override
	public List<UserVol> getList(int userId, String status) {
		List<UserVol> list = repository.findByUserIdAndStatus(userId, status);
		return list;
	}

	@Override
	public UserVol getUserVol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserVol() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserVol> getMyApplyUserVolList(int userId) {
		List<UserVol> list = repository.FindMyApplyUserVolListById(userId);
		return list;
	}

}
