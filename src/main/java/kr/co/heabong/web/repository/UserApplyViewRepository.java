package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.UserApplyView;


@Mapper
public interface  UserApplyViewRepository{
	
	List<UserApplyView> findAll();

	List<UserApplyView> findById(int id);

}
