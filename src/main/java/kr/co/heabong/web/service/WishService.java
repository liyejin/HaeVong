package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.Wish;

public interface WishService {
	
	void wish(User user, OrgVol OrgVol);
	void wish(int userId, int orgVolId);
	List<Wish> getListByUser(User user);
	List<OrgVol> getListByOrgVol(OrgVol orgVol);
	List<Wish> getListByUserId(int userId);
	List<OrgVol> getOrgVolListByUser(int userId);
	int apend(Wish wish);
	int delete(Wish wish);
	

}
