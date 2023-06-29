package kr.co.heabong.web.controller;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserApplyView;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.ApplyOrgVolService;
import kr.co.heabong.web.service.DistrictService;
import kr.co.heabong.web.service.MetroService;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.VolCategoryService;

@Controller
@RequestMapping("org")
public class OrgController {

	@Autowired
	private OrgVolService volService;

	@Autowired
	private OrgService orgService;

	@Autowired
	private ApplyOrgVolService applyOrgVolService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private MetroService metroService;

	@Autowired
	private VolCategoryService volCategoryService;

	@GetMapping("main") // 빈칸으로 놔둘지 고민해봐야할듯(루트 -> / )
	public String getMain(Model model,@AuthenticationPrincipal MyUserDetails user) {
	
	Org org = orgService.getById(user.getId());
	int ingCount = volService.ingOrgVol(user.getId());
	model.addAttribute("org",org);
	model.addAttribute("ingCount",ingCount);

		return "org/main";
	}

	@RequestMapping("info")
	public String getInfo(Model model) {

		return "org/info";
	}

	@RequestMapping("signin")
	public String getSignIn(Model model) {

		return "org_signin";
	}

	@GetMapping("signup")
	public String getSignUp(Model model) {

		return "org_signup";
	}

	@RequestMapping("find_pwd")
	public String getFind_pwd(Model model) {

		return "org/find_pwd";
	}

	@RequestMapping("membership_withdrawal")
	public String getMembership_withdrawal(Model model) {

		return "org/membership_withdrawal";
	}

	@RequestMapping("new_pwd")
	public String getNew_pwd(Model model) {

		return "org/new_pwd";
	}

	@RequestMapping("pwd_change_auth")
	public String getPwd_change_auth(Model model) {

		return "org/pwd_change_auth";
	}

	@RequestMapping("pwd_chang_insert")
	public String getPwd_chang_insert(Model model) {

		return "org/pwd_chang_insert";
	}

	@RequestMapping("vol/applicants")
	public String getRecruit_vol_list(@RequestParam("id") int orgVolID, Model model) {

		OrgVol orgVol = volService.getById(orgVolID);
		List<UserApplyView> userList = applyOrgVolService.getApplicantlList(orgVolID);
		model.addAttribute("userList", userList);
		model.addAttribute("orgVol", orgVol);

		return "org/applicants";
	}

	// @RequestMapping("recruit_write")
	// public String getRecruit_write(@RequestParam("oid") int orgId, Model model) {
	// model.addAttribute(orgId);
	// return "org/recruit_write";
	// }

	@RequestMapping("signup")
	public String getSignup(Model model) {

		return "org/signup";
	}

	@RequestMapping("vol_edit")
	public String getVol_edit(@RequestParam(name="id", required=true) int volId,
			Model model) {
		OrgVol orgVol = volService.getById(volId);
		int volCategoryId = orgVol.getVolCategoryId();
		VolCategory volCategory = volCategoryService.getById(volCategoryId);
		String volCategoryName = volCategory.getName();
		List<VolCategory> cateList = volCategoryService.getCateList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("vol", orgVol);
		model.addAttribute("volCategory",volCategory);
		

		return "org/vol_post_edit";
	}

	@RequestMapping("vol_list_empty")
	public String getVol_empty_list(Model model) {

		return "org/vol_list_empty";
	}

	@RequestMapping("vol_list") // org/vol_list // 기관 봉사 리스트
	public String getVol_list(@RequestParam(name = "s", required = false) String status,
			@RequestParam(name = "o", required = true) int orgId, Model model) {
		List<OrgVol> list = volService.getList(orgId, status);
		// for (OrgVol orgVol : list) {
		// DateTimeFormatter dateTimeFormatter =
		// DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// orgVol.getRegdate().format(dateTimeFormatter);
		// }
		if (list.size() == 0)
			return "org/vol_list_empty";
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("orgId", orgId);
		map.put("status", status);
		model.addAttribute("map", map);
		return "org/vol_list"; // templates/org/vol_list
	}

	@GetMapping("vol_post_detail")
	public String getVol_post_detail(Model model, @RequestParam(name = "id") int orgVolId) {
		OrgVol orgVol = volService.getById(orgVolId);
		Org org = orgService.getById(orgVol.getOrgId());
		model.addAttribute("orgVol", orgVol);
		model.addAttribute("org", org);

		// 현재 시간

		// 시간 포맷 지정

		// 형식에 맞게 시간 출력
		// String formattedTime = currentTime.format(formatter);
		// System.out.println(orgVol.getDate());

		// LocalDateTime dateTime = LocalDateTime.parse(orgVol.getDate(), formatter);
		// System.out.println(dateTime);

		// LocalDate dateDate = dateTime.toLocalDate();

		String dateString = orgVol.getDate();
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate date = LocalDate.parse(dateString, formatter);

		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate nowDate = currentTime.toLocalDate();

		Period period = Period.between(nowDate, date);

		int restDate = period.getDays() + (period.getMonths() * 30);
		// System.out.println("현재 시간: " + (period.getDays()+(period.getMonths()*30)));

		model.addAttribute("dDay", restDate);

		return "org/vol_post_detail";
	}

	@GetMapping("vol_post_write")
	public String getRecruit_write(@RequestParam("oid") int orgId, Model model) {
		List<VolCategory> cateList = volCategoryService.getCateList();
		model.addAttribute("cateList", cateList);
		model.addAttribute("orgId", orgId);
		return "org/vol_post_write";
	}

	@PostMapping("vol_write") // 정보를 받기
	public String postMethod(OrgVol orgVol) {

		int metropolId = metroService.getById(orgVol.getRoadAddress().split(" ")[0]);
		int districtId= districtService.getById(orgVol.getRoadAddress().split(" ")[1],metropolId);
		orgVol.setMetropolId(metropolId);
		orgVol.setDistrictId(districtId);
		   int save = volService.save(orgVol);
		   System.out.println(save);
		   
		return "redirect:vol_post_detail?id="+orgVol.getId(); 

	}
	
	
	@PostMapping("vol_edit")
	public String volEdit(OrgVol orgVol) {
		int metropolId = metroService.getById(orgVol.getRoadAddress().split(" ")[0]);
		int districtId= districtService.getById(orgVol.getRoadAddress().split(" ")[1],metropolId);
		orgVol.setMetropolId(metropolId);
		orgVol.setDistrictId(districtId);
		System.out.println(orgVol.getId());   
		   int edit = volService.edit(orgVol);
		   System.out.println(edit);
		System.out.println(orgVol.getId());   
		return "redirect:vol_post_detail?id="+orgVol.getId(); 
	}

	@RequestMapping("vol_post_detail")

	public String vol_post_detail(@RequestParam("id") int orgVolID, Model model) {

		return "org/vol_post_detail";
	}
	
	
	

}
