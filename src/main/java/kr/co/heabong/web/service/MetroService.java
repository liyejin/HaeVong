package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.Metropol;

public interface MetroService {

	List<Metropol> getList();
	int getById(String name);
	String getNameById(int id);
	
}
