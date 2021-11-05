package com.example.demo.queue;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 在ConcurrentHashMap中每次插入时都进行了cas判断，所以较慢，而ConcurrentSkipListMap
 * 在其内部时跳表方式，即需要按照一定规则进行存储的，所以会比ConcurrentHashMap插入慢
 * hashTable是作为一个重量锁的队列，内部维护了重量锁，相对于上面两个少数量前，差不多，
 * 对于后面来看是会存在一个递增的N（O）的级别，
 * hashMap是内部时线程不安全的，所以对于插入得效率相较于上面会更快一些
 *
 */
public class ConcurentMap {
    public static void main(String[] args) {
//        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String, String> map = new ConcurrentSkipListMap<>(); //߲
        Map<String, String> map = new Hashtable<>();
//        Map<String, String> map = new HashMap<>(); //Collections.synchronizedXXX
        //TreeMap
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        long start = System.currentTimeMillis();
        for(int i=0; i<ths.length; i++) {
            ths[i] = new Thread(()->{
                for(int j=0; j<10000; j++) map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(t->t.start());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(map.size());
    }
}
