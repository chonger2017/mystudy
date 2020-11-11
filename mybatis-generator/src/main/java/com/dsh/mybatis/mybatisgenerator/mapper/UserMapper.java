package com.dsh.mybatis.mybatisgenerator.mapper;

import com.dsh.mybatis.mybatisgenerator.model.User;
import com.dsh.mybatis.mybatisgenerator.model.param.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    List<User> getUser(@Param("userVO") UserVO userVO);

    int updateByPrimaryKey(User record);
}