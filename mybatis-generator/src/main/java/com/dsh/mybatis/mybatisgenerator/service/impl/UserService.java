package com.dsh.mybatis.mybatisgenerator.service.impl;

import com.dsh.mybatis.mybatisgenerator.mapper.UserMapper;
import com.dsh.mybatis.mybatisgenerator.model.User;
import com.dsh.mybatis.mybatisgenerator.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-08_14:12
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }
}
