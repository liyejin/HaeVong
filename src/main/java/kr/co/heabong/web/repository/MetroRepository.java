package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.Metropol;

@Mapper
public interface MetroRepository {

	List<Metropol> findAll();
	int findByName(String name); // 메트로 아이디
	String findNameById(int id);
	
}
