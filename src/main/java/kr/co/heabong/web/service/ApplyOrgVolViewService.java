package kr.co.heabong.web.service;

import java.util.List;

import kr.co.heabong.web.entity.ApplyOrgVolView;

public interface ApplyOrgVolViewService {

    // 신청내역
    List<ApplyOrgVolView> getApplyVolList(int uid);

    // 참여내역(지난내역)
    List<ApplyOrgVolView> getAttendVolList(int uid);

}
