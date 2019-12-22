package com.dsh.mybatis.mybatisgenerator.gp.config;

import com.dsh.mybatis.mybatisgenerator.model.Test;
import org.springframework.web.util.pattern.PathPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_15:14
 */
public class MapperRegistry {
    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    public MapperRegistry() {
        methodSqlMapping.put("com.dsh.mybatis.mybatisgenerator.mapper.TestMapper.selectByPrimaryKey",new MapperData("select * from test where id = %d", Test.class));
    }

    public class MapperData<T> {
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public Class<T> getType() {
            return type;
        }
    }

    public MapperData get(String nameSpace) {
        return methodSqlMapping.get(nameSpace);
    }
}
