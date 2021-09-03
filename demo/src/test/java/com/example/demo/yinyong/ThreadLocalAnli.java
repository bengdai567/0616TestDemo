package com.example.demo.yinyong;

import java.util.concurrent.TimeUnit;

public class ThreadLocalAnli {
    //以空间换时间理论测试 即以局部变量针对某个线程，相当于挂载在某个线程上，
    public static void main(String[] args) {
        ThreadLocal<Person> local = new ThreadLocal<>();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(local.get());
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            local.set(new Person());
            System.out.println(local.get());
        }).start();
    }

}

class Person{
    String name = "zhangsan";
}
