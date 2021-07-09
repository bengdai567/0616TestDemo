package com.example.demo.synchronizedOne;

import java.util.concurrent.TimeUnit;

public class O3SynchronizedDem {
    public static void main(String[] args) {
//        new SynchronizedDemo().sss();
        new O3SynchronizedDem().new childClass().mm();

    }

    synchronized void sss(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2~");
    }

    public void testColor(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打印过程。。。。。");
    }

    class parentClass{
        synchronized void mm(){
            System.out.println("父类MM方法");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束父类的调用");
        }
    }
    class childClass extends parentClass{
        @Override
        synchronized void mm() {
            System.out.println("开始子类的调用");
            super.mm();
            System.out.println("结束");
        }
    }

}
