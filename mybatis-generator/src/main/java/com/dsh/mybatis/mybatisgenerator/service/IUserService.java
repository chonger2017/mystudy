package com.dsh.mybatis.mybatisgenerator.service;

import com.dsh.mybatis.mybatisgenerator.model.User;
import com.dsh.mybatis.mybatisgenerator.model.param.UserVO;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-08_14:12
 */
public interface IUserService {
    List<User> getAll();

    List<User> getUser(UserVO userVO);
}
