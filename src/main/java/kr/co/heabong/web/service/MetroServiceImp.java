package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.Metropol;
import kr.co.heabong.web.repository.MetroRepository;
@Service
public class MetroServiceImp implements MetroService {

	@Autowired
	private MetroRepository repository;
	
	
	
	public List<Metropol> getList(){
		List<Metropol> list = repository.findAll();
		return list;
	}



	@Override
	public int getById(String name) {
	int MetropolId = repository.findByName(name);
		return MetropolId;
	}



}
