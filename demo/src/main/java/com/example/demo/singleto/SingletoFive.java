package com.example.demo.singleto;

public class SingletoFive {
  private static SingletoFive singletoFive;

  //
  public static SingletoFive getInstance(){

    if (singletoFive ==null){
      //将锁放到代码块当中,造成线程不安全
      synchronized (SingletoFive.class){
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        singletoFive = new SingletoFive();
      }
      }
    return singletoFive;
  }



  public static void main(String[] args){
    for (int i = 0 ;i<100;i++){
      new Thread(()->{
        SingletoFive instance = SingletoFive.getInstance();
        System.out.println(instance.hashCode());
      }).start();
    }

  }
}
