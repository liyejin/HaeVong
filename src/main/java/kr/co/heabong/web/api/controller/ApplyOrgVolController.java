package kr.co.heabong.web.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.ApplyOrgVol;
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
		
		
		@PostMapping
		public ResponseEntity<Object> insert(@RequestBody ApplyOrgVol applyOrgVol) {
			ApplyOrgVol newOne = service.add(applyOrgVol);
			
			if(newOne == null) {
				return new ResponseEntity<Object> ("저장 실패",HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(newOne,HttpStatus.OK);
		}
		
		
		@DeleteMapping
		public ResponseEntity<Object> delete (@RequestBody ApplyOrgVol applyOrgVol) {
			int result = service.delete(applyOrgVol);
			
			if(result ==0) {
				return new ResponseEntity<Object> ("삭제 실패",HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		
		
	}

