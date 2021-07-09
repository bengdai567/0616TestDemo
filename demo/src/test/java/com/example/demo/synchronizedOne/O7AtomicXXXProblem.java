package com.example.demo.synchronizedOne;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class O7AtomicXXXProblem {
   /* AtomicInteger atomicInteger = new AtomicInteger(0);

    void m1() {
        for (int i = 0; i < 10000; i++) {
            //incrementAndGet进行叠加相当于i++
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        AtomicXXXProblem atomicXXXProblem = new AtomicXXXProblem();
        ArrayList<Thread> arrayList = new ArrayList<Thread>();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(new Thread(atomicXXXProblem::m1, i + ""));
        }
        arrayList.forEach(e ->
                e.start()
        );
      *//*  arrayList.forEach(s -> {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*//*
        System.out.println(atomicXXXProblem.atomicInteger);
    }*/
    /*volatile*/ //int count1 = 0;

    AtomicInteger count = new AtomicInteger(0);

    synchronized void m() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10000; i++)
            //if count1.get() < 1000
            count.incrementAndGet(); //count1++
    }

    public static void main(String[] args) {
        O7AtomicXXXProblem t = new O7AtomicXXXProblem();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }
        threads.forEach((o) -> o.start());

       /* try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        System.out.println(t.count);

    }

}
