package com.dsh.mybatis.mybatisgenerator.mapper;

import com.dsh.mybatis.mybatisgenerator.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}