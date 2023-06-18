package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserVol;

@Mapper
public interface UserVolRepository {

	List<UserVol> findAll();

	UserVol findByUserId(int userId);

	int save(UserVol userVol);

	int update(UserVol userVol);

	int delete(int id);

	List<UserVol> findByOffsetAndSize(int offset, int size);

	List<UserVol> findByUserIdAndStatus(int userId, String status);

	// My page category section ----------------------------
	List<UserVol> FindMyApplyUserVolListById(int userId);

}
