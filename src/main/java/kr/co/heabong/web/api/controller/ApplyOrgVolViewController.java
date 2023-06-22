package kr.co.heabong.web.api.controller;

import java.util.List;

import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.ApplyOrgVolView;
import kr.co.heabong.web.security.config.MyUserDetails;
import kr.co.heabong.web.service.ApplyOrgVolViewService;

@RestController("apiApplyOrgVolViewController")
@RequestMapping("api/apply-org-vols")
public class ApplyOrgVolViewController {

    @Autowired
    ApplyOrgVolViewService service;

    @GetMapping
    public List<ApplyOrgVolView> list(@RequestParam(name = "s", required = false) Integer status, // 신청내역 0,지난내역 1
            @AuthenticationPrincipal MyUserDetails user) {

        List<ApplyOrgVolView> list = null;

        int uid = user.getId();
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());
        System.out.println(user.getId());

        if (status == null) {
            status = 0;
        }

        if (status == 0) {
            list = service.getApplyVolList(uid);
            System.out.println("if");
        } else if (status == 1) {
            list = service.getAttendVolList(uid);
            System.out.println("else if");
        }
        return list;
    }

}
