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
public class ApplyOrgVolServiceImp implements ApplyOrgVolService{

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
		List<User>userList = new ArrayList<>();
		List<ApplyOrgVol>applyVolList=	repository.findByOrgVolId(orgVolId); 
		for(ApplyOrgVol a : applyVolList ) {
		int userId = a.getUserId();
		User user=userRepository.findById(userId);// 딱 하나의 아이디로 찾은 유저
		userList.add(user);
		}
	//List<Map<String,object>>  list  -> 예를들어 유저리스트랑 어플라이 리스트 담을 수있다. "userApply", 객체        
		return userList;             
	}


	@Override
	public List<UserApplyView> getApplicantlList(int orgVolId) {
		
		List<UserApplyView>applyVolList=	userApplyViewRepository.findById(orgVolId);

		return applyVolList;
	}



}
