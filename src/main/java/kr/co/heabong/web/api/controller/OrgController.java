package kr.co.heabong.web.api.controller;

import java.util.ArrayList;
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

/*★★★★★여기는 API 컨트롤러★★★★★*/

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
	public List<OrgVol> list(@RequestParam(name = "s", required = false) String serchKeyword,
			@RequestParam(name = "c", required = true) Integer categoryId) {
		
		List<OrgVol> list = new ArrayList<>();
		if (serchKeyword == "" && categoryId == 1)
			list = volService.getList();
		else if (serchKeyword == "" && categoryId != null)
			list = volService.getOrgVolListByCategoryId(categoryId);
		/* else if (serchKeyword != "") */
		else if (serchKeyword != null && categoryId == 1)
			list = volService.getOrgVolListBySearch(null , serchKeyword);
		else{
			list = volService.getOrgVolListBySearch(categoryId, serchKeyword);
			list.get(0).getTitle();
			System.out.println(list.get(0).getTitle());
		}
		return list;
	}

}
