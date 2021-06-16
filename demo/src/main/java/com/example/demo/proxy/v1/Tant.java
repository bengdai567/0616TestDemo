package com.example.demo.proxy.v1;

import java.util.Random;

//实现接口完成移动操作
public class Tant implements Movable{

  @Override
  public void move() {
    System.out.println("Tank 移动 ++++++>>>>>>++++++++");
    try {
      Thread.sleep(new Random().nextInt(10000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}



interface Movable{
  void move();
}
