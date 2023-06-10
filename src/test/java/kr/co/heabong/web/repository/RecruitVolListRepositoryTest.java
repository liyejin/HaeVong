package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.User;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecruitVolListRepositoryTest {
	@Autowired
	private UserRepository repository;
	
	@Test
	void testFindeAll() {
		List<User>list = repository.findAll();
		  System.out.println(list);
	}

	//@Test
	void testFindeAllStringStringStringStringStringIntStringString() {
		fail("Not yet implemented");
	}

	//@Test
	void testFindById() {
		User user = repository.findById(1);
		System.out.println(user);
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

}
