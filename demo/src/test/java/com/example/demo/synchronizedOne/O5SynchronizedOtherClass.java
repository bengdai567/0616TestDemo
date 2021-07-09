package com.example.demo.synchronizedOne;

import java.util.concurrent.TimeUnit;

public class O5SynchronizedOtherClass {
    Object object = new Object();
    void m1(){
        int count =0;
        synchronized(object){
            System.out.println(Thread.currentThread().getName()+"start");
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(count);
            }
        }
    }

    public static void main(String[] args) {
        O5SynchronizedOtherClass otherClass = new O5SynchronizedOtherClass();
        new Thread(otherClass::m1,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        otherClass.object = new Object();
        new Thread(otherClass::m1,"t2").start();
    }
}
