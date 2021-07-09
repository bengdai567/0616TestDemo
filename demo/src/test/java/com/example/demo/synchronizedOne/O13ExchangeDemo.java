package com.example.demo.synchronizedOne;

import java.util.concurrent.Exchanger;

public class O13ExchangeDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = "one";
                    Thread.currentThread().setName(s);
                    String exchange = exchanger.exchange(s);
                    System.out.println("one切换后的结果："+exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = "two";
                    Thread.currentThread().setName(s);
                    String exchange = exchanger.exchange(s);
                    System.out.println("two切换后的结果："+exchange);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

    }
}