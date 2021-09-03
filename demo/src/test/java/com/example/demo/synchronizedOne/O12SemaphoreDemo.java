package com.example.demo.synchronizedOne;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Semaphore;

public class O12SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Thread.sleep(5000);
                    System.out.println("线程1执行");
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("线程2执行");
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
