package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.District;

@Mapper
public interface DistrictRepository {
	@Select("SELECT * FROM district;")
	public List<District> findAll();
}
