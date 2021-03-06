package com.example.demo.synchronizedOne;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class O9CyclicBarier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("===================");
            }
        });
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(new Runnable() {
                final int s = finalI;
                @Override
                public void run() {
                    try {
                        System.out.println(s);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
