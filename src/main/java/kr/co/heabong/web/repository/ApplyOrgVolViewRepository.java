package kr.co.heabong.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.heabong.web.entity.ApplyOrgVolView;

@Mapper
public interface ApplyOrgVolViewRepository {

    // 현재 신청 봉사
    public List<ApplyOrgVolView> findMyApplyOrgVolList(int uid);

    // 참여했던 봉사 (모집 글 내에 봉사일자가 오늘 날짜보다 지난날 일때)
    public List<ApplyOrgVolView> findMyAttendOrgVolList(int uid);

}
