package com.example.demo.singleto;

public class SingletoOne {
  private static final SingletoOne single = new SingletoOne();

  public static SingletoOne getInstance(){
    return single;
  }


  public static void main(String[] args){
    SingletoOne instance = SingletoOne.getInstance();
    SingletoOne instance1 = SingletoOne.getInstance();
    System.out.println(instance==instance1);
  }
}


