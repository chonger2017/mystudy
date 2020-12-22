package com.dsh.excel.service.impl;

import com.dsh.excel.event.CustomerEvent;
import com.dsh.excel.mapper.UserMapper;
import com.dsh.excel.model.User;
import com.dsh.excel.model.UserBean;
import com.dsh.excel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Override
    public void register(UserBean userBean) {
        applicationContext.publishEvent(new CustomerEvent(this, userBean));
    }
}
