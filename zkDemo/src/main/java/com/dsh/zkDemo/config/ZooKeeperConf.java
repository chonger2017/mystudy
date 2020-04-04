package com.dsh.zkDemo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-01_20:42
 */
@Configuration
@Slf4j
public class ZooKeeperConf {
    @Value("${zk.url}")
    private String zkUrl;

    @Bean
    public CuratorFramework getCuratorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        //必须调用start开始连接ZooKeeper
        client.start();
        return client;
    }
}
