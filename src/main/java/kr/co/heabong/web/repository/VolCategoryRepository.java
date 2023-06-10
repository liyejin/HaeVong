package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.VolCategory;

@Mapper
public interface VolCategoryRepository {
	List<VolCategory>findAll();

}
