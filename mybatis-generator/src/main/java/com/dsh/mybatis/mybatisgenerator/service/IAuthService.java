package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.model.User;

public interface IAuthService {

    public User login(User users);

    public boolean logout(String loginName);
}
