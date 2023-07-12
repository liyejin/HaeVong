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
import kr.co.heabong.web.entity.UserWishView;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.ApplyOrgVolViewService;
import kr.co.heabong.web.service.OrgService;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.UserVolService;
import kr.co.heabong.web.service.VolCategoryService;

@Controller
@RequestMapping("/user")
public class UserVolController {

	@Autowired
	private UserVolService userVolService;
	@Autowired
	private OrgVolService orgVolService;

	@Autowired
	private OrgService OrgService;
	@Autowired
	private VolCategoryService cateService;

	/* 봉사 상세보기 */
	@GetMapping("/vol")

	public String getList(@RequestParam("id") int OrgVolId, @AuthenticationPrincipal MyUserDetails user, Model model) {
		// 기관의 게시글 가져오기

		OrgVol orgVol = orgVolService.getById(OrgVolId);
		// 게시글 쓴 기관 찾기
		Org org = OrgService.getById(orgVol.getOrgId());

		// 신청여부 확인중 
		boolean isApply = orgVolService.checkApply(user.getId(),OrgVolId);

		int countBookmarkUser = orgVolService.getBooKmarkUser(OrgVolId);
	
		List<UserWishView> userWishView = orgVolService.getViewOrgVolByOrgVolId(user.getId(), OrgVolId,1);
		
		VolCategory VolCategory = cateService.getById(orgVol.getVolCategoryId());
		
		
		//게시글 작성 시간
	    String dateString = orgVol.getDate();
	    String deadLine =   orgVol.getDeadLine();
	  
	    int restDate = orgVolService.calculateRestDate(dateString);
	    int diff = orgVolService.calculateDeadLineDate(dateString, deadLine);

		model.addAttribute("orgVol",orgVol);
		model.addAttribute("isApply",isApply);
		model.addAttribute("org",org);
	  // model.addAttribute("dDay", restDate);
	    model.addAttribute("diff", diff);
	    model.addAttribute("userWishView", userWishView.get(0));
	    model.addAttribute("countBookmarkUser", countBookmarkUser);
	    model.addAttribute("volCategory",VolCategory);
	    
		return "user/vol_recruit";
	}

	@GetMapping("/calender")
	public String getMyCalender() {

		return "user/calender";
	}
}