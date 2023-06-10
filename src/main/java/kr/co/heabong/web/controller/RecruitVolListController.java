package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.service.RecruitVolListService;

@Controller
@RequestMapping("recruit")
public class RecruitVolListController {
	@Autowired
	private RecruitVolListService service;
	
	@GetMapping("list")
	public String list(Model model) {
		List<User>list = service.getlist();
		model.addAttribute("list",list);
		
		return "org/recruit_vol_list";
	}
	
}
