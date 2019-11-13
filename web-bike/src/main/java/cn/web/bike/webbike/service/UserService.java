package cn.web.bike.webbike.service;

import cn.web.bike.webbike.domain.User;

public interface UserService {

    boolean sendMsg(String nationCode, String phoneNum);

    boolean verify(String phoneNum, String verifyCode);

    void register(User user);
}
