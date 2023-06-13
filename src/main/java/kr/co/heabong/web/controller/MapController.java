package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heabong.web.entity.Metropol;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.service.MetroService;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.VolCategoryService;

@RequestMapping("map")
@Controller
public class MapController {
	//기관 서비스
	@Autowired
	private OrgService orgService;
	//기관 봉사 서비스
	@Autowired
	private OrgVolService orgVolService;
	@Autowired
	private MetroService metroService;
	@Autowired
	private VolCategoryService volCategoryService;
	
	//맵 가져오기
	@GetMapping("/main")
	public String getList(Model model,
			@RequestParam(name = "address", defaultValue = "서울", required = false) String address
			) {

		List<OrgVol> orgVolList = null;
		List<Metropol> mtpList = metroService.getList();
		List<VolCategory> volCategoryList = volCategoryService.getList();
		if(address!=null)
			orgVolList  = orgVolService.getList();
		else
			orgVolList  = orgVolService.getList(address);
		
		
		model.addAttribute("ol", orgVolList);
		model.addAttribute("mtp", mtpList);
		model.addAttribute("volCategory", volCategoryList);
		return "map_main";
	}

	@ResponseBody
	@GetMapping("/test")
	public List<Metropol> getTest(
			@RequestParam(name = "dist", defaultValue = "서울") String distName,
			Model model) {

		List<Metropol> list = metroService.getList();
		
		
		
		return list;
	}
}
