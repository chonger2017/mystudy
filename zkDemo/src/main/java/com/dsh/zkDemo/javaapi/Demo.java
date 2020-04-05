package com.dsh.zkDemo.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_17:06
 */
public class Demo {
    private final static String connectstring = "49.233.88.114:2181,49.233.88.114:2182,49.233.88.114:2183,49.233.88.114:2184";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(connectstring, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                // 如果连接状态是连接成功的，
                if (watchedEvent.getState()== Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
                System.out.println(watchedEvent.getState());
            }
        });
        countDownLatch.await();

        System.out.println(zooKeeper.getState());
    }
}
