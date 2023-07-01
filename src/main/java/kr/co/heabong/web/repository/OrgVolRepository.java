package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.UserWishView;

@Mapper
public interface OrgVolRepository {
	List<OrgVol> findAll();

	List<OrgVol> findByOffsetAndSize(int offset, int size);

	List<OrgVol> findByOrgIdAndStatus(int orgId, String status);
	
	List<OrgVol> findByOrgId(int orgId, String status);

	List<OrgVol> findByAddress(String address);

	int save(OrgVol orgVol);

	int update(OrgVol orgVol);

	OrgVol findById(int id);

	List<OrgVol> FindOrgVolListByCategoryId(int categoryId);

	List<OrgVol> FindOrgVolListBySearch(Integer categoryId, String serchKeyword);

	int delete(int id);

	List<OrgVol> FindMyApplyOrgVolListById(int userId);

	/* 기관 사진 */
	List<String> getPhotoList(int orgId);

	//북마크
	List<UserWishView> findViewAll(Integer userId,Integer volCategoryId,String title);
	
	List<UserWishView> FindViewByCategoryId(Integer volCategoryId);
	List<UserWishView> FindViewBySearch(Integer volCategoryId,String title);
	

}
