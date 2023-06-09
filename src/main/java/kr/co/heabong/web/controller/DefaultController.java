package kr.co.heabong.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.ApplyOrgVolView;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.OrgVolAddressView;
import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserWishView;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.security.config.CustomOAuth2UserService;
import kr.co.heabong.web.security.config.HeabongConfig;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.ApplyOrgVolViewService;
import kr.co.heabong.web.service.DistrictService;
import kr.co.heabong.web.service.EmailService;
import kr.co.heabong.web.service.MetroService;
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
	DistrictService districtService;
	@Autowired
	MetroService metroService;
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
	@Autowired
	ApplyOrgVolViewService applyOrgVolViewService;;

	@GetMapping("/modal")
	public String getM(Model model) {
		// model.addAttribute();
		return "map_apply_modal";
	}

	@ResponseBody
	@GetMapping("/kakao")
	public String getK(String code) throws JsonMappingException, JsonProcessingException {

		System.out.println("code : " + code);

		String REQUEST_URL = "https://kauth.kakao.com/oauth/token";
		RestTemplate restTemplate = new RestTemplate();

		// Set Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Accept", "application/json");

		// Set parameter
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "9134e21d6cafc9fba3e3ded952ba43a2");
		params.add("redirect_uri", "http://localhost:8080/kakao");
		params.add("code", code);
		// Set http entity
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

		ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(REQUEST_URL, request, String.class);

		String responseBody = stringResponseEntity.getBody();
		System.out.println("Response Body: " + responseBody);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(responseBody);
		String accessToken = responseJson.get("access_token").asText();

		System.out.println("Access Token: " + accessToken);

		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Authorization", "Bearer " + accessToken);
		headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<String> requestEntity1 = new HttpEntity<>(headers1);

		// 요청 보내기
		ResponseEntity<String> responseEntity1 = restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.GET, requestEntity1, String.class);

		String responseBody1 = responseEntity1.getBody();

		ObjectMapper objectMapper1 = new ObjectMapper();
		JsonNode responseJson1 = objectMapper1.readTree(responseBody1);

		String id = responseJson1.get("id").asText();
		String email = responseJson1.get("kakao_account").get("email").asText();

		System.out.println(id);
		System.out.println(email);

		return responseBody1;
	}

	// 메인
	@GetMapping("/")
	public String getIndex(Model model) {
		List<OrgVol> list = orgVolService.getList();

		List<OrgVolAddressView> summerList = orgVolService.getListByRandom();

		model.addAttribute("orgVOl", list);
		model.addAttribute("summerList", summerList);

		return "index";
	}

	// 사진 움직이는 페이지
	@GetMapping("login")
	public String getLogin() {

		return "login";
	}

	// 개인 로그인
	@GetMapping("user_signin")
	public String getSignIn(HttpServletRequest request, Model model) {

		String uri = request.getHeader("Referer");
		if (uri != null && !uri.contains("/user_signin")) {
			request.getSession().setAttribute("prevPage", uri);
		}

		return "user_signin";
	}

	@PostMapping("user_signin")
	public String setSignIn(String uid, String pwd) {
		System.out.println(uid);
		System.out.println(pwd);

		System.out.println();

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

		return "redirect:/";
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

	// My Page
	@GetMapping("mypage")
	public String getMypage(@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		User userInfo = null;
		Integer countApplyVol = applyOrgVolViewService.getCountMyAttendList(user.getId());
		// countApplyVol = countApplyVol != null ? countApplyVol : 0;
		System.out.println(countApplyVol);
		List<PostPhoto> myPostPhoto = null;

		// user name, profile photo, signdate info
		if (user != null) {
			userInfo = userService.getUserInfoById(user.getId());

		}
		// My post photo
		if (user != null) {
			myPostPhoto = postPhotoService.getMyPostPhoto(user.getId());
		}
		model.addAttribute("mypic", myPostPhoto);
		model.addAttribute("user", userInfo);
		model.addAttribute("countAV", countApplyVol);

		return "mypage";
	}

	@GetMapping("vol_list")
	public void getUserVolList(@RequestParam(name = "id", required = false) int id, Model model) {
		model.addAttribute("u", model);

	}

	// 분야별 봉사 검색 기능
	@GetMapping("orgvol")
	public String getOrgVolListByCategory(
			@RequestParam(name = "cid", required = true) Integer categoryId,
			@RequestParam(name = "sk", required = false) String searchKeyword,
			@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		Integer userId = null;

		if (user != null)
			userId = user.getId();
		// 카테고리 전체 가져오기
		List<VolCategory> volCategory = volCategoryService.getCateList();
		List<UserWishView> orgVolList = null;

		if (searchKeyword == null & categoryId == 1) {
			orgVolList = orgVolService.getView(userId);
			System.out.println("디폴트조건1");
		} else if (searchKeyword == null & categoryId != null) {
			orgVolList = orgVolService.getViewOrgVolListByCategoryId(userId, categoryId);
			System.out.println("디폴트조건2");
		} else if (searchKeyword != null && categoryId != 1) {
			orgVolList = orgVolService.getViewOrgVolListBySearch(categoryId, searchKeyword);
			System.out.println("디폴트조건3");
		} else if (searchKeyword != null & categoryId == 1) {
			orgVolList = orgVolService.getVolListBySearch(userId, searchKeyword);
			System.out.println("디폴트조건4");
		}

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