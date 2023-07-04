package kr.co.heabong.web.controller;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.KakaoProfile;
import kr.co.heabong.web.entity.OAuthToken;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserWishView;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.security.config.HeabongConfig;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.EmailService;
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
	@Autowired
	EmailService emailService;
	// @Autowired
	// PostService postService;

	@GetMapping("/modal")
	public String getM(Model model) {
		// model.addAttribute();
		return "map_apply_modal";
	}

	// 이메일 테스트
	@GetMapping("/email")
	public void getEamil(Model model) {
		// model.addAttribute();
		emailService.sendMail();
	}

	// 메인
	@GetMapping("/")
	public String getIndex(Model model) {
		List<OrgVol> list = orgVolService.getList();
		model.addAttribute("orgVOl", list);
		return "index";
	}

	// 사진 움직이는 페이지
	@GetMapping("login")
	public String getLogin() {

		return "login";
	}

	// 개인 로그인
	@GetMapping("user_signin")
	public String getSignIn(HttpServletRequest request,
			Model model) {

		String uri = request.getHeader("Referer");
		if (uri != null && !uri.contains("/user_signin")) {
			request.getSession().setAttribute("prevPage", uri);
		}

		return "user_signin";
	}

	// 카카오 로그인
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code, Model model) {
		// 라이브러리
		RestTemplate rt = new RestTemplate();

		// Http Object 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpBodoy Object 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "fb963abaf801111b161895b5e289780c");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);

		// HttpHeaders, HttpBodoy를 하나의 Object에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청 : POST 방식
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			oauthToken = new OAuthToken();
		}

		System.out.println("카카오 엑세스 토큰:" + oauthToken.getAccess_token());

		// 라이브러리
		RestTemplate rt2 = new RestTemplate();

		// Http Object 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type",
				"application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeaders, HttpBodoy를 하나의 Object에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		// Http 요청 : POST 방식
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class);
		// 카카오 프로필 정보 ----------------------------------------
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(),
					KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			oauthToken = new OAuthToken();
		}
		System.out.println("카카오 아이디" + kakaoProfile.getId());
		System.out.println("카카오 이메일" + kakaoProfile.getKakao_account().getEmail());

		System.out.println("해봉 유저네임" + kakaoProfile.getKakao_account().getEmail() +
				"_" + kakaoProfile.getId());
		System.out.println("해봉 이메일" + kakaoProfile.getKakao_account().getEmail());
		UUID garbagePassword = UUID.randomUUID();
		System.out.println("해봉 패스워드" + garbagePassword);

		User user = User.builder()
				.name(kakaoProfile.getKakao_account().getEmail() + "_" +
						kakaoProfile.getId())
				.pwd(garbagePassword.toString())
				.email(kakaoProfile.getKakao_account().getEmail())
				.build();

		// 가입/비가입 회원 체크
		Boolean originUser = userService.isValid(kakaoProfile.getId());

		if (originUser == false) {
			model.addAttribute("user", user);

			return "user_signup";
		} else {
			// 이미 가입된 회원이므로 로그인 처리 등을 수행합니다.
			return "redirect:/";
		}
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

		// String idNum = user.getIdentityNumber();
		// String[] idArr = idNum.split("-");
		// if(idArr)
		//
		// user.setIdentityNumber()

		System.out.println(user);
		user.setIdentityNumber(user.getBirthDate() + user.getGender());
		if (user.getGender() % 2 == 1)
			user.setGender(1);
		else
			user.setGender(2);

		LocalDateTime currentDateTime = LocalDateTime.now();
		int year = currentDateTime.getYear();
		int twoDigitYear = year % 100; // 년도의 두 자리수 계산

		String frontTwoDigits = user.getBirthDate().substring(0, 2); // 앞 두 자리만 잘라내기
		int result = Integer.parseInt(frontTwoDigits); // int로 변환

		user.setAge(twoDigitYear - result + 100);
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
		// 사진
		List<String> photoList = orgVolService.getPhotoList(1);
		model.addAttribute("photoList", photoList);
		return "category_main";
	}

	@GetMapping("mypage")
	public String getMypage(@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		User userProfile = null;
		List<PostPhoto> myPostPhoto = null;

		// user name, profile photo
		if (user != null) {
			userProfile = userService.getUserInfoById(user.getId());
			model.addAttribute("user", userProfile);
		}
		// My post photo
		if (user != null) {
			myPostPhoto = postPhotoService.getMyPostPhoto(user.getId());
			model.addAttribute("mypic", myPostPhoto);
		}

		return "mypage";
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
			@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		Integer userId = null;
		if (user != null)
			userId = user.getId();
		// 카테고리 전체 가져오기
		List<VolCategory> volCategory = volCategoryService.getCateList();
		List<UserWishView> orgVolList = null;

		if (serchKeyword == null & categoryId == 1)
			orgVolList = orgVolService.getView(userId);
		else if (serchKeyword == null & categoryId != null)
			orgVolList = orgVolService.getViewOrgVolListByCategoryId(userId, categoryId);
		else if (serchKeyword != null)
			orgVolList = orgVolService.getViewOrgVolListBySearch(categoryId, serchKeyword);

		model.addAttribute("orgVolList", orgVolList);
		model.addAttribute("cid", categoryId);
		model.addAttribute("volCategory", volCategory);

		return "org_vol_by_category";
	}

	@GetMapping("community")
	public String community() {

		return "community_main";
	}

}