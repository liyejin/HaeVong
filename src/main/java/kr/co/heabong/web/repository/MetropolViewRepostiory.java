package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.MetropolView;

@Mapper
public interface MetropolViewRepostiory {
	public List<MetropolView> findAll();
	public List<MetropolView> findByMetropolId(int metropolId);
}
