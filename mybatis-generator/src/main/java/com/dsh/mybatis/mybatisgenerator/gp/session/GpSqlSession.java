package com.dsh.mybatis.mybatisgenerator.gp.session;

import com.dsh.mybatis.mybatisgenerator.gp.config.GpConfiguration;
import com.dsh.mybatis.mybatisgenerator.gp.config.MapperRegistry;
import com.dsh.mybatis.mybatisgenerator.gp.executor.Executor;
import com.dsh.mybatis.mybatisgenerator.gp.mapper.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_13:14
 */
public class GpSqlSession {
    private GpConfiguration configuration;
    private Executor executor;

    public GpConfiguration getConfiguration() {
        return configuration;
    }

    public Executor getExecutor() {
        return executor;
    }

    //关联起来
    public GpSqlSession(GpConfiguration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},new MapperProxy(this,clazz));
    }

    public <T> T selectOne(MapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        return executor.query(mapperData, parameter);
    }
}
