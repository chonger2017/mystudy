package com.dsh.mybatis.mybatisgenerator.controller;

import com.dsh.mybatis.mybatisgenerator.service.IMemberManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testAPI")
public class TestAPI {

    @Autowired
    private IMemberManagerService memberManagerService;

    @RequestMapping("/test")
    public String test() {
        memberManagerService.add(null);
        return "test";
    }
}
