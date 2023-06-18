package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.OrgVol;

public interface OrgVolService {
	List<OrgVol> getList();

	List<OrgVol> getList(String address);

	List<OrgVol> getList(int orgId, String status);

	OrgVol getById(int id);

	List<OrgVol> getListByAddress(String address);

	OrgVol get();

	int save(OrgVol orgVol);

	List<OrgVol> getOrgVolListBySearch(int categoryId, String serchKeyword);

	// <My Page>
	// profile section ----------------------------
	List<OrgVol> getOrgVolListByCategoryId(int categoryId);

	// My page category section ----------------------------
	List<OrgVol> getMyApplyOrgVolList(int userId);

}
