package com.example.demo.designModel.proxy.v7;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Random;


//采用cglib的方式，代理类内部继承需要加强的类，完成代理调用操作
public class ManTant {
  public static void main(String[] args) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(Tant.class);
    enhancer.setCallback(new MethodProxyDoc());
    Tant tant = (Tant) enhancer.create();
    tant.move();
  }
}

class MethodProxyDoc implements MethodInterceptor{

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    System.out.println("加强前++++++++++++");
    Object o1 = methodProxy.invokeSuper(o, objects);
    System.out.println("加强后++++++++++++");
    return o1;
  }
}


class Tant{
  public void move(){
    System.out.println("开始移动>>>>>>>>>>>>>>>");
    try {
      Thread.sleep(new Random().nextInt(10000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
