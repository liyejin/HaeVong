package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.UserVolService;

@Controller
@RequestMapping("/user")
public class UserVolController {

	@Autowired
	private UserVolService userVolService;
	@Autowired
	private OrgVolService orgVolService;

	@GetMapping("/vol")
	public String getList(Model model) {
		UserVol userVol = userVolService.getUserVol();
		model.addAttribute("userVol", userVol);
		return "user/vol_recruit";
	}

	// My page category section ----------------------------
	@GetMapping("/myapply")
	public String getMyVolList(
			@RequestParam(name = "uid", required = true) int userId,
			Model model) {

		// My apply vol list : 로그인 후 내 봉사 신청/ 참여했던 봉사
		List<OrgVol> orgVol = orgVolService.getMyApplyOrgVolList(userId);
		List<UserVol> userVol = userVolService.getMyApplyUserVolList(userId);
		model.addAttribute("orgVol", orgVol);
		model.addAttribute("userVol", userVol);

		return "user/my_vol_manage";
	}
}