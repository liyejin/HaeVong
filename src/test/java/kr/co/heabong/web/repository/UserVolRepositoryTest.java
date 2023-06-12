package kr.co.heabong.web.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.UserVol;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserVolRepositoryTest {

	@Autowired
	private UserVolRepository repository;


	//@Test 
	void testFindAll() {
//		List<UserVol> list = repository.findAll();
	}

	@Test
	void testFindById() {

		UserVol userVol = repository.findById(2);
		System.out.println(userVol);
	}

	@Test void testSave() {
		UserVol userVol = UserVol.builder()
						 .id(1)
						 .title("이름")
						 .regDate(null)
						 .date(null)
						 .capacity(1)
						 .place("")
						 .roadAddress("백범로")
						 .address("서울시 마포구")
						 .userId(1)
						 .districtId(1)
						 .metropolId(1)
						 .volCategoryId(1)
						 .content("제목입니다")
						 .build();

		repository.save(userVol);
	}

//	@Test void testUpdate() {
//	}

	//@Test
	void testDelete() {
//		UserVol userVol = UserVol;
	}

}
