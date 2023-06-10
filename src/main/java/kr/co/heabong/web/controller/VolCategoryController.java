package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.service.VolCategoryService;

@Controller
@RequestMapping("volcategory")
public class VolCategoryController {
	@Autowired
		private VolCategoryService service;
		
	@GetMapping("category")
		public String list(Model model) {
		
			List<VolCategory>list = service.getlist();
			
			return "vol_category";
		}
}
