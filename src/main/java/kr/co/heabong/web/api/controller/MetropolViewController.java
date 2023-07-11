package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.MetropolView;
import kr.co.heabong.web.service.MetropolViewService;

@RestController("apiMetropolViewController")
@RequestMapping("api/metropolView")
public class MetropolViewController {
	
	@Autowired
	private MetropolViewService service;
	
	
	@GetMapping("{metroId}")
	public List<MetropolView> getList(@PathVariable("metroId") Integer metroId){
		List<MetropolView> list = null;
		if(metroId !=null)
		list = service.getListByMetropolId(metroId);
		
		return list;
		
		
	}
}
