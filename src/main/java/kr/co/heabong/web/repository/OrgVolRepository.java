package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.heabong.web.entity.OrgVol;

@Mapper
public interface OrgVolRepository {
	List<OrgVol> findAll();
	List<OrgVol> findByOffsetAndSize(int offset, int size);
	int save(@Param("orgVol")OrgVol orgVol);
	int update(@Param("orgVol")OrgVol orgVol);
	int delete(int id);
	OrgVol findById(int id);
}
