package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.enity.Users;
import com.dsh.mybatis.mybatisgenerator.model.Member;

public interface IAuthService {

    public Member login(Users users);

    public boolean logout(String loginName);
}
