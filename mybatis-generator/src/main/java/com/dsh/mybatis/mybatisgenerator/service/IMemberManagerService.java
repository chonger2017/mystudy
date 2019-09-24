package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.model.Member;

public interface IMemberManagerService {

    public boolean add(Member member);

    public boolean remove(long id) throws Exception;

    public boolean modify(Member member);

    public boolean query(String loginName);
}
