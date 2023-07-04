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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
