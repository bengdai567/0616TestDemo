package com.example.demo;

public class testOnetestOne {
    private static int count = 10;

    public synchronized static void m() { //这里等同于synchronized(FineCoarseLock.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized(testOnetestOne.class) { //
            count --;
        }
    }

    public static void main(String[] args) {
        new Thread(()->mm()).start();
        new Thread(()->m()).start();
    }
}
