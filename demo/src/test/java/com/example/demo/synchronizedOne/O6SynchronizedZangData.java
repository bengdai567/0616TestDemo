package com.example.demo.synchronizedOne;

import java.util.concurrent.TimeUnit;

public class O6SynchronizedZangData {
    private int count = 0;
    synchronized void s1(){
        System.out.println(Thread.currentThread().getName()+"start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count == 5) {
                int i = 1/0; //此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                System.out.println(i);
            }
        }
    }

     void s2(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                s1();
            }
        });

        new Thread(thread,"t1").start();
         try {
             TimeUnit.SECONDS.sleep(3);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
        new Thread(thread,"t2").start();
    }

    public static void main(String[] args) {
        new O6SynchronizedZangData().s2();
    }

}
