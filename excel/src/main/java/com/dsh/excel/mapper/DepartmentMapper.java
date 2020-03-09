package com.dsh.excel.mapper;

import com.dsh.excel.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(Integer id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
}