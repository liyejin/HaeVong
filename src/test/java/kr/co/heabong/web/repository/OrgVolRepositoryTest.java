package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.OrgVol;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrgVolRepositoryTest {
	@Autowired
	private OrgVolRepository repository;

	// @Test
	void testFindAll() {
		List<OrgVol> list = repository.findAll();
		System.out.println(list);
	}

//	@Test
	void testFindByOffsetAndSize() {
		List<OrgVol> list = repository.findByOffsetAndSize(0, 6);
		System.out.println(list);
	}

	// @Test
	void testSave() {
		OrgVol orgVol = OrgVol.builder().title("test").date("2023-06-22").capacity(12).content("content")
				.roadAddress("road_address").address("address").orgId(1).districtId(1).metropolId(1).volCategoryId(1)
				.build();
		repository.save(orgVol);
	}

	// @Test
	void testUpdate() {
		OrgVol orgVol = OrgVol.builder().id(2).title("test").date("2023-06-22").capacity(12).content("content")
				.roadAddress("road_address").address("address").orgId(1).districtId(1).metropolId(1).volCategoryId(1)
				.build();
		repository.update(orgVol);
	}

	// @Test
	void testDelete() {
		OrgVol orgVol = OrgVol.builder().id(2).title("test").date("2023-06-22").capacity(12).content("content")
				.roadAddress("road_address").address("address").orgId(1).districtId(1).metropolId(1).volCategoryId(1)
				.build();
	}

	// @Test
	void testFindById(int id) {
		OrgVol orgVol = repository.findById(id);
		System.out.println(orgVol);
	}

	@Test
	void testFindByOrgIdAndStatus() {
		List<OrgVol> list = repository.findByOrgIdAndStatus(1, "over");
		System.out.println(list);
		System.out.printf("size : %d\n", list.size());
	}

	// @Test
	void testFindOrgVolListByCategoryId() {
		List<OrgVol> list = repository.FindOrgVolListByCategoryId(1);
	}

	@Test
	void testOrgVolListBySearch() {
		List<OrgVol> list = repository.FindOrgVolListBySearch(1, "네이버");
		System.out.println(list);
	}

	@Test
	void testFindOrgVolListByCategoryId(int categoryId) {
		List<OrgVol> list = repository.FindOrgVolListByCategoryId(categoryId);
	}

	@Test
	void testDelete1() {
		repository.delete(65);
	}

}
