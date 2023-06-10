package kr.co.heabong.web.service;

import java.util.List;


import kr.co.heabong.web.entity.OrgVol;

public interface OrgVolService {
	List<OrgVol> getList();
	OrgVol get();
	void save();
	
	
}
