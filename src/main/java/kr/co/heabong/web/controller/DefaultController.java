package kr.co.heabong.web.controller;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.security.config.HeabongConfig;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.PostPhotoService;
import kr.co.heabong.web.service.UserService;
import kr.co.heabong.web.service.UserVolService;
import kr.co.heabong.web.service.VolCategoryService;

@Controller
@RequestMapping("/")
public class DefaultController {
	@Autowired
	VolCategoryService volCategoryService;
	@Autowired
	UserService userService;
	@Autowired
	UserVolService userVolService;
	@Autowired
	OrgVolService orgVolService;
	@Autowired
	PostPhotoService postPhotoService;
	@Autowired
	OrgService orgService;
	@Autowired
	HeabongConfig config;
	// @Autowired
	// PostService postService;

	@GetMapping("/modal")
	public String getM(Model model) {
		// model.addAttribute();
		return "map_apply_modal";
	}

	// 테스트겸 만든거
	@GetMapping("/test")
	public String getTest(Model model) {
		// model.addAttribute();
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
		List<OrgVol> list = orgVolService.getList();
		model.addAttribute("orgVOl",list);
		return "index";
	}

	// 사진 움직이는 페이지
	@GetMapping("login")
	public String getLogin() {

		return "login";
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
	
	@PostMapping("user_signup")
	public String setUserSignUp(Model model, User user) {

//		String idNum = user.getIdentityNumber();
//		String[] idArr  = idNum.split("-");
//		if(idArr)
//		
//		user.setIdentityNumber()
		
		System.out.println(user);
		user.setIdentityNumber(user.getBirthDate()+user.getGender());
		if(user.getGender()%2 == 1) 
			user.setGender(1);
		else
			user.setGender(2);
		
		 LocalDateTime currentDateTime = LocalDateTime.now();
		 int year = currentDateTime.getYear();
	     int twoDigitYear = year % 100; // 년도의 두 자리수 계산
	     
	     String frontTwoDigits = user.getBirthDate().substring(0, 2); // 앞 두 자리만 잘라내기
	     int result = Integer.parseInt(frontTwoDigits); // int로 변환
		
	     
	     user.setAge(twoDigitYear-result+100);
	     user.setPwd(config.passwordEncoder().encode(user.getPwd()));
		System.out.println("changed : " + user);
		userService.setUser(user);
		
		
		
		return "redirect:/login";
	}
	
	// 기관 로그인
	@GetMapping("org_signin")
	public String getOrgSignin() {

		return "org_signin";
	}

	// 기관 로그인
	@PostMapping("org_signin")
	public String setOrgSignin(String regNum, String pwd, HttpSession session) {
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
	
	
	// 기관 비밀번호 찾기(포스트)
	@PostMapping("user_find_pwd")
	public String setUserPwd(String uid, String pwd) {

		System.out.println(uid + " " + pwd);
		
		return "redirect:/user_signin";
	}

	// 카테고리 목록 페이지
	@GetMapping("category_main")
	public String getVol_category(Model model) {
		// 카테고리
		List<VolCategory> cateList = volCategoryService.getCateList();
		model.addAttribute("cateList", cateList);
		// 게시글
		List<OrgVol> mainCateList = volCategoryService.getMainCategoryList();
		model.addAttribute("mainCateList", mainCateList);
		//사진
		List<String> photoList = orgVolService.getPhotoList(1);
		model.addAttribute("photoList",photoList);
		return "category_main";
	}

	// <My Page> : default, user 페이지 따로 있습니다.
	// profile section ----------------------------
	@GetMapping("mypage")
	public String getMypage(Model model) {

		return "mypage_default";
	}

	@GetMapping("vol_list")
	public void getUserVolList(
			@RequestParam(name = "id", required = false) int id,
			Model model) {
		model.addAttribute("u", model);

	}
	
	// 분야별 봉사 검색 기능
	@GetMapping("orgvol")
	public String getOrgVolListByCategory(
			@RequestParam(name = "cid", required = true) Integer categoryId,
			@RequestParam(name = "sk", required = false) String serchKeyword,
			Model model) {
		List<OrgVol> orgVolList = null;
		
//		카테고리 전체 가져오기
		List<VolCategory> volCategory = volCategoryService.getCateList(); 

		if (serchKeyword == null&categoryId==1)
			orgVolList = orgVolService.getList();
		else   if (serchKeyword == null& categoryId !=null )
			orgVolList = orgVolService.getOrgVolListByCategoryId(categoryId);
		else if (serchKeyword != null)
			orgVolList = orgVolService.getOrgVolListBySearch(categoryId, serchKeyword);

		model.addAttribute("orgVolList", orgVolList);
		model.addAttribute("cid", categoryId);
		model.addAttribute("volCategory",volCategory);
		
		return "org_vol_by_category";
	}
	
	
	

}