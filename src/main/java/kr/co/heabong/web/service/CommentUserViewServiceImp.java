package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Literal;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.CommentUserView;
import kr.co.heabong.web.repository.CommentUserViewRepository;
@Service
public class CommentUserViewServiceImp implements CommentUserViewService {
	@Autowired
	CommentUserViewRepository repository;
	@Override
	public List<CommentUserView> getListByPostId(int postId) {
		List<CommentUserView> list = repository.findByPostId(postId);
		return list;
		
	}
	@Override
	public List<CommentUserView> getReplyListByParentId(int parentId) {
		List<CommentUserView> list = repository.findByParentId(parentId);
		return list;
	}

}
