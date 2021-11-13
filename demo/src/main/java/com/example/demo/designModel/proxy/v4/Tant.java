package com.example.demo.designModel.proxy.v4;

import java.util.Random;

//这种多接口的实现方式有种和装设者directory很像
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
    Tant tant = new Tant();
    TimeTant timeTant = new TimeTant(tant);
    LogTant logTant = new LogTant(timeTant);
    logTant.move();

  }
}

//该对象实现对时间的操控
class TimeTant implements Movable {
  //注入初始对象
  Movable movable;
  public TimeTant(Movable movable){
    this.movable = movable;
  }

  @Override
  public void move() {
    long start = System.currentTimeMillis();
    System.out.println(start);
    movable.move();

    long end = System.currentTimeMillis();
    System.out.println(end);
    long timeOut = end - start;
    System.out.println(timeOut);
  }
}

//日志记录它的移动和停止
class LogTant implements Movable{

  //注入初始对象
  Movable movable;
  public LogTant(Movable movable){
    this.movable = movable;
  }

  @Override
  public void move() {
    System.out.println("Tant starting————————");
    movable.move();
    System.out.println("Tant stop----------------");
  }
}


interface Movable{
  void move();
}
