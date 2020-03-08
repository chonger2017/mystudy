package com.dsh.excel.controller;

import com.dsh.excel.model.User;
import com.dsh.excel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_13:20
 */
@RestController
@RequestMapping(value = "user/api")
public class UserController {
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }
}
