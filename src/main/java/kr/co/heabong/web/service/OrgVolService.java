package kr.co.heabong.web.service;

import java.util.List;


import kr.co.heabong.web.entity.OrgVol;

public interface OrgVolService {
	List<OrgVol> getList();
	List<OrgVol> getList(int orgId, String status);
	OrgVol get();
	int save(OrgVol orgVol);
	
	
}
