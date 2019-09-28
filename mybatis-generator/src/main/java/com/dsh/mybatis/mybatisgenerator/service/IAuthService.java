package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.enity.User;
import com.dsh.mybatis.mybatisgenerator.model.Member;

public interface IAuthService {

    public Member login(User user);

    public boolean logout(String loginName);
}
