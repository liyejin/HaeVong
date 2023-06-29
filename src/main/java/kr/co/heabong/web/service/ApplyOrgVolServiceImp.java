package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.ApplyOrgVol;
import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.UserApplyView;
import kr.co.heabong.web.repository.ApplyOrgVolRepository;
import kr.co.heabong.web.repository.OrgVolRepository;
import kr.co.heabong.web.repository.UserApplyViewRepository;
import kr.co.heabong.web.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ApplyOrgVolServiceImp implements ApplyOrgVolService {

	@Autowired
	ApplyOrgVolRepository repository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrgVolRepository orgVolRepository;
	@Autowired
	UserApplyViewRepository userApplyViewRepository;

	@Override
	public List<User> getApplyList(int orgVolId) {
		List<User> userList = new ArrayList<>();
		List<ApplyOrgVol> applyVolList = repository.findByOrgVolId(orgVolId);
		for (ApplyOrgVol a : applyVolList) {
			int userId = a.getUserId();
			User user = userRepository.findById(userId);// 딱 하나의 아이디로 찾은 유저
			userList.add(user);
		}
		// List<Map<String,object>> list -> 예를들어 유저리스트랑 어플라이 리스트 담을 수있다. "userApply", 객체
		return userList;
	}

	@Override
	public List<UserApplyView> getApplicantlList(int orgVolId) {

		List<UserApplyView> applyVolList = userApplyViewRepository.findByorgVolId(orgVolId);

		return applyVolList;
	}

	@Override
	public int changeApplicantStatus(int orgVolId, int userId, int status) {
		ApplyOrgVol applyOrgVol = repository.get(orgVolId, userId);
		applyOrgVol.setStatus(status);
		int update = repository.update(applyOrgVol);
		return update;
	}

	//입력 성공 실패 여부 판단후,컨트롤러 전달
	@Override
	public ApplyOrgVol add(ApplyOrgVol applyOrgVol) {
		
		int result = repository.save(applyOrgVol);
		//승인일때
		if(result == 1) {
			//새로 넣은 결과
			ApplyOrgVol newOne = repository.lastOne();
			return newOne;
		}
			
		return null;
	}

	@Override
	public int delete(ApplyOrgVol applyOrgVol) {
		
		int result = repository.delete(applyOrgVol);
		
		return result;
	}
	
	

}
