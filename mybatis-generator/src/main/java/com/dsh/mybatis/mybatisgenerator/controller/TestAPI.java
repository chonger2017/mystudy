package com.dsh.mybatis.mybatisgenerator.controller;

import com.dsh.mybatis.mybatisgenerator.model.User;
import com.dsh.mybatis.mybatisgenerator.response.ResponseVO;
import com.dsh.mybatis.mybatisgenerator.service.IAuthService;
import com.dsh.mybatis.mybatisgenerator.service.IMemberManagerService;
import com.dsh.mybatis.mybatisgenerator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/testAPI")
public class TestAPI {

    @Autowired
    private IMemberManagerService memberManagerService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/test")
    public String test() {
        memberManagerService.add(null);
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User users) {
        authService.login(users);
        return "login";
    }

    @RequestMapping("/getAll")
    public ResponseVO<List<User>> getAll() {
        ResponseVO<List<User>> response = null;
        return ResponseVO.respok(userService.getAll());
    }

}
