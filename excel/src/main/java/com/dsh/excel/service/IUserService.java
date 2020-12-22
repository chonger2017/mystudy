package com.dsh.excel.service;

import com.dsh.excel.model.User;
import com.dsh.excel.model.UserBean;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_13:08
 */
public interface IUserService {
    List<User> getAll();

    void register(UserBean userBean);
}
