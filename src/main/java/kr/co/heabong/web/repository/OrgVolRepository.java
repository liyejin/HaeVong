package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.OrgVol;

@Mapper
public interface OrgVolRepository {
	List<OrgVol> findAll();
	List<OrgVol> findByOffsetAndSize(int offset, int size);
	List<OrgVol> findByOrgIdAndStatus(int orgId, String status);
	List<OrgVol> findByAddress(String address);
	int save(@Param("orgVol")OrgVol orgVol);
	int update(@Param("orgVol")OrgVol orgVol);
	int delete(int id);
	OrgVol findById(int id);
}
