package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.service.UserVolService;

@Controller
@RequestMapping("/user")
public class UserVolController {

	@Autowired
	private UserVolService service;
	
	@GetMapping("/vol")
	public String getList(Model model) {
		UserVol userVol = service.getUserVol();
		model.addAttribute("userVol",userVol);
		return "user/user_vol_recruit";
	}
}