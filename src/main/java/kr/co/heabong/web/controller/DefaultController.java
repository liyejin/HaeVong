package kr.co.heabong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.UserService;
import kr.co.heabong.web.service.VolCategoryService;

@Controller
@RequestMapping("/")
public class DefaultController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;
//	@Autowired
//	private VolCategoryService volCategoryService;

	@GetMapping("/modal")
	public String getM(Model model) {
//		model.addAttribute();
		return "map_apply_modal";
	}

	// 테스트겸 만든거
	@GetMapping("/test")
	public String getTest(Model model) {
//		model.addAttribute();
		return "map_apply_modal";
	}

	// 테스트겸 만든거 (포스트 매핑)

	@ResponseBody
	@PostMapping("test1")
	public String getTest21(User user) {

		return "Submitted Data: " + user.toString();
	}

	// 메인
	@GetMapping("/")
	public String getIndex(Model model) {
//		model.addAttribute();
		return "index";
	}

	// 사진 움직이는 페이지
	@GetMapping("login")
	public String getLogin() {

		return "login";
	}

	// 로그인 페이지

	@ResponseBody
	@PostMapping("signin")
	public String getTest1(Model model) {
//		model.addAttribute();

		return "Submitted Data: " + model.toString();
	}

	// 개인 로그인
	@GetMapping("user_signin")
	public String getSignIn() {

		return "user_signin";
	}

	@PostMapping("user_signin")
	public String setSignIn(String uid, String pwd) {
		System.out.println(uid);
		System.out.println(pwd);

		if (!userService.isValid(uid, pwd))
			return "redirect:/user_signin?error";
		
		return "redirect:/";
	}

	@GetMapping("user_signup")
	public String getUserSignUp(Model model) {

		return "user_signup";
	}

	@PostMapping("complete_user_signup")
	public String setUserSignUp(User user, Model model) {
		// model.addAttribute();

		return "complete_user_signup" + user.toString();
	}

	// 기관 로그인
	@GetMapping("org_signin")
	public String getOrgSignin() {

		return "org_signin";
	}

	// 기관 로그인
	@PostMapping("org_signin")
	public String setOrgSignin(String regNum, String pwd , HttpSession session) {
		System.out.println(regNum);
		System.out.println(pwd);

		if (!orgService.isValid(regNum, pwd))
			return "redirect:/org_signin?error";
		
		
		Org org = orgService.getByRegNum(regNum);
		session.setAttribute("org", org);
		
		return "redirect:/org/main";
	}
	
	


	// 기관 가입
	@GetMapping("org_signup")
	public String getOrgSignup() {

		return "org_signup";
	}

	// 기관 비밀번호 찾기
	@GetMapping("org_find_pwd")
	public String getOrgFindPwd() {

		return "org_find_pwd";
	}

	// 기관 비밀번호 찾기
	@GetMapping("user_find_pwd")
	public String getUserFindPwd() {

		return "user_find_pwd";
	}

	// 카테고리 목록 페이지
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