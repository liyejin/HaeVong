package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;

@RestController("apiOrgController")
@RequestMapping("api/org")
public class OrgController {
	
	@Autowired
	private OrgService service;
	@Autowired
	private OrgVolService volService;
	
	@GetMapping("main")
	public Org getById(@AuthenticationPrincipal MyUserDetails user) {
		
		Org org = service.getById(user.getId());
		
	return org;
		
	}
	
	@GetMapping
	public List<OrgVol> list(@RequestParam(name="c",required = false)
							Integer id){
		List<OrgVol> list = volService.getOrgVolListByCategoryId(id);
		return list; 
	}

}
