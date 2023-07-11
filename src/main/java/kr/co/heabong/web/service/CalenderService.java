package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Calender;


public interface CalenderService {
		
	List<Calender> findSchedule(int userId);
		
}
