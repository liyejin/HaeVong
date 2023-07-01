package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserWishView;

public interface OrgVolService {
	//전체보기처럼 다 가져오는애
	List<UserWishView> getView(Integer userId);
	//카테고리 가져오는애
	List<UserWishView> getViewOrgVolListByCategoryId(Integer userId,Integer volCategoryId);
	//검색까지 하는애
	List<UserWishView> getViewOrgVolListBySearch(Integer userId, String title);

	List<OrgVol> getList();
	List<OrgVol> getList(String address);

	List<OrgVol> getList(int orgId, String status);

	List<OrgVol> getOrgVolListByCategoryId(int categoryId);
	List<OrgVol> getOrgVolListBySearch(Integer categoryId, String serchKeyword);
	OrgVol getById(int id);

	List<OrgVol> getListByAddress(String address);

	OrgVol get();

	int save(OrgVol orgVol);

	int edit(OrgVol orgVol);


	int delete(int id);

	List<OrgVol> getMyApplyOrgVolList(int userId);
	
	/* 기관 사진 */
	List<String> getPhotoList(int orgId);
	
	int calculateRestDate(String dateString);
	
}
