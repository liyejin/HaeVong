package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.repository.OrgRepository;

@Service
public class OrgServiceImp implements OrgService{

	@Autowired
	private OrgRepository repository;
	
	@Override
	public List<Org> getList() {
		// TODO Auto-generated method stub
		List <Org> list = repository.findAll();
		return list;
	}



	@Override
	public Org getById(int id){
		Org org = repository.findById(id);
		return org;
	}



	@Override
	public void setOrg(Org org) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Org org) {
		// TODO Auto-generated method stub
		
	}

}
