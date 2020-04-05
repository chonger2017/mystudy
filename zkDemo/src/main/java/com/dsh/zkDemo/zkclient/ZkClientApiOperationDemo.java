package com.dsh.zkDemo.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_20:54
 */
public class ZkClientApiOperationDemo {
    private final static String CONNECTING = "49.233.88.114:2181,49.233.88.114:2182,49.233.88.114:2183,49.233.88.114:2184";

    private static ZkClient getInstance() {
        return new ZkClient(CONNECTING, 5000);
    }

    public static void main(String[] args) throws Exception{
        ZkClient zkClient = getInstance();
//        zkClient.createEphemeral("/zkclient");
//        zkClient.createPersistent("/zkclient/zkclient1/zkclient1-1", true);
//        System.out.println("success");

//        zkClient.deleteRecursive("/node1");

        List<String> children = zkClient.getChildren("/node");
        System.out.println(children);

        // watcher
        zkClient.subscribeDataChanges("/node", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(s + "->" +o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

        zkClient.writeData("/node","node");
        TimeUnit.SECONDS.sleep(1);
    }
}
