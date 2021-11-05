package com.example.demo.queue;

import java.util.*;
import java.util.concurrent.*;

public class HelloArray {
    public static void main(String[] args) {
        //有界队列
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

class CopyOnWriteList{
    //CopyOnWrite(ArrayList并发问题的替代品)
    //写时复制：不加锁，写的时候直接复制一份，由复制后的老的地址指向新的，写效率低，读效率高
    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for(int i=0; i<ths.length; i++) {
            Runnable task = new Runnable() {

                @Override
                public void run() {
                    for(int i=0; i<1000; i++) lists.add("a" + r.nextInt(10000));
                }

            };
            ths[i] = new Thread(task);
        }


        runAndComputeTime(ths);

        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] ths) {
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2 - s1);
    }
}
//支持并发的容器，链表实现的队列，内部使用了大量cas，但是遍历比数组低，而且链表需要维护头指针和尾指针，头部加的时候要加锁，尾部拿的时候要加锁
class ConcurrentLinkedQueueTest{
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for(int i=0; i<10; i++) {
            strs.offer("a" + i);  //add
        }

        System.out.println(strs);

        System.out.println(strs.size());

        System.out.println(strs.poll());//取出即移除
        System.out.println(strs.size());
        System.out.println(strs.peek());//单纯取出
        System.out.println(strs.size());

    }
}

class LinkedBlockingQueueTest{
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
//    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(1);

    static Random r = new Random();

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            try {
                strs.put(i+"");
                System.out.println("===========");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        System.out.println("+++++++++++++++++++++");
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    strs.put("a" + i); //如果满了，就会等待
                    System.out.println("存"+"a" + i);
//                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take -" + strs.take()); //如果空了，就会等待
                        //即取一次，没有等待，然后生产那面线程放进去了，这里又拿到了，然后又会过去，而生产那面继续又放了第二个
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();

        }
    }
}
class ArrayBlockingQueueTest{
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }
        //strs.put("aaa"); //满了就会等待，程序阻塞
        //strs.add("aaa");
        //strs.offer("aaa");
        //在此队列的尾部插入指定的元素，如果 队列已满，则等待直到指定的等待时间后该空间是否可用，不可用过
        strs.offer("aaa", 1, TimeUnit.SECONDS);
        //默认取下标第0个
        System.out.println("==="+strs.peek());
        System.out.println(strs);
    }
}
class PriorityQueueTest{
    static PriorityQueue<String> strs = new PriorityQueue(1);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.add("a" + (9-i));
        }
        System.out.println(strs);
        //strs.offer("aaa");
        strs.offer("aaa");
        for (int i = 0; i < 10; i++) {
            //peek默认取下标为0的
            System.out.println(strs.poll());
        }
    }
}

class DelayQueueTest{
    static BlockingQueue<Delayed> strs = new DelayQueue();

    static class Mydelayd implements Delayed{
        String name;
        long runningTime;

        Mydelayd(String name, long rt) {
            this.name = name;
            this.runningTime = rt;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            long convert = unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
            //convert值为负值时，poll方法可以直接弹出来，若为正数时，则直接返回null
            //同样take方法负值时则直接弹出来，为正数时，等待直到当前时间为负数就可以拿到
//            System.out.println("=测===="+convert);
            return convert;
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }

        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();

        Mydelayd a = new Mydelayd("a", now - 1000);
        Mydelayd b = new Mydelayd("b", now - 2000);
        Mydelayd c = new Mydelayd("c", now - 3000);
        Mydelayd d = new Mydelayd("d", now - 4000);
//        Mydelayd a = new Mydelayd("a", now + 1000);
//        Mydelayd b = new Mydelayd("b", now + 2000);
//        Mydelayd c = new Mydelayd("c", now + 3000);
//        Mydelayd d = new Mydelayd("d", now + 4000);
        strs.put(b);strs.add(a);strs.add(d);strs.add(c);
        System.out.println(strs);
        for (int i = 0; i < 4; i++) {
            //peek默认取下标为0的
            System.out.println(strs.poll());
            System.out.println(strs.take());
        }
    }
}

class SynchronousQueueTest{
    static  SynchronousQueue<Object> strs = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //阻塞等待消费者消费
        //strs.put("bbb");
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}

class LinkedTransferQueueTest{
    static  LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.put("aaa"); //阻塞等待消费者消费,消费后释放线程
        strs.put("bbb"); //无界队列并不会阻塞
        //strs.add("aaa");
        System.out.println(strs.size());
    }
}


