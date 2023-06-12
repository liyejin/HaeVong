package kr.co.heabong.web.controller;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolServiceImpl;


@Controller
@RequestMapping("org")
public class OrgController {
	@Autowired
	OrgVolServiceImpl volService;
	
	@Autowired
	OrgService orgService;
	@RequestMapping("main")//빈칸으로 놔둘지 고민해봐야할듯(루트 -> / )
	public String getMain(Model model) {

		return "org/main";
	}
	
	@RequestMapping("info")
	public String getInfo(Model model) {

		return "info";
	}
	@RequestMapping("login")
	public String getLogin(Model model) {

		return "login";
	}
	
	@RequestMapping("find_pwd")
	public String getFind_pwd(Model model) {

		return "find_pwd";
	}
	@RequestMapping("membership_withdrawal")
	public String getMembership_withdrawal(Model model) {

		return "membership_withdrawal";
	}
	@RequestMapping("new_pwd")
	public String getNew_pwd(Model model) {

		return "new_pwd";
	}
	@RequestMapping("pwd_change_auth")
	public String getPwd_change_auth(Model model) {

		return "pwd_change_auth";
	}
	
	@RequestMapping("pwd_chang_insert")
	public String getPwd_chang_insert(Model model) {

		return "pwd_chang_insert";
	}
	
	
	@RequestMapping("recruit_detail")
	public String getRecruit_detail(Model model) {

		return "recruit_detail";
	}
	
	@RequestMapping("recruit_on_list")
	public String getRecruit_on_list(Model model) {

		return "recruit_on_list";
	}
	
	@RequestMapping("recruit_vol_list")
	public String getRecruit_vol_list(Model model) {

		return "recruit_vol_list";
	}
	
	@RequestMapping("recruit_write")
	public String getRecruit_write(Model model) {

		return "recruit_write";
	}
	
	@RequestMapping("signup")
	public String getSignup(Model model) {

		return "signup";
	}
	
	@RequestMapping("vol_edit")
	public String getVol_edit(Model model) {

		return "vol_edit";
	}
	
	
	
	@RequestMapping("vol_list_empty")
	public String getVol_empty_list(Model model) {

		return "org/vol_list_empty";
	}
	@RequestMapping("vol_list") //org/vol_list // 기관 봉사 리스트
	public String getVol_list(
			@RequestParam(name="s", required=false)String status,
			@RequestParam(name="o", required = true)int orgId,
			Model model) {
		List<OrgVol> list = volService.getList(orgId,status);
		if (list.size()==0)
			return "org/vol_list_empty";
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("orgId", orgId);
		model.addAttribute("map", map);
		return "org/vol_list";  //templates/org/vol_list
	}
	@RequestMapping("vol_recruit")
	public String getVol_recruit(Model model) {

		return "vol_recruit";
	}


	@GetMapping("vol_post_detail")
	public String getVol_post_detail(Model model,
			@RequestParam(name="id"	)int orgId
			) {
		OrgVol orgVol = volService.getById(orgId);
		Org org = orgService.getById(orgVol.getOrgId());
		model.addAttribute("orgVol", orgVol);
		model.addAttribute("org", org);

		
		//현재 시간
	
        // 시간 포맷 지정
  
        
        // 형식에 맞게 시간 출력
//        String formattedTime = currentTime.format(formatter);
//        System.out.println(orgVol.getDate());

//        LocalDateTime dateTime = LocalDateTime.parse(orgVol.getDate(), formatter);
//      System.out.println(dateTime);
        
//        LocalDate dateDate = dateTime.toLocalDate();
        
        String dateString = orgVol.getDate();
        String pattern = "yyyy-MM-dd";  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate date = LocalDate.parse(dateString, formatter);

    	LocalDateTime currentTime = LocalDateTime.now();
    	LocalDate nowDate = currentTime.toLocalDate();
        
        Period period = Period.between(nowDate, date);
        
        int restDate = period.getDays()+(period.getMonths()*30);
//        System.out.println("현재 시간: " + (period.getDays()+(period.getMonths()*30)));

        model.addAttribute("dDay",restDate);
		
		return "org/vol_post_detail";
	}
	
	@RequestMapping("vol_write")
	public String getVol_write(Model model) {

		return "vol_write";
	}
	@RequestMapping("vol")
	public String vol() {
		
		return "org/vol";
	}
}
