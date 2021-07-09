package com.example.demo.synchronizedOne;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class O8AtomicLongAdderComPare {
    static Object object = new Object();
    static int count = 0;

    static AtomicLong atomicLong = new AtomicLong(0L);
    static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[1000];
        objectDemo(threads);
        System.out.println("普通方式运行"+count);

        AtomicLongDemo(threads);
        System.out.println("tomic方式运行"+atomicLong.get());

        LongAdderDemo(threads);
        System.out.println("longAdder运行"+longAdder.longValue());
    }

    private static void objectDemo(Thread[] threads) throws InterruptedException, BrokenBarrierException {
//        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        synchronized (object){
                            count++;
                        }
                    }
//                    countDownLatch.countDown();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) {thread.start();};
//        countDownLatch.await();
//        for (Thread thread : threads) thread.join();
        long end = System.currentTimeMillis();
//        timeDemo();
        System.out.println("普通运行时间"+(end-start));
    }

    private static void AtomicLongDemo(Thread[] threads) throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {for (int j = 0; j < 10000; j++) {
                atomicLong.incrementAndGet();
            }});
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        long end = System.currentTimeMillis();
//        timeDemo();
        System.out.println("tomic运行时间"+(end-start));
    }

    private static void LongAdderDemo(Thread[] threads) throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> {for (int j = 0; j < 1000; j++) longAdder.increment();});
        };
        long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        long end = System.currentTimeMillis();
//        timeDemo();
        System.out.println("longAdder运行时间"+(end-start));
    }


    private static void timeDemo(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
