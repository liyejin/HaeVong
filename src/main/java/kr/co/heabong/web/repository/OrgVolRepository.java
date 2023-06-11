package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.OrgVol;

@Mapper
public interface OrgVolRepository {
	
	
	List <OrgVol> findAll();
	List <OrgVol> findByAddress(String address);
}
