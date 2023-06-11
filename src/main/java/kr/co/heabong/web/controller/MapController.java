package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.heabong.web.entity.District;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;

@RequestMapping("map")
@Controller
public class MapController {
	//기관 서비스
	@Autowired
	private OrgService orgService;
	//기관 주관 봉사 서비스
	@Autowired
	private OrgVolService orgVolService;

	//맵 가져오기
	@GetMapping("/main")
	public String getList(Model model,
			@RequestParam(name = "dist", defaultValue = "서울", required=false) String address
			) {

		List<Org> orgList = null;
		orgService.getList();

//		if(query!=null)
//			orgList  = orgService.getList(query);
//		
//		else if(query!=null)
//			orgList  = orgService.getList(query);

		model.addAttribute("ol", orgList);
		
		System.out.println(address.toString());

		List<OrgVol> orgVolList = null;
		
		if(address!=null)
			orgVolList  = orgVolService.getList();
	
	else if(address!=null)
		orgVolList  = orgVolService.getListByAddress(address);
		
		
		return "map_main";
	}

	@GetMapping("/test")
	public String getTest(
			@RequestParam(name = "dist", defaultValue = "서울") String distName,
			Model model) {

		List<Org> orgList = orgService.getList();
		
		
		
		return "map_test";
	}
}
