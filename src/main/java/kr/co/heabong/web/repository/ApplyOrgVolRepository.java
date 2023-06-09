package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.ApplyOrgVol;
import kr.co.heabong.web.entity.UserApplyView;

@Mapper
public interface ApplyOrgVolRepository {
	
	List<ApplyOrgVol> findAll();
	List<ApplyOrgVol>  findByOrgVolId(int orgVolId);
	
	List<UserApplyView> findViewAll();
	List<UserApplyView> findViewByOrgVolId(int orgVolId);


	ApplyOrgVol get(int orgVolId, int userId);

	int save(ApplyOrgVol  applyOrgVol);

	int update(ApplyOrgVol  applyOrgVol);

	int delete(ApplyOrgVol  applyOrgVol);
	
	//최신껄 가져오는 함수
	ApplyOrgVol lastOne();
	
	

}
