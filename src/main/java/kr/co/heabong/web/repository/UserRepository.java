package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.User;

@Mapper
public interface UserRepository {

	// List<User>findAll(String name,String email,String identityNumber,String
	// phoneNumber,String gender,int age,String birthDate,String nickname);
	List<User> findAll();

	User findById(int id);

	int save(User user);

	int update(User user);

	int delete(int id);

	User findByUserName(int id);

	User findByUserEmail(String email);

	List<User> findByMyPost();

	User findByUserPhoto(int id);

	User findByUid(String uid);

	// My page
	User findUserInfoById(int id);
	
	User findByEmail(String email);
}
