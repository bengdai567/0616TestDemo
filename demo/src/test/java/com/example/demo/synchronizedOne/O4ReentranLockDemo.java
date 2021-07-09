package com.example.demo.synchronizedOne;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class O4ReentranLockDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                reentrantLock.lock();
                System.out.println("t1开始");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
//                TimeUnit.SECONDS.sleep(10);
                System.out.println("t1结束");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            } finally {
                reentrantLock.unlock();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
//            标识确保是否获取到锁
            boolean flag =true;
            try {
                reentrantLock.lockInterruptibly();
                //            标识确保是否获取到锁
//                boolean lock = reentrantLock.tryLock();
//                reentrantLock.lock();
                System.out.println("t2开始");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t2结束");
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                flag =false;
            } finally {
                if (flag)
                    reentrantLock.unlock();
            }
        });
        t2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
