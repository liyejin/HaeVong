package kr.co.heabong.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.Comment;
import kr.co.heabong.web.entity.CommentUserView;
import kr.co.heabong.web.service.CommentService;
import kr.co.heabong.web.service.CommentUserViewService;

@RestController("apiCommentController")

@RequestMapping("api/comments")
public class CommentController {
	@Autowired
	CommentService service;
	@Autowired
	CommentUserViewService commentUserViewService;
	
	@PostMapping
	public ResponseEntity<Object> insertComment(@RequestBody Comment comment) {
		Comment newOne = service.addComment(comment);
		CommentUserView newCommentUserView = null;
		
		if(newOne == null) {
			return new ResponseEntity<Object> ("저장 실패",HttpStatus.BAD_REQUEST);
		}
		else {
			newCommentUserView=commentUserViewService.getLastOne();
		}
		return new ResponseEntity<Object>(newCommentUserView,HttpStatus.OK);	
	}
}
