package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.VolCategory;
@Mapper
public interface VolCategoryRepository {
	List<VolCategory> findAll();
	List<VolCategory> findByOffsetAndSize(int offset, int size);
	int save(VolCategory volCategory);
	int update(VolCategory volCategory);
	int delete(int id);
	VolCategory findById(int id);
}
