package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.ApplyOrgVol;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ApplyOrgVolRepositoryTest {

	@Autowired
	private ApplyOrgVolRepository repository;

	@Test
	void testFindAll() {
		List<ApplyOrgVol> list = repository.findAll();
		System.out.println(list);
	}

	@Test
	void testFindByOrgVolId() {
		List<ApplyOrgVol> list = repository.findByOrgVolId(1);
		System.out.println(list);
	}

	@Test
	void testSave() { // 테스트는 안되고 롤백되는 거임 
		ApplyOrgVol applyOrgVol = ApplyOrgVol.builder()
				.orgVolId(5)
				.userId(7)
				.status(0)
				.build();
		repository.save(applyOrgVol);

	}

	@Test
	void testUpdate() {
		ApplyOrgVol applyOrgVol = ApplyOrgVol.builder()
				.orgVolId(5)
				.userId(7)
				.status(0)
				.build();
		repository.update(applyOrgVol);
	
	}

	@Test
	void testDelete() {
		ApplyOrgVol applyOrgVol = ApplyOrgVol.builder()
				.orgVolId(1)
				.userId(1)
				.status(0)
				.build(); // new 해주고 
		repository.delete(applyOrgVol); //생성된 객체를 넣었다. 
	}

}
