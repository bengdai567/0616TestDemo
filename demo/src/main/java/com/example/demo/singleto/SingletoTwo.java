package com.example.demo.singleto;


/**
 * 饿汉式的加载方式
 */
public class SingletoTwo {
  private static  SingletoTwo single;

  static {
    single = new SingletoTwo();
  }

  public static SingletoTwo getInstance(){
    return single;
  }


  public static void main(String[] args){
    SingletoTwo instance = SingletoTwo.getInstance();
    SingletoTwo instance1 = SingletoTwo.getInstance();
    System.out.println(instance==instance1);
  }
}


