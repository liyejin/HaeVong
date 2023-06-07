package kr.co.heabong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class DefaultController {
	
	//테스트겸 만든거
	@GetMapping("/test")
	public String getTest(Model model) {
//		model.addAttribute();
		return "test";
	}
	
	@GetMapping("/")
	public String getIndex(Model model) {
//		model.addAttribute();
		return "index";
	}
	
	@GetMapping("map_apply_modal")
	public String getMap_apply_modal(Model model) {
//		model.addAttribute();
		
		return "map_apply_modal";
	}
	
	@GetMapping("map_main")
	public String getMap_main(Model model) {
//		model.addAttribute();
		return "map_main";
	}
	
	@GetMapping("vol_category")
	public String getVol_category(Model model) {
//		model.addAttribute();
		return "vol_category";
	}
	
	
	@GetMapping("vol_recruit")
	public String getVol_recruit(Model model) {
//		model.addAttribute();
		return "vol_recruit";
	}
}