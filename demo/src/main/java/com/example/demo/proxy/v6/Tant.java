package com.example.demo.proxy.v6;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

//这种多接口的实现方式有种和装设者directory很像
//因为如果时间的记录需要其他的调用，或者想复用，直接采用基于接口的jdk动态代理的方式
//将记录的方式放到实现类的方法中
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
    Movable movable = (Movable) Proxy.newProxyInstance(Tant.class.getClassLoader(), new Class[]{Movable.class}, new TimeProxy(tant));
    movable.move();

  }
}

//日志记录它的移动和停止
class TimeProxy implements InvocationHandler {

  //注入初始对象
  Movable tant;
  public TimeProxy(Movable tant){
    this.tant = tant;
  }
  public void startWork(){
    System.out.println("开始记录》》》》》》》");
  }

  public void endWork(){
    System.out.println("记录结束》》》》》》》");
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    startWork();
    Object o = method.invoke(tant, args);
    endWork();
    return o;
  }
}


interface Movable{
  void move();
}
