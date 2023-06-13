package kr.co.heabong.web.service;

import java.util.List;


import kr.co.heabong.web.entity.ApplyOrgVol;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserApplyView;

public interface ApplyOrgVolService {
	
	// applyorg 에 유저도 있고 orgvol id도 있는데 그중에 user 만 가져오겠다 
	List<User> getApplyList(int orgVolId);
	List<UserApplyView>getApplicantlList(int orgVolId);
	
}
