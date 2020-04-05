package com.dsh.zkDemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_21:54
 */
public class CuratorClientUtil {
    private final static String CONNECTING = "49.233.88.114:2181,49.233.88.114:2182,49.233.88.114:2183,49.233.88.114:2184";

    private static CuratorFramework curatorFramework;

    public static CuratorFramework getInstance() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.
                newClient(CONNECTING, 5000, 5000,
                        new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        return curatorFramework;
    }
}
