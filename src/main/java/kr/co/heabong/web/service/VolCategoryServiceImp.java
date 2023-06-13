package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.VolCategory;
import kr.co.heabong.web.repository.VolCategoryRepository;
@Service
public class VolCategoryServiceImp implements VolCategoryService{

	@Autowired
	public VolCategoryRepository repository;
	
	@Override
	public List<VolCategory> getList() {
		// TODO Auto-generated method stub
		
		
		List <VolCategory> list = repository.findAll();
		return list;
	}
	
	@Override
	public List<VolCategory> getCateList() {
		List<VolCategory> list = repository.findAll();
		return list;
	}

	@Override
	public List<OrgVol> getMainCategoryList() {
		List<OrgVol> list = repository.findByCategoryMainPost(4);		
		return list;
	}
	


}
