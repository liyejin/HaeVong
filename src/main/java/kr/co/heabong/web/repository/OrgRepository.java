package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Org;

@Mapper
public interface OrgRepository {
	List<Org> findAll();

	Org findById(int id);

	void update(int id);

	void save(Org org);

	void delete(Org org);
	
	Org findByRegNum(String regNum);
	
}
