package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.VolCategory;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostPhotoRepositoryTest {
	@Autowired
	private PostPhotoRepository postPhotoRepository;
	
	@Test
	void testFindAll() {
		List<VolCategory> list = postPhotoRepository.findAll();
		System.out.println(list);
	}
	
	//@Test
//	void testFindByMyPostPhoto() {
//		List<PostPhoto> myPostUrl = postPhotoRepository.findByMyPostPhoto();
//		System.out.println(myPostUrl);
//	}
	
}
