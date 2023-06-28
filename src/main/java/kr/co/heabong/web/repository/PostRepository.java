package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Post;

@Mapper
public interface PostRepository {
	List<Post> findAll();

	List<Post> findByUserId(int userId);

	List<Post> findByCategoryId(int categoryId);

	int save(Post post);

	int update(Post post);

	int delete(int id);

}
