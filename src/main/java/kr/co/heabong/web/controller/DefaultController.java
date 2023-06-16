package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.PostPhotoService;
import kr.co.heabong.web.service.UserService;
import kr.co.heabong.web.service.UserVolService;
import kr.co.heabong.web.service.VolCategoryService;
import kr.co.heabong.web.security.config.MyUserDetails;


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
	MyUserDetails myUserDetails ;
//	@Autowired
//	PostService postService;
	
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
	public String getIndex(Model model, @AuthenticationPrincipal MyUserDetails user) {
//		model.addAttribute();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                System.out.println("현재 로그인한 사용자: " + userDetails.getUsername());
            }
        }
        
        System.out.println("GET / 유저 메인 페이지로 이동");
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

	

	@GetMapping("user_signup")
	public String getUserSignUp(Model model) {

		return "user_signup";
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
	@GetMapping("category_main")
	public String getVol_category(
			@RequestParam(name="s", required=false)String status,
			@RequestParam(name="v", required = true)int volCategory,
			Model model) {
		//카테고리 
		List<VolCategory> cateList = volCategoryService.getCateList();
		model.addAttribute("cateList",cateList);
		//게시글
		List<OrgVol> mainCateList  = volCategoryService.getMainCategoryList();
		model.addAttribute("mainCateList",mainCateList);
		return "category_main";
	}
	
	
//	마이페이지
	@GetMapping("mypage")
	public String getMypage(
							@RequestParam(name="uid",required = true) int id,
							Model model) {
//		받아와야될것:유저이름,내 게시글,마이페이지 카테고리,유저 프로필사진
		User userName = userService.getUserName(id);
		model.addAttribute("userName",userName);
		
		User userProfilePhoto = userService.findByUserPhoto(id);
		model.addAttribute("userProfilePhoto",userProfilePhoto);
		 
		List<PostPhoto>myPostPhoto = postPhotoService.getMyPostPhoto();
		model.addAttribute("mypic",myPostPhoto);
		
		return "mypage";
	}
	
	
	@GetMapping("vol_list")
	public void getUserVolList(
			@RequestParam(name="id",required = false) int id,
			Model model
			) {
		model.addAttribute("u",model);
		
	}
	
	
//	분야별 봉사 검색 기능
	@GetMapping("orgvol")
	public String getOrgVolListByCategory(
			@RequestParam(name="cid",required = true) int categoryId,
			@RequestParam(name="sk",required = false) String serchKeyword,
			Model model) {
		
		List<OrgVol> orgVolList = null;
		
		if(serchKeyword==null)
			orgVolList = orgVolService.getOrgVolListByCategoryId(categoryId);
		else if(serchKeyword!=null)
			orgVolList = orgVolService.getOrgVolListBySearch(categoryId,serchKeyword);
	
		model.addAttribute("orgVolList",orgVolList);
		model.addAttribute("cid", categoryId);
		
		return "org_vol_by_category";
	}
	
	
	
	

	
	
	@GetMapping("complete_signup")
	public String setUserSignUp(User user, Model model) {
		// model.addAttribute();

		return "complete_signup";
	}
	
	
}