package com.dsh.zkDemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-05_12:54
 */
public class CuratorEventDemo {
    /**
     * 三种watcher来做节点的监听
     * pathCache 监听一个路径下子节点的创建，删除，节点数据跟新
     * nodeCache 监听一个节点的创建，更新，删除
     * TreeCache pathCache+nodeCache 的合体，监视路径下的创建，更新，删除事件
     * 缓存路径下的所有子节点的数据
     */
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtil.getInstance();
        /*NodeCache cache = new NodeCache(curatorFramework, "/curator", false);
        cache.start(true);

        cache.getListenable().addListener(() -> System.out.println("节点数据发生变化，结果为："
                + new String(cache.getCurrentData().getData())));
        curatorFramework.setData().forPath("/curator", "菲菲".getBytes());*/


        PathChildrenCache cacheChild = new PathChildrenCache(curatorFramework, "/event", true);
        cacheChild.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cacheChild.getListenable().addListener((curatorFramework1, pathChildrenCacheEvent) -> {
            switch (pathChildrenCacheEvent.getType()) {
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:break;
            }
        });

        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/event", "event".getBytes());
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/event/event1", "123".getBytes());
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.setData().forPath("/event/event1", "222".getBytes());
        TimeUnit.SECONDS.sleep(1);
        curatorFramework.delete().forPath("/event/event1");
        System.in.read();
    }
}
