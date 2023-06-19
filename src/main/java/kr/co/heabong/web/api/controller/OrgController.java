package kr.co.heabong.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.service.OrgService;

@RestController("apiOrgController")
@RequestMapping("api/org")
public class OrgController {
	
	@Autowired
	private OrgService service;
	
//	@GetMapping("main")
//	public Org getById() {
//		
//		Org org = service.getById();
//		
//		return org;
//		
//	}

}
