package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Calender;
import kr.co.heabong.web.entity.OrgVol;

@Mapper
public interface CalenderRepository {
	List<Calender> findAll(int userId);
}
