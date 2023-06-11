package kr.co.heabong.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.repository.UserVolRepository;

@Service
public class UserVolServiceImp implements UserVolService {

	@Autowired
	UserVolRepository repository;

	@Override
	public UserVol getUserVol() {
		UserVol userVol = repository.findAll();
		return userVol;
	}

	@Override
	public void setUserVol() {
		// TODO Auto-generated method stub

	}

}
