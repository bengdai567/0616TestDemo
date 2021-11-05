package com.example.demo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
     /*   condition.await();
        condition.signal();*/
//        new Thread(()->mm()).start();
//        new Thread(()->m()).start();
        BigDecimal s = new BigDecimal(10000001001.0001);
        DecimalFormat df = new DecimalFormat("#0.####");
//        BigDecimal bigDecimal = s.stripTrailingZeros();

        System.out.println( df.format(s));
//        System.out.println(bigDecimal.toString());

    }
}
