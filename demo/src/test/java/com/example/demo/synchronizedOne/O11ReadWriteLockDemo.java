package com.example.demo.synchronizedOne;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class O11ReadWriteLockDemo {
    static Lock lock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readlock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    public static void readMet(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("进行读取");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void writeMet(Lock lock,int value){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("进行写"+value);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable read = ()->readMet(readlock);
        Runnable writes = ()->writeMet(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) new Thread(read).start();
        for (int i = 0; i < 2; i++) new Thread(writes).start();
    }
}
