package com.dsh.mybatis.mybatisgenerator.gp.config;

import java.io.IOException;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2019-12-22_13:15
 */
public class GpConfiguration {
    private String scanPath;

    private MapperRegistry mapperRegistry = new MapperRegistry();

    public GpConfiguration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public void build() throws IOException {
        if (scanPath == null || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required");
        }
    }
}
