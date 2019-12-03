package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.model.User;

public interface IMemberManagerService {

    public boolean add(User member);

    public boolean remove(long id) throws Exception;

    public boolean modify(User member);

    public boolean query(String loginName);
}
