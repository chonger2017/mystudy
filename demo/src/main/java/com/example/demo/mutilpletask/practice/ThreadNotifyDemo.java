package com.example.demo.mutilpletask.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadNotifyDemo extends Thread {

    private Lock lock;
    private Condition condition;

    public ThreadNotifyDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("开始执行 thread notify");
            condition.signal();
            System.out.println("执行结束 thread notify");
        } finally {
            lock.unlock();
        }
    }
}
