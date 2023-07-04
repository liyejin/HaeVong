package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.CommentUserView;

@Mapper
public interface CommentUserViewRepository {
	List<CommentUserView> findByPostId(int postId);
	List<CommentUserView> findByParentId(int parentId);
	int replyCount(int id);

}
