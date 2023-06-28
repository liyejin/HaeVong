package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.UserApplyView;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserApplyViewRepositoryTest {
	
	 @Autowired 
	 private UserApplyViewRepository repository;

	@Test
	void testFindAll() {
		
		List<UserApplyView> list = repository.findAll();
		System.out.println(list);
	
	}

	@Test
/*	void testFindById() {
		List<UserApplyView> list = repository.findById(2);
		System.out.println(list);
	}*/

}
