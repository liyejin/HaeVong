package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.VolCategory;

public interface VolCategoryService {
	List<VolCategory> getList();

	VolCategory getCategory();

	void setCategory();
}