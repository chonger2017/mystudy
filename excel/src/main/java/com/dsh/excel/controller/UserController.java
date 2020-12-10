package com.dsh.excel.controller;

import com.dsh.excel.model.User;
import com.dsh.excel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getAll(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String header = request.getHeader("X-Real_IP");
        String in = request.getHeader("interface_version");
        System.out.println(remoteAddr);
        System.out.println(header);
        System.out.println(in);
        HashMap<String, String> ele = new HashMap<>();
        ele.put("111", "111");
        return userService.getAll();
    }
}
