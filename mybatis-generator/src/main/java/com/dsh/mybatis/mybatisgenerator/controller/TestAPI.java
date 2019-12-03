package com.dsh.mybatis.mybatisgenerator.controller;

import com.dsh.mybatis.mybatisgenerator.enity.Users;
import com.dsh.mybatis.mybatisgenerator.service.IAuthService;
import com.dsh.mybatis.mybatisgenerator.service.IMemberManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testAPI")
public class TestAPI {

    @Autowired
    private IMemberManagerService memberManagerService;

    @Autowired
    private IAuthService authService;

    @RequestMapping("/test")
    public String test() {
        memberManagerService.add(null);
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody Users users) {
        authService.login(users);
        return "login";
    }

}
