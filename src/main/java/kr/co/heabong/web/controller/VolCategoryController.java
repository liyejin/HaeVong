package kr.co.heabong.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.service.VolCategoryService;

@Controller
@RequestMapping("vol_category")
public class VolCategoryController {

	@Autowired
		private VolCategoryService service;
		
	@GetMapping("category")
		public String list(Model model) {
		
			List<VolCategory>list = service.getCateList();

			return "vol_category";
		}


	@GetMapping("/")
	public String getList(
			@RequestParam(name = "c", required=false) Integer categoryId, Model model) {

		List<VolCategory> list = service.getList();

		List<VolCategory> list1 = new ArrayList<>();
		List<VolCategory> list2 = new ArrayList<>();
		List<VolCategory> list3 = new ArrayList<>();
		list1.add(list.get(0));
		list1.add(list.get(1));
		list1.add(list.get(2));
		list1.add(list.get(3));
		list2.add(list.get(4));
		list2.add(list.get(5));
		list2.add(list.get(6));
		list2.add(list.get(7));
		list3.add(list.get(8));
		list3.add(list.get(9));
		list3.add(list.get(10));
		list3.add(list.get(11));
		
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		for (VolCategory m : list) {
			System.out.println(m.getId() + " : " + m.getName());
		}

		return "vol_category";
	}

}
