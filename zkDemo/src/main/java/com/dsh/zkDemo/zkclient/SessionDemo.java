package com.dsh.zkDemo.zkclient;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_20:46
 */
public class SessionDemo {
    private final static String CONNECTING = "49.233.88.114:2181,49.233.88.114:2182,49.233.88.114:2183,49.233.88.114:2184";

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(CONNECTING, 4000);
        System.out.println(zkClient + "-> success");
    }
}
