package kr.co.heabong.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserWishView;
import kr.co.heabong.web.entity.Wish;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.OrgVolService;
import kr.co.heabong.web.service.WishService;

@RestController("apiBookmarkController")
@RequestMapping("api/wish")
public class BookmarkController {
	@Autowired
	WishService service;
	@Autowired
	OrgVolService orgvol;
	
	@GetMapping
	public List<UserWishView> orgVolList(@RequestParam(name = "c", required = true) Integer categoryId,
			@RequestParam(name = "s", required = false) String serchKeyword,@AuthenticationPrincipal MyUserDetails user){
		
		Integer userId = user.getId();
		System.out.println(user.getId());
		List<UserWishView> orgVolList = new ArrayList<>();
		
		if(categoryId != null)
			orgVolList = orgvol.getViewOrgVolListByCategoryId(userId, categoryId);
		else if(serchKeyword != null )
			orgVolList = orgvol.getViewOrgVolListBySearch(userId, serchKeyword);
		else
			orgVolList = orgvol.getView(userId);
		
		return orgVolList;
	}
	
	
	@PostMapping
	public int append(
			@RequestParam("oi") int orgVolId,
			@RequestParam("ui") int userId
			) {
		Wish wish = Wish
							.builder()
							.userId(userId)
							.orgVolId(orgVolId)
							.build();
		return service.apend(wish);
	}
	
	
	@DeleteMapping("{orgVolId}/user/{userId}")
	public int delete(
			@PathVariable("orgVolId") int orgVolId,
			@PathVariable("userId") int userId
			) {
		Wish wish = Wish
				.builder()
				.userId(userId)
				.orgVolId(orgVolId)
				.build();
		return service.delete(wish);
	}
	
}
