package com.dsh.mybatis.mybatisgenerator.mapper;

import com.dsh.mybatis.mybatisgenerator.model.Test;
import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    Test selectByPrimaryKey(Integer id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);
}