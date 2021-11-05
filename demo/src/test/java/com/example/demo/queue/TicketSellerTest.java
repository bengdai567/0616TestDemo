package com.example.demo.queue;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 *
 */
public class TicketSellerTest {
    static List ticketLists = new ArrayList();

    static {
        for (int i = 0; i < 10000; i++) ticketLists.add(i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
           new Thread(()->{
               while (ticketLists.size()>0){
                   System.out.println("销售了-----"+ticketLists.remove(0));
               }
           }).start();
        }
    }
}
/**
 *  有N张火车票，每张票都有一个编号
 *  * 同时有10个窗口对外售票
 *  * 请写一个模拟程序
 *  *
 *  * 分析下面的程序可能会产生哪些问题？
 *  *
 *  * 使用Vector或者Collections.synchronizedXXX
 *  * 分析一下
 *  多个线程同时卖票，卖的速度有快有慢，当其中一个线程还未检出时，另一个线程又开始卖
 *  ，而且也卖的这张票，因为锁的原因会等待，当前一个线程检出了，且也已是最后一张，后一个线程又开始
 *  卖出，结果没了，就会indexOut了
 */
class TicketSellerTestTwo{
    static List list = Collections.synchronizedList(new ArrayList());

    static {
        for (int i = 0; i < 10000; i++) list.add(i);
    }

    public static void main(String[] args) {
        final HashMap hashMap = new HashMap();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(()->{
                while (list.size()>0){

                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object remove = list.remove(0);
                    System.out.println(num+"销售了-----"+remove);
                    if (hashMap.get(remove)!=null){
                        System.out.println("重复"+list.remove(0));
                    }else{
                        hashMap.put(remove,remove);
                    }
                }
            }).start();
        }
    }
}
/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，也会超售问题
 *
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 同步的同时速度也会降低
 */
 class TicketSellerTestThree{
    static List list = Collections.synchronizedList(new ArrayList());

    static {
        for (int i = 0; i < 10000; i++) list.add(i);
    }

    public static void main(String[] args) {
        final HashMap hashMap = new HashMap();
        for (int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(()->{
                while (true){
                    synchronized (list){
                        if (list.size()==0) break;
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Object remove = list.remove(0);
                        System.out.println(num+"销售了-----"+remove);
                        if (hashMap.get(remove)!=null){
                            System.out.println("重复"+list.remove(0));
                        }else{
                            hashMap.put(remove,remove);
                        }
                    }
                }
            }).start();
        }
    }
}

/**
 *  * 有N张火车票，每张票都有一个编号
 *  * 同时有10个窗口对外售票
 *  * 请写一个模拟程序
 *  *
 *  * 分析下面的程序可能会产生哪些问题？
 *  * 重复销售？超量销售？
 *  *
 *  * 使用Vector或者Collections.synchronizedXXX
 *  超量销售
 *  *
 *  * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 *  * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 *  *
 *  * 使用ConcurrentQueue提高并发性
 *  采用ConcurrentHashMap也无法实现并发队列取的同步，因为该链表实现的是cas插入操作，而读取并没有做锁
 *  对于ConcurrentLinkedQueue是取和拿都会加锁，所以会一致
 */
class TicketSellerTestFour{
    static ConcurrentLinkedQueue linkedQueue = new ConcurrentLinkedQueue();
//    static ConcurrentHashMap linkedQueue = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i < 10000; i++) linkedQueue.add(i);
//        for (int i = 0; i < 10000; i++) linkedQueue.put(i,i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int num = i;
            new Thread(() -> {
                while (true) {
//                    Integer o1 = (Integer) linkedQueue.keys().nextElement();
                       /* try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    Object poll = linkedQueue.poll();
//                    Object poll = linkedQueue.remove(o1);
                      /*  if (poll==null){
                            continue;
                        }*/
                    if (linkedQueue.size() == 0) break;
                    System.out.println(num + "销售了-----" + poll);
                    }
            }).start();
        }
    }
}

