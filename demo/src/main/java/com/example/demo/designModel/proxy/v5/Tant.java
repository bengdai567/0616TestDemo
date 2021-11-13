package com.example.demo.designModel.proxy.v5;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

//这种多接口的实现方式有种和装设者directory很像
//因为如果日志的记录需要其他的调用，或者想复用，直接采用基于接口的jdk动态代理的方式
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
    Movable movable = (Movable) Proxy.newProxyInstance(Tant.class.getClassLoader(), new Class[]{Movable.class}, new LogTant(tant));
    movable.move();

  }
}

//日志记录它的移动和停止
class LogTant implements InvocationHandler {

  //注入初始对象
  Movable tant;
  public LogTant(Movable tant){
    this.tant = tant;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("开始打印运行+++++++++++++++");
    Object o = method.invoke(tant, args);
    System.out.println("开始打印结束+++++++++++++++");
    return o;
  }
}


interface Movable{
  void move();
}
