package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.VolCategory;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VolCategoryRepositoryTest {
	
	@Autowired
	private VolCategoryRepository repository;
	
	//@Test
	void testFindAll() {
		List<VolCategory> list = repository.findAll();
		System.out.println(list);
	}
	
	//@Test
	void testFindByOffsetAndSize() {
		List<VolCategory> list = repository.findByOffsetAndSize(1,3);
		System.out.println(list);
	}

	//@Test
	void testSave() {
		fail("Not yet implemented");
	}

	//@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	//@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	//@Test
	void testFindById() {
		fail("Not yet implemented");
	}
	
	@Test
	void testfindByCategoryMainPost() {
		List<OrgVol> list = repository.findByCategoryMainPost(4);
	}
	

}
