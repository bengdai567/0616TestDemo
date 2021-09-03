package com.example.demo.synchronizedOne.anli;

import java.util.concurrent.atomic.AtomicInteger;

public class ZidingyiSyc {
//    static AtomicInteger count = new AtomicInteger() ;
    static int count = 0 ;
    public static void main(String[] args) throws InterruptedException {
        MLock mLock = new MLock();
        Thread[] threads = new Thread[100000];
        for (int i = 0; i < threads.length; i++) {
            int i1= i;
            threads[i] = new Thread(() -> {
                mLock.lock();
                count += i1;
//                count.addAndGet(i1);
                mLock.unlock();
            }, "t1");
        }
        for (Thread thread : threads) {
            thread.start();
            thread.join();
        }
        System.out.println(count);


      /*  new Thread(()->{
            for (int i = 0; i < 100000; i++) {
//                mLock.lock();
                count.addAndGet(i);
//                mLock.unlock();
            }
            System.out.println(count.getAndDecrement());
        },"t1").start();*/
    }
}
