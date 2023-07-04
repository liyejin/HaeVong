package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.heabong.web.entity.Comment;
import kr.co.heabong.web.entity.CommentUserView;
import kr.co.heabong.web.entity.Post;
import kr.co.heabong.web.entity.PostCommentLikeView;
import kr.co.heabong.web.service.CommentService;
import kr.co.heabong.web.service.CommentUserViewService;
import kr.co.heabong.web.service.PostService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	@Autowired
	PostService service;
	@Autowired
	CommentUserViewService commentUserViewService;
	@GetMapping("/")
	public String community() {
		
		return "community_main";
	}
	
	@GetMapping("/my")
		public String my() {
		return "user/my_post_list.html";
	}
	
	@GetMapping("post_detail")
		public String detail(@RequestParam("id") int id, Model model) {
		PostCommentLikeView postCommentLikeViewByPostId = service.getPostCommentLikeViewByPostId(id);
		List<CommentUserView> commentList = commentUserViewService.getListByPostId(id);
		model.addAttribute("post", postCommentLikeViewByPostId);
		model.addAttribute("commentList",commentList);
		System.out.println(commentList);
		return "post_detail";
	}
}
