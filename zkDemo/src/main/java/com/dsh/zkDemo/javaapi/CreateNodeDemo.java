package com.dsh.zkDemo.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_17:15
 */
public class CreateNodeDemo implements Watcher{
    private final static String connectstring = "49.233.88.114:2181,49.233.88.114:2182,49.233.88.114:2183,49.233.88.114:2184";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zookeeper;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zookeeper = new ZooKeeper(connectstring, 5000, new CreateNodeDemo());
        countDownLatch.await();

        System.out.println("1-->" +zookeeper.getState());

        /*// 创建节点
        String result = zookeeper.create("/mic", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        // 增加一个watch
        zookeeper.getData("/mic", new CreateNodeDemo(), stat);
        System.out.println("创建成功result=" + result);

        //修改数据
        zookeeper.setData("/mic", "mic123".getBytes(), -1);
        Thread.sleep(2000);
        System.out.println("修改后的值：");

        // 删除节点
        zookeeper.delete("/mic", -1);
        Thread.sleep(2000);

        System.out.println("=============================================");

        // 创建节点和子节点
        String path = "/node1";
        zookeeper.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        TimeUnit.SECONDS.sleep(1);

        Stat stat1 = zookeeper.exists(path + "/node1", true);
        if (stat1 == null) {
            // 表示节点不存在
            zookeeper.create(path + "/node1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            TimeUnit.SECONDS.sleep(1);
        }
        // 修改子路径
        zookeeper.setData(path + "/node1", "123".getBytes(), -1);
        TimeUnit.SECONDS.sleep(1);*/

        List<String> children = zookeeper.getChildren("/node", true);
        System.out.println(children);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // 如果连接状态是连接成功的，
        if (watchedEvent.getState()== Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
            System.out.println(watchedEvent.getState() +"-->"+ watchedEvent.getType());

            if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                try {
                    System.out.println("数据变更触发路径："+watchedEvent.getPath() +"->改变后的值："+
                            zookeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("子节点数据变更触发路径："+watchedEvent.getPath() +"->创建节点的值："+
                            zookeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                try {
                    System.out.println("节点创建路径："+watchedEvent.getPath() +"->创建节点的值："+
                            zookeeper.getData(watchedEvent.getPath(), true, stat));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                System.out.println("节点删除路径："+watchedEvent.getPath());
            }
        }
        System.out.println("2-->" +watchedEvent.getState());
    }
}
