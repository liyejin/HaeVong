package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.repository.OrgVolRepository;
@Service
public class OrgVolServiceImpl implements OrgVolService {
	@Autowired
	OrgVolRepository repository;
	@Override
	public List<OrgVol> getList() {
		List<OrgVol> list = repository.findAll();
		return list;
	}

	@Override
	public OrgVol get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

}
