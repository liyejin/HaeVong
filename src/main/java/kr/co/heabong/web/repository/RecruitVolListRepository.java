package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.User;

@Mapper
public interface RecruitVolListRepository {
	List<User>findeAll();
	List<User>findeAll(String name,String email,String identityNumber,String phoneNumber,String gender,int age,String birthDate,String nickname);
	
	User findById(int id);
	
	int save(User user);
	int update(User user);
	int delete(int id);
}
