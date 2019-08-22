package com.example.demo.mutilpletask.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 重入读写锁
 */
public class RWLockDemo {

    //共享锁---在同一时刻可以有多个线程获得锁
    //读锁  写锁  （读多写少，此种情况下比排他锁的效率高）

    static Map<String, Object> cacheMap = new HashMap<>();

    static ReentrantReadWriteLock rw = new ReentrantReadWriteLock();

    static Lock read = rw.readLock(); //读锁

    static Lock write = rw.writeLock(); //写锁

    //缓存的更新和读取的时候
    public static final Object get(String key) {
        read.lock();

        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    public static final Object set(String key, String value) {
        write.lock();

        try {
            return cacheMap.put(key,value);
        } finally {
            write.unlock();
        }
    }

}
