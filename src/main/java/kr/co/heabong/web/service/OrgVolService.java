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

	int edit(OrgVol orgVol);

	List<OrgVol> getOrgVolListByCategoryId(int categoryId);

	List<OrgVol> getOrgVolListBySearch(Integer categoryId, String serchKeyword);

	int delete(int id);

	List<OrgVol> getMyApplyOrgVolList(int userId);
	
	/* 기관 사진 */
	List<String> getPhotoList(int orgId);
	
	int calculateRestDate(String dateString);
}
