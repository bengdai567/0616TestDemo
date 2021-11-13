package com.example.demo.designModel.proxy.v3;

import java.util.Random;

//当出需要记录它的移动时间时
public class Tant implements Movable {

  @Override
  public void move() {
    System.out.println("Tank 移动 ++++++>>>>>>++++++++");
    try {
      Thread.sleep(new Random().nextInt(10000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Tant2(new Tant()).move();
  }
}

//采用实现方式，多实现去实现记录操作的时间
class Tant2 implements Movable {
  //注入移动的对象
  Tant tant;
  public Tant2(Tant tant){
    this.tant = tant;
  }

  @Override
  public void move() {
    long start = System.currentTimeMillis();
    System.out.println(start);
    tant.move();

    long end = System.currentTimeMillis();
    System.out.println(end);
    long timeOut = end - start;
    System.out.println(timeOut);
  }
}

interface Movable{
  void move();
}
