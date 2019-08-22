package com.example.demo.mutilpletask.example;

public class InterruptThread  {

    public static void main(String[] args) {
//        Thread thread = new Thread(new InterruptRunner(),"aa");
//        thread.start();
//        thread.interrupt();
        System.out.println("start");
        Runnable runnable = new InterruptRunner();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("end");
    }

    static class InterruptRunner implements Runnable{

        @Override
        public void run() {
//            System.out.println("当前的状态"+Thread.currentThread().isInterrupted());
//            System.out.println("当前的状态"+Thread.currentThread().isInterrupted());
//            System.out.println("当前的状态"+Thread.currentThread().isInterrupted());
//            System.out.println("当前状态"+Thread.interrupted());
//            System.out.println("当前状态"+Thread.interrupted());
//            System.out.println("当前状态"+Thread.interrupted());
            int i = 0;
            try {
                while (true) {
//                    if (Thread.currentThread().isInterrupted()) {
//                        System.out.println("已经是中断状态了，我要退出了");
                        Thread.sleep(100);
//                    }
                }
            } catch (InterruptedException e) {
                System.out.println("中断标志" + Thread.currentThread().isInterrupted());
                System.out.println("进入" + Thread.currentThread().getName() + "的exception方法， 然后中断");
                System.out.println(e);
                Thread.currentThread().interrupt();
                System.out.println("中断标志" + Thread.currentThread().isInterrupted());
            }
        }
    }
}
