package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.Comment;

public interface CommentService {
	List<Comment> getListByPostId(int postId);
	List<Comment> getListByParentId(int parentId);
	Comment update(Comment comment);
	Comment addComment(Comment comment);
	int delete(int id);
}
