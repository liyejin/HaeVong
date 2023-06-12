package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.Org;

public interface OrgService {
	List<Org> getList();
	Org getListById(int id);
//	List<Org> getListByQuery(String query); 
}
