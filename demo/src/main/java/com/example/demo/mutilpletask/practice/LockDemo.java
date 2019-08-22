package com.example.demo.mutilpletask.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static Lock lock = new ReentrantLock();

    private static int count = 0;

    public static void incr() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        count ++;
        lock.unlock();
    }

    /**
     * Synchronized 和 lock 有什么区别
     * 一 -----Synchronized 是JVM 层面的一个关键字
     * lock是jdk 层面实现的一个api
     * 二 -----lock 比较灵活，可以控制锁的获取和释放
     *     syn 代码执行完会释放锁，出现异常也会释放锁
     * 三 ----- lock 可以判断锁的状态
     * 四 ----- Lock 有公平锁和非公平锁
     *          synchronize 是非公平锁
    */

}
