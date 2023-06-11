package kr.co.heabong.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.UserVol;

@Mapper
public interface UserVolRepository {


	UserVol findAll();
	UserVol findById(int id);
	int save(UserVol userVol);
	int update(UserVol userVol);
	int delete(int id);


}
