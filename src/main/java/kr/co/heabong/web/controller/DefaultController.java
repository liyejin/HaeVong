package kr.co.heabong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class DefaultController {
	
	//테스트겸 만든거
	@GetMapping("/test")
	public String getTest(Model model) {
//		model.addAttribute();
		return "test";
	}
	
	//테스트겸 만든거 (포스트 매핑)
	
		@ResponseBody
		@PostMapping("/test1")
		public String getTest1(Model model) {
//			model.addAttribute();
			return "test1";
		}
	
	//메인
	@GetMapping("/")
	public String getIndex(Model model) {
//		model.addAttribute();
		return "index";
	}
	
	//사진 움직이는 페이지
	@GetMapping("login")
	public String getLogin() {
		
		return "login";
	}
	
	
	//로그인 페이지
	@GetMapping("signin")
	public String getSignIn() {
		
		return "signin";
	}
	
	//기관 비밀번호 찾기
	@GetMapping("org_find_pwd")
	public String getOrgFindPwd() {
		
		return "org_find_pwd";
	}
	
	//기관 비밀번호 찾기
	@GetMapping("user_find_pwd")
	public String getUserFindPwd() {
		
		return "user_find_pwd";
	}
	
	//카테고리 목록 페이지
	@GetMapping("vol_category")
	public String getVol_category(Model model) {
//		model.addAttribute();
		return "vol_category";
	}
	
	@GetMapping("mypage")
	public String getMypage(Model model) {
//		model.addAttribute();
		return "mypage";
	}
	
	
	
}