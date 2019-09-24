package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.model.Member;

public interface IAuthService {

    public Member login(String loginName, String loginPassword);

    public boolean logout(String loginName);
}
