package com.dsh.mybatis.mybatisgenerator.gp.mapper;

import com.dsh.mybatis.mybatisgenerator.gp.config.MapperRegistry;
import com.dsh.mybatis.mybatisgenerator.gp.session.GpSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_13:23
 */
public class MapperProxy<T> implements InvocationHandler {
    private final GpSqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(GpSqlSession gpSqlSession, Class<T> clazz) {
        this.sqlSession = gpSqlSession;
        this.mapperInterface = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistry.MapperData mapperData =
                sqlSession.getConfiguration()
                        .getMapperRegistry()
                        .get(method.getDeclaringClass().getName() + "." + method.getName());
        if (null != mapperData) {
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }
        return null;
    }
}
