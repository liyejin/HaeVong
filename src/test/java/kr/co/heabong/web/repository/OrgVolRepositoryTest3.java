package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserWishView;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrgVolRepositoryTest3 {
	
	@Autowired
	private OrgVolRepository repository;

	@Test
	void testFindViewBySearch() {
		List<UserWishView> list = repository.findViewAll(null, 2, "미혼모",null);
		System.out.println(list);
	}
	@Test
	void testFindViewByCategoryId() {
		List<UserWishView> list = repository.FindViewByCategoryId(2);
		System.out.println(list);
	}

	
	
}
