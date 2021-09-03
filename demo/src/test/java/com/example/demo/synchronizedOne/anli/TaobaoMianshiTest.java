package com.example.demo.synchronizedOne.anli;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 实现一个容器，提供两个方法，add，size
 *  * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *  *
 */
public class TaobaoMianshiTest {
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
        taobaoMianshiTest contSize = new taobaoMianshiTest();
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
        taobaoMianshiTest contSize = new taobaoMianshiTest();
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
      taobaoMianshiTest contSize = new taobaoMianshiTest();
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
       taobaoMianshiTest contSize = new taobaoMianshiTest();
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

    /*public static void main(String[] args) {
        //单个锁定时，也会出现数量为6时才释放，存在时间问题
       taobaoMianshiTest contSize = new taobaoMianshiTest();
        Thread t1 = new Thread(() -> {
            if (contSize.size() != 5) {
                LockSupport.park();
            }
            System.out.println("t2释放！！！");
        }, "t1");
        t1.start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                contSize.add(new Object());
                System.out.println("t1数据" + contSize.size());
                if (contSize.size() == 5) {
                    LockSupport.unpark(t1);
                }
            }

        }, "t2").start();
    }*/

    /*static Thread t1 = null,t2=null;
    public static void main(String[] args) {
        //这种方式也是相当可以的，内部也是eas操作
        taobaoMianshiTest contSize = new taobaoMianshiTest();
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                contSize.add(new Object());
                System.out.println("t1数据" + contSize.size());
                if (contSize.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");
        t2 = new Thread(() -> {
            if (contSize.size() != 5) {
                LockSupport.park();
            }
            LockSupport.unpark(t1);
            System.out.println("t2释放！！！");
        }, "t2");
        t1.start();
        taobaoMianshiTest.t2.start();
    }*/
    static Thread t1 = null,t2=null;
    public static void main(String[] args) {
        TaobaoMianshiTest c = new TaobaoMianshiTest();
        Semaphore s = new Semaphore(1);

        t1 = new Thread(() -> {
            try {
                s.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    System.out.println(i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        t2 = new Thread(() -> {
            try {
                s.acquire();
                System.out.println("t2 结束");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        //t2.start();
        t1.start();
    }
}
