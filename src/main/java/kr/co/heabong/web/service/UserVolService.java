package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.UserVol;

public interface UserVolService {

	List<UserVol> getList();
	List<UserVol> getList(int userId,String status);
	UserVol getUserVol();
	
	
	void setUserVol();
}
