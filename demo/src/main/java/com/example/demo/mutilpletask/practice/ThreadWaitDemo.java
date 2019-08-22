package com.example.demo.mutilpletask.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadWaitDemo extends Thread {

    private Lock lock;
    private Condition condition;

    public ThreadWaitDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("开始执行 thread wait");
            condition.await(); // 会释放锁
            System.out.println("执行结束 thread wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
