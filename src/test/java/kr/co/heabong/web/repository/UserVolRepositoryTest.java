package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
		List<UserVol> list = repository.findAll();
		System.out.println(list);
	}

	//@Test
	void testFindById() {

		List<UserVol> list = repository.findByOffsetAndSize(0,6);
		System.out.println(list);
	}

	//@Test 
	void testSave() {
		UserVol userVol = UserVol.builder()
						 .title("이름")
						 .date("2022-09-19")
						 .capacity(1)
						 .place("아아")
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
	
	//@Test 
	void testUpdate() {
		UserVol userVol = UserVol.builder()
						 .title("이름")
						 .date("2022-09-19")
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

		repository.update(userVol);
	}
	
	//@Test
	void testDelete() {
		repository.delete(1);
	}
	
	@Test
	void testFindByUserIdAndStatus() {
		List<UserVol> list = repository.findByUserIdAndStatus(1, "over");
		System.out.println(list);
		System.out.printf("size : %d\n", list.size());
		
	}

}