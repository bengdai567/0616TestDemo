package com.example.demo.synchronizedOne;

import org.assertj.core.util.Lists;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class taobaoMianshiTest {
    //加volatile只是为了确保两个线程之间都能访问到
    volatile List<Object> list = Lists.newArrayList();
//    List<Object> list = Collections.synchronizedList(lists);

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    /*public static void main(String[] args) {
        //门栓方式进行禁停时由于不是同步容器，最后可能禁停的条数并不在所要禁停的位置
        CountDownLatch downLatch = new CountDownLatch(1);
        TestContSize contSize = new TestContSize();
        new Thread(()->{
               if (contSize.size()!=5){
                   try {
                       downLatch.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            System.out.println("线程已结束，数量："+contSize.size());
        },"t2").start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                contSize.add(new Object());
                System.out.println("添加add："+i);
                if (contSize.size()==5){
                    downLatch.countDown();
                }

            }
        },"t1").start();
    }*/
  /*  public static void main(String[] args) {
        TestContSize contSize = new TestContSize();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                contSize.add(new Object());
                System.out.println("已接收："+i);
                if (contSize.list.size()==5){
                    return;
                }
            }

        },"t1").start();

        new Thread(()->{
            while (true){
                if (contSize.list.size()==5){
                    System.out.println("asdasdasd结束"+contSize.list.size());
                    break;
                }
            }
        },"t2").start();
    }*/
  //即两个线程，一个线程监听另一个线程是否达到size标准，走另一个线程，然后以syn重量锁去锁定
  /*public static void main(String[] args) {
      TestContSize contSize = new TestContSize();
      final Object obj = new Object();
      Thread t1 = new Thread(() -> {
          synchronized (obj) {
              if (contSize.size() != 5) {
                  try {
                      obj.wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
              System.out.println("t1结束" + contSize.size());
              obj.notify();
          }
      }, "t1");

      Thread t2 = new Thread(() -> {
          synchronized (obj) {
              for (int i = 0; i < 10; i++) {
                  contSize.add(new Object());
                  System.out.println("添加次数：" + i);
                  if (contSize.size() == 5) {
                      try {
                          obj.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
                  obj.notify();
              }
          }
      }, "t2");
      t1.start();
      t2.start();
  }*/

  /* public static void main(String[] args) {
       //采用ReentrantLock失败，无法实现
       TestContSize contSize = new TestContSize();
       ReentrantLock lock = new ReentrantLock();
       Condition one = lock.newCondition();
       Condition two = lock.newCondition();
       new Thread(new Runnable() {
           @Override
           public void run() {
               if (contSize.size()!=5){
                   try {
                       one.await();
                       System.out.println("asdasdasd");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }else{
                   try {
                       two.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("线程t1结束："+contSize.size());
               }
           }
       },"t1").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                contSize.add(new Object());
                System.out.println("已添加："+i);
                if (contSize.size()==5){
                    two.signal();
                }else{
                    one.signal();
                }
            }
        },"t2").start();
   }*/

    public static void main(String[] args) {
//        final CyclicBarrier cyclicBarrier = new CyclicBarrier();
    }
}
