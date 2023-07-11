package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.ApplyOrgVol;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserApplyView;

public interface ApplyOrgVolService {

	// applyorg 에 유저도 있고 orgvol id도 있는데 그중에 user 만 가져오겠다
	List<User> getApplyList(int orgVolId);

	List<UserApplyView> getApplicantlList(int orgVolId);
	
	int changeApplicantStatus(int orgVolId, int userId, int status);
	
	//한명 신청할때마다 이 한줄이 삽입
	ApplyOrgVol add(ApplyOrgVol applyOrgVol);
	
	int delete(ApplyOrgVol applyOrgVol);

}