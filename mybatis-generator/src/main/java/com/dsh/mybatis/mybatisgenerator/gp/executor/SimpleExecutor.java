package com.dsh.mybatis.mybatisgenerator.gp.executor;

import com.dsh.mybatis.mybatisgenerator.gp.config.GpConfiguration;
import com.dsh.mybatis.mybatisgenerator.gp.config.MapperRegistry;
import com.dsh.mybatis.mybatisgenerator.gp.statement.StatementHandler;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_15:38
 */
public class SimpleExecutor implements Executor {
    private GpConfiguration configuration;

    public SimpleExecutor(GpConfiguration configuration) {
        this.configuration = configuration;
    }

    public GpConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(GpConfiguration configuration) {
        this.configuration = configuration;
    }

    public <E> E query(MapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        StatementHandler handler = new StatementHandler(configuration);
        return (E) handler.query(mapperData, parameter);
    }
}
