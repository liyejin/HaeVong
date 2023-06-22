package kr.co.heabong.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.heabong.web.entity.UserVol;
import kr.co.heabong.web.service.UserService;
import kr.co.heabong.web.service.UserVolService;

@RestController("apiUserController")
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserVolService volService;

    @GetMapping
    public List<UserVol> list(
            @RequestParam(name = "uid", required = false) Integer id,
            @RequestParam(name = "s", required = false) Integer Status) {
        List<UserVol> list = volService.getMyApplyUserVolList(id);
        return list;
    }

}
