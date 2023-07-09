package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Comment;
import kr.co.heabong.web.repository.CommentRepository;
@Service 
public class CommentServiceImp implements CommentService {
	@Autowired
	CommentRepository repository;
	
	@Override
	public List<Comment> getListByPostId(int postId) {
		List<Comment> list = repository.findByPostId(postId);
		return list;
	}

	@Override
	public List<Comment> getListByParentId(int parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment update(Comment comment) {
		int result = repository.update(comment);
		Comment lastEdited = null;
		if( result == 1)
			lastEdited = repository.getLastEdited();
		return lastEdited;
	}

	@Override
	public Comment addComment(Comment comment) {
		int result = repository.save(comment);
		Comment lastComment=null;
		if(result ==1) {
			lastComment = repository.getLastComment();
		}
		return lastComment;
	}

	@Override
	public int delete(int id) {
		int result = repository.delete(id);
		return result;
	}


}
