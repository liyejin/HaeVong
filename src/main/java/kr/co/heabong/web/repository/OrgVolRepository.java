package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.heabong.web.entity.OrgVol;

@Mapper
public interface OrgVolRepository {
	List<OrgVol> findAll();
	List<OrgVol> findByOffsetAndSize(int offset, int size);
	List<OrgVol> findByOrgIdAndStatus(int orgId, String status);
	int save(OrgVol orgVol);
	int update(OrgVol orgVol);
	int delete(int id);
	OrgVol findById(int id);
}
