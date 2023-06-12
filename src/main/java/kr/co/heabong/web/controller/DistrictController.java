package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heabong.web.entity.District;
import kr.co.heabong.web.service.DistrictService;

@RequestMapping("district")
@Controller
public class DistrictController {
	
	@Autowired
	private DistrictService service;
	
	@GetMapping("dist")
	public String getList(Model model) {
		List<District> list = service.getList();
		
		model.addAttribute("list",list);
		
		return "map_main";
	}
	
	@ResponseBody
	@GetMapping("test")
	public List<District> getListTest(Model model) {
		List<District> list = service.getList();
		
		model.addAttribute("list",list);
		
		return list;
	}
}
