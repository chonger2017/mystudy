package com.dsh.mybatis.mybatisgenerator.gp.executor;

import com.dsh.mybatis.mybatisgenerator.gp.config.GpConfiguration;
import com.dsh.mybatis.mybatisgenerator.gp.config.MapperRegistry;
import com.dsh.mybatis.mybatisgenerator.gp.statement.StatementHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_16:30
 */
public class CachingExecutor implements Executor {
    private GpConfiguration configuration;

    private SimpleExecutor delegate;

    private Map<String,Object> localCache = new HashMap();

    public CachingExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CachingExecutor(GpConfiguration configuration) {
        this.configuration = configuration;
    }

    public <E> E query(MapperRegistry.MapperData mapperData, Object parameter)
            throws Exception {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        Object result = localCache.get(mapperData.getSql());
        if( null != result){
            System.out.println("缓存命中");
            return (E)result;
        }
        result =  (E) delegate.query(mapperData,parameter);
        localCache.put(mapperData.getSql(),result);
        return (E)result;
    }
}
