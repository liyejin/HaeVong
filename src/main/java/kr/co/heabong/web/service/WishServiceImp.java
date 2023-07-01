package kr.co.heabong.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.User;
import kr.co.heabong.web.entity.Wish;
import kr.co.heabong.web.repository.OrgVolRepository;
import kr.co.heabong.web.repository.UserRepository;
import kr.co.heabong.web.repository.WishRepository;

@Service
public class WishServiceImp implements WishService {
	@Autowired
	private WishRepository repository;
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private OrgVolRepository orgVolRepository;
	

	@Override
	public void wish(User user, OrgVol OrgVol) {
		// TODO Auto-generated method stub

	}

	@Override
	public int apend(Wish wish) {
//		Wish wish = Wish.builder().userId(userId).orgVolId(orgVolId).build();
//		int save = repository.save(wish);
//		if (save == 1)
//			System.out.println("wish 성공");
		return repository.save(wish);
	}

	@Override
	public List<Wish> getListByUser(User user) {
		int userId = user.getId();
		List<Wish> list = repository.findByUserId(userId);

		return list;
	}

	@Override
	public List<OrgVol> getListByOrgVol(OrgVol orgVol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Wish> getListByUserId(int userId) {
		List<Wish> list = repository.findByUserId(userId);

		return list;
	}

	@Override
	public List<OrgVol> getOrgVolListByUser(int userId) {
		List<Wish> wishList = repository.findByUserId(userId);
		List<OrgVol> orgVolList = new ArrayList<>();
		for (Wish wish : wishList) {
			int orgVolId = wish.getOrgVolId();
			OrgVol orgVol = orgVolRepository.findById(orgVolId);
			orgVolList.add(orgVol);
		}
		return orgVolList;
	}

	@Override
	public void wish(int userId, int orgVolId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Wish wish) {
		
		return repository.delete(wish);
	}

}
