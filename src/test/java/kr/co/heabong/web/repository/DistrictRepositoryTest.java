package kr.co.heabong.web.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.District;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DistrictRepositoryTest {

	@Autowired
	private DistrictRepository repo;

	@Test
	void testFindAll() {
		List<District> ls = repo.findAll();
		System.out.println(ls);
	}
	@Test
	void testFindByName() {
//		int districtId = repo.findByName("마포구");
//		System.out.println(districtId);
	}

}
