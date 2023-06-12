package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Org;

@Mapper
public interface OrgRepository {
	List<Org> findAll();

	//List<Org> findAll(String query,Integer categoryId,int offset, int size);
	// 기관을 어떻게 조회를 할껀지 기준은 내가 정하는 것  
	
	Org findById(int id);

	int save(Org org);

	int update(Org org);

	int delete(int id);
}
