package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrgVolRepositoryTest2 {
	@Autowired
	private OrgVolRepository repository;
	
	@Test
	void testfindByOrgIdAndStatus() {
		System.out.println(repository.findByOrgIdAndStatus(1,""));
		System.out.println(repository.findByOrgIdAndStatus(2,""));
	}
	
	

}
