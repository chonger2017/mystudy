package com.example.demo.mutilpletask.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.Executors.*;

public class ThreadPoolDemo implements Runnable{

    static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        /*//创建一个固定的线程池
        ExecutorService es = newFixedThreadPool(5);
        //创建只有一个线程的线程池
        ExecutorService singleThread = newSingleThreadExecutor();
        //不限制最大线程数
        ExecutorService threads = newCachedThreadPool();
        //定时器，延时执行的线程池
        ExecutorService scheduled = newScheduledThreadPool(5);*/
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ThreadPoolDemo());
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
