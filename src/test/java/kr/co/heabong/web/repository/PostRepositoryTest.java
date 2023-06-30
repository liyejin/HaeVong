package kr.co.heabong.web.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import kr.co.heabong.web.entity.Post;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {
	@Autowired
	PostRepository repository;

	// @Test
	void testFindAll() {
		List<Post> findAll = repository.findAll();
		System.out.println(findAll);
	}

	// @Test
	void testFindByUserId() {
		List<Post> findByUserId = repository.findByUserId(1);
		System.out.println(findByUserId);
	}

	// @Test
	void testFindByCategoryId() {
		List<Post> findByCategoryId = repository.findByCategoryId(1);
		System.out.println(findByCategoryId);
	}

	// @Test
	void testSave() {
		Date date = new Date();
		Post post = Post.builder().build();
		int result = repository.save(post);
		System.out.println(result);
	}

	// @Test
	void testUpdate() {
		Post post = Post.builder().build();
		int result = repository.update(post);
		System.out.println(result);

	}

	@Test
	void testDelete() {
		int result = repository.delete(14);
		System.out.println(result);

	}

}
