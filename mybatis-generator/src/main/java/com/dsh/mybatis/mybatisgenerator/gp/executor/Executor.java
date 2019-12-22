package com.dsh.mybatis.mybatisgenerator.gp.executor;

import com.dsh.mybatis.mybatisgenerator.gp.config.MapperRegistry;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_13:16
 */
public interface Executor {

    <T> T query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception;
}
