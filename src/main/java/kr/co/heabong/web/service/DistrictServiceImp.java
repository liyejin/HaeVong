package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.District;
import kr.co.heabong.web.repository.DistrictRepository;

@Service
public class DistrictServiceImp implements DistrictService{

	@Autowired
	private DistrictRepository repository;
	
	@Override
	public List<District> getList() {
		// TODO Auto-generated method stub
		
		List<District> list = repository.findAll();
		return list;
	}
	 
	
}
