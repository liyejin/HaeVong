package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Comment;

@Mapper
public interface CommentRepository {
	List<Comment> findByPostId(int postId);
	List<Comment> findByParentId(int parentId);
	int replyCount(int id);
	int update(Comment comment);
	int save(Comment comment);
	int delete(int id);
	Comment getLastComment();
	Comment getLastEdited();
}
