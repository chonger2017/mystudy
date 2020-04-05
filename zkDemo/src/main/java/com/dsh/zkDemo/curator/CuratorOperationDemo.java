package com.dsh.zkDemo.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-04-04_21:35
 */
public class CuratorOperationDemo {
    public static void main(String[] args) throws InterruptedException {
        CuratorFramework curatorFramework = CuratorClientUtil.getInstance();
        System.out.println("连接成功。。。。。。");

        //fluent 风格
        /**
         * 创建节点
         */
        /*try {
            String result = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                    .forPath("/curator/curator1/curator1-1", "123".getBytes());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /**
         * 删除节点
         */
        /*try {
            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/node");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /**
         * 查询
         */
        /*Stat stat = new Stat();
        try {
            byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/curator");
            System.out.println(new String(bytes,"UTF-8") + "--->stat:" +stat);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /**
         * 修改
         */
        /*try {
            Stat stat = curatorFramework.setData().forPath("/curator", "123".getBytes());
            System.out.println(stat);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        ExecutorService service = Executors.newFixedThreadPool(1);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        /**
         * 异步操作
         */
        /*try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println(Thread.currentThread().getName() + "->resultCode" + curatorEvent.getResultCode()+"-->"
                            + curatorEvent.getType());
                    countDownLatch.countDown();
                }
            },service).forPath("/mic","123".getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.await();
        service.shutdown();*/

        /**
         * 事务操作（curator独有的）
         */
        try {
            Collection<CuratorTransactionResult> result = curatorFramework.inTransaction().create().forPath("/trans", "111".getBytes()).and()
                    .setData().forPath("/curator", "222".getBytes()).and().commit();
            for (CuratorTransactionResult result1 : result) {
                System.out.println(result1.getForPath()+"->"+result1.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
