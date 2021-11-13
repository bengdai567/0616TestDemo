package com.example.demo.designModel.singleto;

public class SingletoFive2 {
  private static volatile SingletoFive2 singletoFive;

  //
  public static SingletoFive2 getInstance(){

    if (singletoFive ==null){
      //
      synchronized (SingletoFive2.class){
        if (singletoFive == null){
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          singletoFive = new SingletoFive2();
        }
        }
    }
    return singletoFive;
  }



  public static void main(String[] args){
    for (int i = 0 ;i<100;i++){
      new Thread(()->{
        SingletoFive2 instance = SingletoFive2.getInstance();
        System.out.println(instance.hashCode());
      }).start();
    }

  }
}
