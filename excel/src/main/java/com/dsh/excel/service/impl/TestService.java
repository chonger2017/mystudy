package com.dsh.excel.service.impl;

import com.dsh.excel.mapper.TestMapper;
import com.dsh.excel.model.Test;
import com.dsh.excel.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-03-08_18:33
 */
@Service
public class TestService implements ITestService {
    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> getAll() {
        return testMapper.selectAll();
    }
}
