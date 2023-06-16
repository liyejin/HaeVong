package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MetroRepositoryTest {
	
	@Autowired
		private MetroRepository repository;

	@Test
	void testFindByName() {
		
		int MetropolId = repository.findByName("서울");
		System.out.println(MetropolId);
	}

}
