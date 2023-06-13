package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.OrgVol;
import kr.co.heabong.web.entity.VolCategory;

public interface VolCategoryService {
	List<VolCategory> getList();
	List<VolCategory> getCateList();
	List<OrgVol> getMainCategoryList();
}
