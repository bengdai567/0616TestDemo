package com.example.demo.synchronizedOne;

import java.util.concurrent.TimeUnit;

public class O2parentClass {
    synchronized void s1(){
        System.out.println("父类开售");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("父结束");
    }

    public static void main(String[] args) {
        new O2parentClass().s1();
    }
}
