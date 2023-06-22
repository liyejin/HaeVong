package kr.co.heabong.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.heabong.web.entity.ApplyOrgVolView;
import kr.co.heabong.web.repository.ApplyOrgVolViewRepository;

@Service
public class ApplyOrgVolViewServiceImp implements ApplyOrgVolViewService {

    @Autowired
    ApplyOrgVolViewRepository repository;

    @Override
    public List<ApplyOrgVolView> getApplyVolList(int uid) {
        return repository.findMyApplyOrgVolList(uid);
    }

    @Override
    public List<ApplyOrgVolView> getAttendVolList(int uid) {
        return repository.findMyAttendOrgVolList(uid);
    }

}
