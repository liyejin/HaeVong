package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.OrgVolAddressView;
import kr.co.heabong.web.entity.UserWishView;

public interface OrgVolService {

	//
	List<UserWishView> getView(Integer userId,Integer volCategoryId,String searchKeyword,Integer offset);

	//전체보기처럼 다 가져오는애
	List<UserWishView> getView(Integer userId,Integer offset);
	//전체보기 _ 좋아요유무
	List<UserWishView> getViewOrgVolByOrgVolId(Integer userId,Integer orgVolId,Integer offset);
	//카테고리 가져오는애
	List<UserWishView> getViewOrgVolListByCategoryId(Integer userId,Integer volCategoryId,Integer offset);
	//검색까지 하는애
	List<UserWishView> getViewOrgVolListBySearch(Integer userId, String title,Integer offset);
	
	UserWishView getViewById(Integer id);
	
	int getBooKmarkUser(int OrgVolId);

	List<OrgVol> getList(String address);
	List<OrgVol> getList(int orgId, String status);
	
	int ingOrgVol (int orgId);

	List<OrgVol> getAllList();
	List<OrgVol> getList(Integer page);
	List<OrgVol> getOrgVolListByCategoryId(int categoryId,Integer page);
	List<OrgVol> getOrgVolListBySearch(Integer categoryId, String serchKeyword,Integer page);
	
	List<OrgVol> getList(Integer offset, int size);
	
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
	int calculateDeadLineDate(String dateString,String deadLine);
	List<UserWishView> getVolListBySearch(Integer userId,String searchKeyword,Integer offset);
	List<UserWishView> getVolListBySearchNotLogin(String searchKeyword,Integer offset);
	List<OrgVolAddressView> getListByRandom();
	boolean checkApply(int id, int orgVolId);
	int getMetHaeVong(int id);

	
}
