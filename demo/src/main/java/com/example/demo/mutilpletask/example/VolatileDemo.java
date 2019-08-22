package com.example.demo.mutilpletask.example;

public class VolatileDemo {
    private static int x = 0,y = 0;
    private static int a = 0,b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread t2 = new Thread(() -> {
            b = 1;
            y = a;
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("x=" + x +", y=" + y);

        /**
         * volatile 是干嘛的？
         * 1.可以保证可见性，防止指令重排序
         * 2.#lock -> 缓存所 （MESI）
         * 3. 内存屏障（防止内存重排序）
         *
         * 1. 对每个volatile 写操作的前面会插入 storestore barrier
         *     storestore 保证上面的普通写和后面的volatile写不重排序
         * 2. 对每个volatile 写操作的后面会插入 storeload barrier
         *     storeload 保证上面的volatile写和后面的volatile读或写不重排序
         * 3. 对每个volatile 读操作的后面会插入 loadload barrier
         *    loadload 保证后面的普通读和 前面的volatile读操作不重排序
         * 4. 对每个volitale 读操作的后面会插入 loadstore barrier
         *    loadstore 保证后面的普通写和前面的volatile读操作不重排序
         *
         * 使用场景 --> 线程关闭
         */
    }
}
