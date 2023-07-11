package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.Wish;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WishRepositoryTest {
	@Autowired
	private WishRepository repository;
//	@Test
	void testFindByUserId() {
	List<Wish> list = repository.findByUserId(1);
	System.out.println(list);
	}

//	@Test
	void testFindByOrgVolId() {
		List<Wish> list = repository.findByOrgVolId(1);
		System.out.println(list);
	}

//	@Test
	void testFindByUserIdAndOrgVolId() {
		Wish wish = repository.findByUserIdAndOrgVolId(1, 1);
		System.out.println(wish);
	}

//	@Test
	void testCount() {
		int count = repository.count(3);
		System.out.println(count);
	}

//	@Test
	void testSave() {
		Wish wish = Wish.builder().userId(6).orgVolId(6).build();
		int save = repository.save(wish);
		System.out.println(save);
	}

	@Test
	void testDelete() {
		Wish wish = repository.findByUserIdAndOrgVolId(1, 1);
		int i = repository.delete(wish);
		System.out.println(i);
	}

}
