package kr.co.heabong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
	@GetMapping("customer_service")
	public String getCustomer_service(Model model) {

		return "customer_service";
	}

	@GetMapping("customer_service_inquiry")
	public String getCustomer_service_inquiry(Model model) {

		return "customer_service_inquiry";
	}

	@GetMapping("customer_service_insert_inquiry")
	public String getCustomer_service_insert_inquiry(Model model) {

		return "customer_service_insert_inquiry";
	}
	
	@GetMapping("customer_service_most_inquiry")
	public String getCustomer_service_most_inquiry(Model model) {

		return "customer_service_most_inquiry";
	}
	
	@GetMapping("customer_service_my_inquiry")
	public String getCustomer_service_my_inquiry(Model model) {

		return "customer_service_my_inquiry";
	}
	@GetMapping("edit_info")
	public String getEdit_info(Model model) {

		return "edit_info";
	}
	@GetMapping("edit_pwdchange")
	public String getEdit_pwdchange(Model model) {

		return "edit_pwdchange";
	}
	@GetMapping("edit_pwdchange_insert")
	public String getEdit_pwdchange_insert(Model model) {

		return "edit_pwdchange_insert";
	}
}