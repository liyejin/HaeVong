package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.MetropolView;

public interface MetropolViewService {

		List<MetropolView> getListByMetropolId(int metropolId);
}
