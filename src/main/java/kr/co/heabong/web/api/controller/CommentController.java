package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	// ParentId로 ReplyList가져오기
	
	@GetMapping("{id}/replies")
	public ResponseEntity<Object> getReplyList(@PathVariable int id){
		List<CommentUserView> list = commentUserViewService.getReplyListByParentId(id);
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
	
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
	
	@PutMapping
	public ResponseEntity<Object> updateComment(@RequestBody Comment comment) {
		Comment edited = service.update(comment);
		CommentUserView newCommentUserView = null;
		
		if(edited == null) {
			return new ResponseEntity<Object> ("저장 실패",HttpStatus.BAD_REQUEST);
		}
		else {
			newCommentUserView=commentUserViewService.getLastEdited();
		}
		return new ResponseEntity<Object>(newCommentUserView,HttpStatus.OK);	
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") int id) {
		int result = service.delete(id);
		if (result == 0)
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
}
