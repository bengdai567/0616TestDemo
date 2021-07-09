package com.example.demo.synchronizedOne;

import java.util.concurrent.locks.LockSupport;

public class O14LockSupportDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始卡主");
                LockSupport.park();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("释放t1线程");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始释放t1");
                LockSupport.unpark(t1);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("释放t1后走的t2线程");
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
