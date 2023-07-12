package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.OrgVolAddressView;
import kr.co.heabong.web.entity.UserWishView;

@Mapper
public interface OrgVolRepository {
	/*기관 메인*/
	int countHaeVong(int orgId);

	List<OrgVol> findByOffsetAndSize(int offset, int size);

	List<OrgVol> findByOrgIdAndStatus(int orgId, String status);
	
	List<OrgVol> findByOrgId(int orgId, String status);
	
	int findIngOrgVol(int orgId);

	List<OrgVol> findByAddress(String roadAddress);

	int save(OrgVol orgVol);

	int update(OrgVol orgVol);

	OrgVol findById(int id);
	
	List<OrgVol> findAllList();
	
	List<OrgVol> findAll(int offset);

	List<OrgVol> FindOrgVolListByCategoryId(int categoryId,int offset);

	List<OrgVol> FindOrgVolListBySearch(Integer categoryId, String serchKeyword,int offset);

	int delete(int id);

	List<OrgVol> FindMyApplyOrgVolListById(int userId);

	/* 기관 사진 */
	List<String> getPhotoList(int orgId);

	//북마크
	List<UserWishView> findViewAll(Integer userId,Integer volCategoryId,String title,Integer orgVolId,int offset);
	List<UserWishView> FindViewByCategoryId(Integer volCategoryId);
	List<UserWishView> FindViewBySearch(Integer volCategoryId,String title);
	UserWishView findViewById(Integer id);
	int countBookmarkUser(int orgVolId);

	List<UserWishView> findViewBySearch(String searchKeyword);
	List<OrgVolAddressView> findOrgVolRand();





}
