package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.District;

public interface DistrictService {
	public List<District> getList();

	public int getById(String string);
	
	String name (int districtId,int metropolId);

	int getById(String name, int metropolId);
}
