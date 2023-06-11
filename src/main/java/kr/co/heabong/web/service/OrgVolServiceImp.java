package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
}
