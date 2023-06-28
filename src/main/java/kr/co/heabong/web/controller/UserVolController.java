package kr.co.heabong.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.heabong.web.entity.ApplyOrgVolView;
import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.ApplyOrgVolViewService;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.UserVolService;

@Controller
@RequestMapping("/user")
public class UserVolController {

	@Autowired
	private UserVolService userVolService;
	@Autowired
	private OrgVolService orgVolService;
	@Autowired
	private ApplyOrgVolViewService orgVolViewService;
	@Autowired
	private OrgService OrgService;

	
	/* 봉사 상세보기 */
	@GetMapping("/vol")
	public String getList(@RequestParam("id") int OrgVolId
													,Model model) {
		//기관의 게시글 가져오기
		OrgVol orgVol = orgVolService.getById(OrgVolId);
		//게시글 쓴 기관 찾기 
		Org org = OrgService.getById(orgVol.getOrgId());
		
		//게시글 작성 시간
	    String dateString = orgVol.getDate();
	    int restDate = orgVolService.calculateRestDate(dateString);

		model.addAttribute("orgVol",orgVol);
		model.addAttribute("org",org);
	    model.addAttribute("dDay", restDate);

		return "user/vol_recruit";
	}
	
	
	// My page category section ----------------------------
	@GetMapping("/myapply")
	public String getMyVolList(
			@AuthenticationPrincipal MyUserDetails user,
			Model model) {

		// My apply vol list : 로그인 후 내 봉사 신청/ 참여했던 봉사
		List<ApplyOrgVolView> list = orgVolViewService.getApplyVolList(user.getId());
		model.addAttribute("apVol", list);

		return "user/my_vol_manage";
	}
}