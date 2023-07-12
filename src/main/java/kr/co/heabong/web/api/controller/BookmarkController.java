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
			@RequestParam(name = "s", required = false) String serchKeyword,
			@RequestParam(name = "p", defaultValue = "1")Integer page,
			@AuthenticationPrincipal MyUserDetails user) {

		Integer userId = user.getId();
		System.out.println(user.getId());
		List<UserWishView> orgVolList = new ArrayList<>();

		if (categoryId == 1 && serchKeyword != "") {
		    orgVolList = orgvol.getView(userId, null, serchKeyword,page);
		    System.out.println("조건2");
		} else if (categoryId == 1) {
		    orgVolList = orgvol.getView(userId, null, serchKeyword,page);
		    System.out.println("조건5");
		} else if (categoryId != null && serchKeyword == "") {
		    orgVolList = orgvol.getViewOrgVolListByCategoryId(userId, categoryId,page);
		    System.out.println("조건1");
		} else {
		    orgVolList = orgvol.getView(userId, categoryId, serchKeyword,page);
		    System.out.println("조건3" + orgVolList);
		}
		return orgVolList;
	}

	@PostMapping
	public int append(@RequestParam("oi") int orgVolId, @RequestParam("ui") int userId) {
		Wish wish = Wish.builder().userId(userId).orgVolId(orgVolId).build();
		return service.apend(wish);
	}

	@DeleteMapping("{orgVolId}/user/{userId}")
	public int delete(@PathVariable("orgVolId") int orgVolId, @PathVariable("userId") int userId) {
		Wish wish = Wish.builder().userId(userId).orgVolId(orgVolId).build();
		return service.delete(wish);
	}

}