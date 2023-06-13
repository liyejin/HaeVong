package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Wish;

@Mapper
public interface WishRepository {
	List<Wish> findByUserId(int userId);
	List<Wish> findByOrgVolId(int orgVolId);
	Wish findByUserIdAndOrgVolId(int userId, int orgVolId);
	int count(int orgVolId);
	int update(Wish wish);
	int save(Wish wish);
	int delete(Wish wish);
	
}
