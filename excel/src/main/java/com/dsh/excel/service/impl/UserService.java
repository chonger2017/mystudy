package com.dsh.excel.service.impl;

import com.dsh.excel.mapper.UserMapper;
import com.dsh.excel.model.User;
import com.dsh.excel.service.IUserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_13:08
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
