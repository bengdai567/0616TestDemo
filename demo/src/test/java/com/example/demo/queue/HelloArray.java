package com.example.demo.queue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class HelloArray {
    public static void main(String[] args) {
        Queue<Integer> q = new ArrayBlockingQueue<>(2);
        q.add(0);
        q.add(1);
//        q.add(2);
//        q.add(3);
        System.out.println(q);

        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(a).map(i->i+1).forEach(i->System.out.print(i + " "));

        System.out.println();
        Collection<Integer> c1 = new ArrayList();
        c1.add(1);
        c1.add(2);
        c1.add(3);
        c1.stream().forEach(System.out::println);

        List<Integer> c2 = new ArrayList<>();
        Set<Integer> c3 = new HashSet<>();
        Queue<Integer> c4 = new LinkedList<>();

    }
}
