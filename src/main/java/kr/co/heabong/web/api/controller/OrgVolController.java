package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.ApplyOrgVol;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserWishView;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.service.DistrictService;
import kr.co.heabong.web.service.MetroService;
import kr.co.heabong.web.service.MetropolViewService;
import kr.co.heabong.web.service.OrgVolService;

// 나중에 RestController로 바꿀때 
@RestController("apiOrgVolController")
@RequestMapping("api/org-vols")
public class OrgVolController {

	@Autowired
	private OrgVolService service;
	@Autowired
	private MetroService metroService;
	@Autowired
	private MetropolViewService metropolVewService;
	@Autowired
	private DistrictService districtService;

	@GetMapping
	public List<OrgVol> getListByStatus(@RequestParam("o") int orgId, @RequestParam("s") String status) {
		List<OrgVol> list = service.getList(orgId, status);
		return list;
	}
	
	//맵에서 좌측 탭 클릭시 넘겨줄 OrgVol List
	@GetMapping("{address}")
	public List<OrgVol> getListByMetropol(
			@PathVariable("address") String address) {
		
		List<OrgVol> list = service.getListByAddress(address);

		return list;

	}
	
	@DeleteMapping
	public int delete(Integer id) {
		return service.delete(id);
	}
	
	
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insert(@RequestBody OrgVol orgVol) {
		int metropolId = metroService.getById(orgVol.getRoadAddress().split(" ")[0]);
		int districtId = districtService.getById(orgVol.getRoadAddress().split(" ")[1], metropolId);
		orgVol.setMetropolId(metropolId);
		orgVol.setDistrictId(districtId);

		OrgVol newOne = service.add(orgVol);
		
		if(newOne == null) {
			return new ResponseEntity<Object> ("저장 실패",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(newOne,HttpStatus.OK);
	}
	
	
	//업데이트 된 메뉴를 받기 위한 풋매핑
	@PutMapping
	public OrgVol edit(OrgVol orgVol) {
		int metropolId = metroService.getById(orgVol.getRoadAddress().split(" ")[0]);
		int districtId = districtService.getById(orgVol.getRoadAddress().split(" ")[1], metropolId);
		System.out.printf("%d,%d",metropolId,districtId);
		System.out.println(orgVol);
		orgVol.setMetropolId(metropolId);
		orgVol.setDistrictId(districtId);
		OrgVol updatedOrgVol = service.update(orgVol);
		return updatedOrgVol;
	}


}
