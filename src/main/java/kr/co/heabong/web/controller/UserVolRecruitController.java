package kr.co.heabong.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heabong.web.service.UserVolRecruitService;

@Controller
@RequestMapping("userVolRecruit")
public class UserVolRecruitController {
	//@Autowired
	private UserVolRecruitService service;
	
//	@GetMapping("list")
//	public String list(Model model) {
//		받아올 데이터가 없는데..?
//	}
}
