package com.dsh.mybatis.mybatisgenerator.gp;

import com.dsh.mybatis.mybatisgenerator.gp.config.GpConfiguration;
import com.dsh.mybatis.mybatisgenerator.gp.executor.ExecutorFactory;
import com.dsh.mybatis.mybatisgenerator.gp.session.GpSqlSession;
import com.dsh.mybatis.mybatisgenerator.mapper.TestMapper;
import com.dsh.mybatis.mybatisgenerator.model.Test;
//import com.dsh.mybatis.mybatisgenerator.mapper.TestMapper;

import java.io.IOException;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_15:21
 */
public class BootStrap {
    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        GpConfiguration configuration = new GpConfiguration();
        configuration.setScanPath("com.dsh.mybatis.mybatisgenerator.mapper");
        configuration.build();
        GpSqlSession sqlSession = new GpSqlSession(configuration, ExecutorFactory.DEFAULT(configuration));
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        Test test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
