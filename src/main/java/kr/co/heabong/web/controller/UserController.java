package kr.co.heabong.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.heabong.web.entity.ApplyOrgVolView;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.PostPhoto;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.ApplyOrgVolViewService;
import kr.co.heabong.web.service.PostPhotoService;
import kr.co.heabong.web.service.UserService;
import kr.co.heabong.web.service.UserVolService;
import kr.co.heabong.web.service.WishService;

@Controller
@RequestMapping("user")
public class UserController {

	@GetMapping("index")
	public String index(HttpSession session) throws IOException {

		// manager.setUsersByUsernameQuery("select name username, pwd password, 1
		// enabled from org where name=?");//username, password, enabled(컬럼명 반드시 일치시켜줄것)
		// manager.setAuthoritiesByUsernameQuery("select name username, r.name authority
		// from role r join org o on o.role_id=r.id where username=?");//username,
		// authority

		return "index";
	}

	@Autowired
	private UserVolService volService;
	@Autowired
	private WishService wishService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostPhotoService postPhotoService;
	@Autowired
	private ApplyOrgVolViewService orgVolViewService;

	@GetMapping("customer_service")
	public String getCustomer_service(Model model) {

		return "customer_service";
	}

	@GetMapping("customer_service_inquiry")
	public String getCustomer_service_inquiry(Model model) {

		return "customer_service_inquiry";
	}

	@GetMapping("customer_service_insert_inquiry")
	public String getCustomer_service_insert_inquiry(Model model) {

		return "customer_service_insert_inquiry";
	}

	@GetMapping("customer_service_most_inquiry")
	public String getCustomer_service_most_inquiry(Model model) {

		return "customer_service_most_inquiry";
	}

	@GetMapping("customer_service_my_inquiry")
	public String getCustomer_service_my_inquiry(Model model) {

		return "customer_service_my_inquiry";
	}

	@GetMapping("edit_info")
	public String getEdit_info(Model model) {

		return "edit_info";
	}

	@GetMapping("edit_pwdchange")
	public String getEdit_pwdchange(Model model) {

		return "edit_pwdchange";
	}

	@GetMapping("edit_pwdchange_insert")
	public String getEdit_pwdchange_insert(Model model) {

		return "edit_pwdchange_insert";
	}

	@GetMapping("my_calender")
	public String schedule(Model model) {

		return "/user/calender";
	}

	// 여기서부터 user_vol

	@RequestMapping("vol_list") // org/vol_list // 기관 봉사 리스트
	public String getVol_list(
			@RequestParam(name = "s", required = false) String status,
			@RequestParam(name = "u", required = false) int userId,
			Model model) {
		List<UserVol> list = volService.getList(userId, status);
		if (list.size() == 0)
			return "org/vol_list_empty";
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("userId", userId);
		map.put("category", "part");
		model.addAttribute("map", map);
		return "user/vol_list"; // templates/org/vol_list
	}

	@RequestMapping("vol_wish_list")
	public String getVolWishList(
			@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		int userId = user.getId();

		List<OrgVol> list = wishService.getOrgVolListByUser(userId);
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("userId", userId);
		map.put("category", "wish");
		model.addAttribute("map", map);
		return "user/vol_list";
	}

	// My page category section ----------------------------
	@GetMapping("/myapply")
	public String getMyVolList(
			@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		// My apply vol list : 로그인 후 내 봉사 신청/ 참여했던 봉사
		List<ApplyOrgVolView> list = orgVolViewService.getApplyVolList(user.getId());
		model.addAttribute("apVol", list);

		return "user/my_vol_manage";
	}

}