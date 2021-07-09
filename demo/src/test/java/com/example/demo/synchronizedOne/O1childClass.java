package com.example.demo.synchronizedOne;

public class O1childClass extends O2parentClass {
    @Override
    synchronized void s1() {
        System.out.println("子开始");
        super.s1();
        System.out.println("子结束");
    }

    public static void main(String[] args) {
        new O1childClass().s1();
    }
}
