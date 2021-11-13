package com.example.demo.designModel.singleto;


/**
 * 懒汉式的加载方式
 */
public class SingletoThree {
  private static SingletoThree singletoThree;

  public static SingletoThree getInstence(){
    if (singletoThree==null){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      singletoThree = new SingletoThree();
    }
    return singletoThree;
  }


  public static void main(String[] args){
    for (int i = 0 ;i<100;i++){
      new Thread(()->{
        SingletoThree instence = SingletoThree.getInstence();
        System.out.println(instence);
      }).start();
    }
  }
}
