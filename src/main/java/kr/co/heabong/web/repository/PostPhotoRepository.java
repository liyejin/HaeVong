package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.VolCategory;

@Mapper
public interface PostPhotoRepository {
	List<PostPhoto> findByMyPostPhoto(int uid);

	List<VolCategory> findAll();

}
