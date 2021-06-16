package com.example.demo.singleto;

public class SingletoFour {
  private static SingletoFour singletoFour;

  //方法同步效率比较低
  public static synchronized SingletoFour getInstance(){

    if (singletoFour ==null){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      singletoFour = new SingletoFour();
    }
    return singletoFour;
  }



  public static void main(String[] args){
    for (int i = 0 ;i<100;i++){
      new Thread(()->{
        SingletoFour instance = SingletoFour.getInstance();
        System.out.println(instance.hashCode());
      }).start();
    }

  }
}
