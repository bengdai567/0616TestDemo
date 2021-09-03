package com.example.demo.synchronizedOne.anli;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *  * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *  *
 *  * 使用wait和notify/notifyAll来实现
 */
public class RongqiThread<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;
    public synchronized void put(T t) {
        while(lists.size() == MAX) { //想想为什么用while而不是用if？
            try {
                this.wait(); //effective java
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        ++count;
        this.notifyAll(); //通知消费者线程进行消费
    }

    public synchronized T get() {
        T t = null;
        while(lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count --;
        this.notifyAll(); //通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        RongqiThread<String> c = new RongqiThread<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }

  /*  ReentrantLock lock = new ReentrantLock();
    Condition pro = lock.newCondition();
    Condition csu = lock.newCondition();
    public T get(){
        lock.lock();
        T t=null;
        try {
            while (lists.size()==0){
                csu.await();
            }
            t = lists.removeFirst();
            pro.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }
    public void put(T t){
        lock.lock();
        try {
            while (lists.size()==MAX){
                System.out.println(lists);
                pro.await();
            }
            lists.add(t);
            csu.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        RongqiThread<String> thread = new RongqiThread<String>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(thread.get());
                }
            },"t1").start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    thread.put(Thread.currentThread().getName()+"--> "+j);
                }
            },"t"+i).start();
        }
    }*/
}
