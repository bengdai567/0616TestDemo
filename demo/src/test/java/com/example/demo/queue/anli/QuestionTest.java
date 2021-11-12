package com.example.demo.queue.anli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

//要求用线程顺序打印A1B2C3....Z26
public class QuestionTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Object> list = new ConcurrentLinkedQueue<>();
        char a = 'A';
        ConcurrentLinkedQueue<Object> list2 = new ConcurrentLinkedQueue<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }
        int size = list.size();
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    System.out.print(list2.poll());
                    System.out.println(list.poll());
                }
            }
        }, "p1");
        p1.start();
    }
}


class Question_LockSupportTest2 {
    static Thread p1;
    static Thread p2;
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }
        p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Object obj : list2) {
                    System.out.print(obj);
                    LockSupport.unpark(p2);
                    LockSupport.park();
                }
            }
        }, "p1");

        p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    LockSupport.park();
                    System.out.println(list.get(i));
                    LockSupport.unpark(p1);
                }
            }
        }, "p2");
        p1.start();
        p2.start();
    }
}

class Question_CasTest {
    //采用原子性方式
//    enum ReadyToRun{T1,T2};
//    static  String r = "T1";
    static int r = 1;
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Object obj : list2) {
//                    while (r.equals("T2")){}
                    while (r==2){}
                    System.out.print(obj);
//                    r = "T2";
                    r = 2;
                }
            }
        }, "p1");
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Object obj : list) {
//                    while(r.equals("T1")){}
                    while(r==1){}
                    System.out.println(obj);
//                    r = "T1";
                    r = 1;
                }
            }
        }, "p2");
        p1.start();
        p2.start();
    }
}

class Question_SynWaitTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        Object o = new Object();
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    for (Object obj : list2) {
                        o.notify();
                        try {
                            o.wait();//释放锁，进入等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(obj);
                    }
                    o.notify();
                }
            }
        }, "p1");
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    for (Object obj : list) {
                        o.notify();//获取锁释放队列
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(obj);
                    }
                    o.notify();
                }
            }
        }, "p2");
        p1.start();
        p2.start();
    }
}

class Question_SynWaitTest2 {
    //使用一个变量确保打印顺序问题
    private static /*volatile*/ boolean states = false;
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        Object o = new Object();
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    states = true;
                    for (Object obj : list2) {
                        System.out.print(obj);
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        }, "p1");
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    while (!states){
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (Object obj : list) {
                        System.out.println(obj);
                        try {
                            o.notify();
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    o.notify();
                }
            }
        }, "p2");
        p1.start();
        p2.start();
    }
}

class BlockingQueueTest{
    public static void main(String[] args) {
        BlockingQueue<Object> blockingQueueA = new ArrayBlockingQueue(1);
        BlockingQueue<Object> blockingQueueB = new ArrayBlockingQueue(1);
        //采用单个阻塞队列虽然会阻塞，但是伴随而来的是堆积的阻塞队列一瞬间释放后，直接全部打印出来，
        //所以采用两个阻塞队列，在一个阻塞队列释放的同时阻塞另一个，在通过另一个释放当前该阻塞队列
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        new Thread(()->{
            for (Object obj : list2) {
                try {
                    blockingQueueA.put("ok");
                    System.out.print(obj);
                    blockingQueueB.take();
//                    blockingQueue.put(obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (Object obj : list) {
                try {
                    blockingQueueA.take();
                    System.out.println(obj);
                    blockingQueueB.put("ok");
//                    System.out.print(blockingQueue.take());
//                    System.out.println(obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class AtomicIntegerTest{

    //利用cas的原子性操作，去控制顺序
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        //采用atomic方式
        Thread p1 = new Thread(() -> {
            for (Object obj : list2) {
                while (atomicInteger.get() != 1) {}
                System.out.print(obj);
                atomicInteger.set(2);
            }
        }, "p1");

        Thread p2 = new Thread(() -> {
            for (Object obj : list) {
                while (atomicInteger.get() != 2) {}
                System.out.println(obj);
                atomicInteger.set(1);
            }
        }, "p2");
        p1.start();
        p2.start();
    }
}

class ConditionTest{
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Condition lockA = lock.newCondition();
        Condition lockB = lock.newCondition();
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        new Thread(()->{
           lock.lock();
            try {
                for (Object obj : list2) {
                    try {
                        lockA.await();
                        System.out.print(obj);
                        lockB.signal();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                for (Object obj : list) {
                    try {
                        lockA.signal();
                        lockB.await();
                        System.out.println(obj);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }
}

class ConditionOneTest{
    //采用单个线程队列去处理的方式
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(()->{
            try {
                lock.lock();

                for(char c : aI) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(()->{
            try {
                lock.lock();

                for(char c : aC) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }
}

class ExchangeTest{
    //Exchanger无法实现，存在顺序错乱，无法保证每次操作的原子性
    public static void main(String[] args) {
        Exchanger<Object> exchanger = new Exchanger<>();
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        new Thread(()->{
                for (Object obj : list2) {
                    try {
                        System.out.println(exchanger.exchange(obj));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }).start();

        new  Thread(()->{

            for (Object obj : list) {
                try {
                    System.out.print(exchanger.exchange(obj));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class TransferQueueTest{
    //采用LinkedTransferQueue队列方式，即多个线程transfer之间互相调用，以take进行阻塞方式保证顺序
    public static void main(String[] args) {
        LinkedTransferQueue<Object> transferQueue = new LinkedTransferQueue<>();
        List<Object> list = new ArrayList<>();
        char a = 'A';
        List<Object> list2 = new ArrayList<>();
        for (int i = 1; i < 27; i++) {
            list.add(i);
            list2.add((char)(a+(i-1)));
        }

        new Thread(()->{
            for (Object obj : list2) {
                try {
                    transferQueue.transfer(obj);
                    System.out.println(transferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new  Thread(()->{

            for (Object obj : list) {
                try {
                    System.out.print(transferQueue.take());
                    transferQueue.transfer(obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}