package com.example.demo.singleto;

//采用枚举的方式获取，就可以有效避免线程安全的问题
public enum SingletoSeven {

  singletoSeven;




  public static void main(String[] args){
    for (int i = 0;i<100;i++){
      new Thread(()->{
        SingletoSeven singletoSeven = SingletoSeven.singletoSeven;
        System.out.println(singletoSeven.hashCode());
      }).start();
    }
  }

}
