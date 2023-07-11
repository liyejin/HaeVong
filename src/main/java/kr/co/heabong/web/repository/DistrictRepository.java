package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.District;

@Mapper
public interface DistrictRepository {
	public List<District> findAll();
	int findByName(String name,int metropolId);//district ID 찾기
	String findNameById(int districtId,int metropolId);
	
}
