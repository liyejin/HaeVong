package kr.co.heabong.web.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.entity.User;
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
	
	/*
	 * @Override public Date getBySignUpdate(int id){ Org org =
	 * repository.findById(id); Date signUpDate = org.getSignUpDate(); return
	 * signUpDate; }
	 */



	@Override
	public void setOrg(Org org) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void delete(Org org) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean isValid(String regNum, String pwd) {
		// TODO Auto-generated method stub
		Org org = repository.findByRegNum(regNum);
		
		if(org == null)
			return false;
		else if(!org.getPwd().equals(pwd))
			return false;
		
		return true;
	}


	
	@Override
	public Org getByRegNum(String regNum) {
		Org org = repository.findByRegNum(regNum);
		
		return org;
	}


	@Override
	public String getBySignUpdate(int id) {
		Org org = repository.findById(id); 
		String signUpDate = org.getSignUpDate();
		return signUpDate;
	}



	@Override
	public Org getByOrgPhoto(int id) {
		
		Org orgProfile = repository.findByOrgPhoto(id);
		return orgProfile;
	}



}
