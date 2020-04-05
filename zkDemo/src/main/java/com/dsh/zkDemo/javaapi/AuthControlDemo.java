package com.dsh.zkDemo.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_20:06
 */
public class AuthControlDemo implements Watcher {
    private final static String connectstring = "49.233.88.114:2181,49.233.88.114:2182,49.233.88.114:2183,49.233.88.114:2184";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    private static ZooKeeper zookeeper;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zookeeper = new ZooKeeper(connectstring, 5000, new AuthControlDemo());
        countDownLatch.await();
        ACL acl1 = new ACL(ZooDefs.Perms.CREATE, new Id("digest", "root:root"));
        ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.1.1"));

        List<ACL> list = new ArrayList<>();
        list.add(acl1);
        list.add(acl2);
        zookeeper.create("/auth1", "123".getBytes(), list, CreateMode.PERSISTENT);
        // acl(create/delete/admin/read/write)
        // 权限模式； ip/Digest(username:password)/world/super
        zookeeper.addAuthInfo("digest", "root:root".getBytes());

        zookeeper.create("/auth", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zookeeper.create("/auth/auth1-1", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper1 = new ZooKeeper(connectstring, 5000, new AuthControlDemo());
        countDownLatch2.await();
        zooKeeper1.delete("/auth",-1);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 如果连接状态是连接成功的，
        if (watchedEvent.getState()== Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
            countDownLatch2.countDown();
            System.out.println(watchedEvent.getState() +"-->"+ watchedEvent.getType());
        }
        System.out.println("2-->" +watchedEvent.getState());
    }
}
