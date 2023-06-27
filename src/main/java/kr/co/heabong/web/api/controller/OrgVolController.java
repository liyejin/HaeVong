package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.service.OrgVolService;
// 나중에 RestController로 바꿀때 
@RestController("apiOrgVolController")
@RequestMapping("api/org-vols")
public class OrgVolController {

	@Autowired
	private OrgVolService service;

	/*
	 * @GetMapping public List<OrgVol> getList(){ List<OrgVol> list =
	 * service.getList(); return list; }
	 */
	@GetMapping("/orgvol")
	public List<OrgVol> getListByStatus(@RequestParam("o") int orgId,@RequestParam("s") String status){
		List<OrgVol> list = service.getList(orgId, status);
		return list;
		
	}
	
	@DeleteMapping
	public int delete(Integer id) {
		return service.delete(id);
	}
	
	
	
}
