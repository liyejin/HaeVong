package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.repository.OrgVolRepository;

@Service
public class OrgVolServiceImp implements OrgVolService{

	@Autowired
	private OrgVolRepository repository;
	
	@Override
	public List<OrgVol> getList() {
		// TODO Auto-generated method stub
		List<OrgVol> list = repository.findAll();
		return list;
		
	}

	@Override
	public List<OrgVol> getListByAddress(String address) {

		List<OrgVol> list = repository.findByAddress(address);
		return list;
	}

	@Override
	public List<OrgVol> getList(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrgVol getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<OrgVol> getList(int orgId, String status) {
		// Org id와 상태로 OrgVol 리스트 가져오기
		List<OrgVol> list = repository.findByOrgIdAndStatus(orgId, status);

		return list;
	}

	@Override
	public int save(OrgVol orgVol) {
		int save = repository.save(orgVol);
		return save;
	}

	@Override
	public OrgVol get() {
		// TODO Auto-generated method stub
		return null;
	}

}
