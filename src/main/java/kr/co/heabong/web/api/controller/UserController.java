package kr.co.heabong.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ResourceLoader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.heabong.web.entity.User;
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

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public List<UserVol> list(
            @RequestParam(name = "uid", required = false) Integer id,
            @RequestParam(name = "s", required = false) Integer Status) {
        List<UserVol> list = volService.getMyApplyUserVolList(id);
        return list;

    }

    @PutMapping("{id}/nickname")
    public User NicknameEdit(@RequestParam("userNickname") String nickname,
            @PathVariable int id) {

        User user = service.get(id);
        user.setNickname(nickname);
        service.update(user);
        return user;
    }

}
