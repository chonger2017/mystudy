package com.example.demo.mutilpletask.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyAndWaitDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ThreadWaitDemo threadWait = new ThreadWaitDemo(lock, condition);
        threadWait.start();
        ThreadNotifyDemo threadNotify = new ThreadNotifyDemo(lock, condition);
        threadNotify.start();
    }
}
