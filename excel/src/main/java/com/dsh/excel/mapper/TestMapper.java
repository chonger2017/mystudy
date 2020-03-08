package com.dsh.excel.mapper;

import com.dsh.excel.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    Test selectByPrimaryKey(Integer id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);
}