package kr.co.heabong.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.service.ApplyOrgVolService;

@RestController("apiApplyOrgVolController")
@RequestMapping("api/apply-org-vols")
public class ApplyOrgVolController {
	@Autowired
	ApplyOrgVolService service;

	@PutMapping
	public int update(@RequestParam int orgVolId, @RequestParam int userId, @RequestParam int status) {
		int changeApplicantStatus = service.changeApplicantStatus(orgVolId, userId, status);
		if (changeApplicantStatus == 1) {
			System.out.println(status);
			return status;
		} else {
			return -1;
		}
	}
}
