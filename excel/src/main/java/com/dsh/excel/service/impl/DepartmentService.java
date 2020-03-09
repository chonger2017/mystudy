package com.dsh.excel.service.impl;

import com.dsh.excel.mapper.DepartmentMapper;
import com.dsh.excel.model.Department;
import com.dsh.excel.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_21:06
 */
@Service
public class DepartmentService implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentMapper.selectAll();
    }
}
