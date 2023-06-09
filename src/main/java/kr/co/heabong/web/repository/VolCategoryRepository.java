package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.entity.VolCategory;
@Mapper
public interface VolCategoryRepository {
	
	List<VolCategory> findAll();
	List<VolCategory> findByOffsetAndSize(int offset, int size);
	List<OrgVol> findByCategoryMainPost(int categoryId);
	int save(UserVol userVol);
	
	int update(VolCategory volCategory);
	int delete(int id);
	VolCategory findById(int id);
	
}
