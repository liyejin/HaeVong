package kr.co.heabong.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("org")
public class OrgController {
	@RequestMapping("main")//빈칸으로 놔둘지 고민해봐야할듯(루트 -> / )
	public String getMain(Model model) {

		return "main";
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
	
	
	
	@RequestMapping("vol_empty_list")
	public String getVol_empty_list(Model model) {

		return "vol_empty_list";
	}
	@RequestMapping("vol_list")
	public String getVol_list(Model model) {

		return "vol_list";
	}
	@RequestMapping("vol_recruit")
	public String getVol_recruit(Model model) {

		return "vol_recruit";
	}

	@RequestMapping("vol_write")
	public String getVol_write(Model model) {

		return "vol_write";
	}
}
