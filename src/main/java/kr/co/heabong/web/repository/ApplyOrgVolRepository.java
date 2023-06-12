package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.ApplyOrgVol;

@Mapper
public interface ApplyOrgVolRepository {
	
	List<ApplyOrgVol> findAll();

	List<ApplyOrgVol>  findByOrgVolId(int orgVolId);
	
	int save(ApplyOrgVol  applyOrgVol);

	int update(ApplyOrgVol  applyOrgVol);

	int delete(ApplyOrgVol  applyOrgVol);
	

}
