package com.example.demo.designModel.proxy.v2;

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
    new Tant2().move();
  }
}

//这种继承的方式，如果后期出现其它情况，比如要记录它每次行动的日志时，会产生类爆炸的情况
class Tant2 extends Tant{
  @Override
  public void move() {
    long start = System.currentTimeMillis();
    System.out.println(start);
    super.move();

    long end = System.currentTimeMillis();
    System.out.println(end);
    long timeOut = end - start;
    System.out.println(timeOut);
  }
}

interface Movable{
  void move();
}
