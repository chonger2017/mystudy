package com.dsh.mybatis.mybatisgenerator.service.impl;

import com.dsh.mybatis.mybatisgenerator.model.User;
import com.dsh.mybatis.mybatisgenerator.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private static Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Override
    public User login(User users) {
        LOG.info("用户登陆");
        return null;
    }

    @Override
    public boolean logout(String loginName) {
        LOG.info("用户注销");
        return true;
    }
}
