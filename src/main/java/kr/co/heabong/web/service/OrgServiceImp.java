package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Org;
import kr.co.heabong.web.repository.OrgRepository;
@Service
public class OrgServiceImp implements OrgService {
	
	@Autowired
	private OrgRepository repository;

	@Override
	public List<Org> getList() {
		
		List<Org> list = repository.findAll();

		return list;
	}

	@Override
	public Org  getListById(int id) {
		
		Org org = repository.findById(2);
		
		return  org;
	}

//	@Override
//	public List<Org> getListByQuery(String query) {
//		
//		return null;
//	}


}
