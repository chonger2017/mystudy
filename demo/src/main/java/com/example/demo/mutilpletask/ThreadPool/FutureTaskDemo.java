package com.example.demo.mutilpletask.ThreadPool;

import java.util.Map;
import java.util.concurrent.*;

public class FutureTaskDemo {
    private final ConcurrentMap<Object, Future<String>> taskCache =
            new ConcurrentHashMap<Object, Future<String>>();

    private String executionTask(final String taskName)
            throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName);// 1.1,2.1
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    public String call() throws InterruptedException {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<String>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);// 1.3
                System.out.println(taskCache.size()+"-----");
                System.out.println(future+"=====");
                if (future == null) {
                    System.out.println("future is null");
                    future = futureTask;
                    futureTask.run(); // 1.4执行任务
                }
            }
            try {
                return future.get();// 1.5,2.2
            }catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        FutureTaskDemo futureTask = new FutureTaskDemo();
//        String first = futureTask.executionTask("first");
//        System.out.println(first);
        //测试一下currentHashMap.putIfAbsent()

        Map<Long, String> clientMap = new ConcurrentHashMap<>();

        System.out.println("首先打印空的clientMap");
        System.out.println("clientMap: " + clientMap);
        System.out.println();

        //在空的clientMap中添加一个新的记录
        System.out.println("在空的clientMap中添加一个新的记录");
        System.out.println("添加之前的clientMap: " + clientMap);
        long netId = 1234567L;
        String str1 = "michael";
        String result = clientMap.putIfAbsent(netId, str1);
        System.out.println("添加之后的clientMap: " + clientMap);
        System.out.println("查看返回值result: " + result);
        System.out.println();

        //重复添加
        str1 = "daniel";
        System.out.println("重复添加上一次的记录");
        System.out.println("添加之前的clientMap: " + clientMap);
        String result2 = clientMap.putIfAbsent(netId, str1);
        System.out.println("添加之后的clientMap: " + clientMap);
        System.out.println("查看返回值result: " + result2);
        System.out.println();

        //修改concurrentHashMap中的某个值
        clientMap.replace(netId, str1);
        System.out.println("replace后的值为：" + clientMap);
    }
}
