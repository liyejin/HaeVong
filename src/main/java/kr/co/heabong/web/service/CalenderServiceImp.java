package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Calender;
import kr.co.heabong.web.repository.ApplyOrgVolViewRepository;
import kr.co.heabong.web.repository.CalenderRepository;

@Service
public class CalenderServiceImp implements CalenderService{

	
	  @Autowired
	    CalenderRepository repository;

	@Override
	public List<Calender> findSchedule(int userId) {
		// TODO Auto-generated method stub
		return repository.findAll(userId);
	}
	  
	  

}
