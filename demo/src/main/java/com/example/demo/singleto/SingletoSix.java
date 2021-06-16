package com.example.demo.singleto;

public class SingletoSix {

  //匿名内部类由jvm虚拟机保证，加载外部类，内部类不会进行先加载
  private static class Single {
    private static final SingletoSix singletoSix = new SingletoSix();
  }

  private static SingletoSix getInstance(){
    return Single.singletoSix;
  }

  public static void main(String[] args){
    for (int i = 0;i<100;i++){
      new Thread(()->{
        SingletoSix instance = SingletoSix.getInstance();
        System.out.println(instance.hashCode());
      }).start();
    }
  }

}
