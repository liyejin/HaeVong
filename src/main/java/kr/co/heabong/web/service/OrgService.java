package kr.co.heabong.web.service;

import java.util.List;
import kr.co.heabong.web.entity.Org;
public interface OrgService {
	
	List <Org> getList();
	Org getById(int id);
	Org getByRegNum(String regNum);

	void setOrg(Org org);
	
	void delete(Org org);

	boolean isValid(String regNum, String pwd);
}
