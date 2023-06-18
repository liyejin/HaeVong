package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.heabong.web.entity.MetropolView;
import kr.co.heabong.web.repository.MetropolViewRepostiory;

public class MetropolViewServiceImp implements MetropolViewService {
	@Autowired
	private MetropolViewRepostiory repository;
	@Override
	public List<MetropolView> getListByMetropolId(int metropolId) {
		List<MetropolView> list = repository.findByMetropolId(metropolId);
		return list;
	}

}
