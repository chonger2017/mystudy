package cn.web.bike.webbike.controller;

import cn.web.bike.webbike.domain.User;
import cn.web.bike.webbike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/genCode")
    @ResponseBody
    public Boolean genVerifyCode(String nationCode, String phoneNum) {
        System.out.println(nationCode+":"+phoneNum);
        boolean flag = userService.sendMsg(nationCode, phoneNum);
        return flag;
    }

    @RequestMapping("/user/verify")
    @ResponseBody
    public Boolean verify(String phoneNum, String verifyCode){
        System.out.println(phoneNum+":"+verifyCode);
        boolean flag = userService.verify(phoneNum, verifyCode);
        return flag;
    }

    @RequestMapping("/user/register")
    @ResponseBody
    public Boolean register(@RequestBody User user) {
        System.out.println(user);
        boolean flag = true;
        try {
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
