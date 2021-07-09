package com.example.demo.synchronizedOne;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

public class O10PhaserDemo extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {

        switch (phase){
            case 0:
                System.out.println("吃饭已到达");
                return false;
            case 1:
                System.out.println("学习已到达");
                return false;
            case 2:
                System.out.println("玩耍已到达");
                return false;
            default:
                System.out.println("回家结束");
                return true;
        }
    }
}

class Person{
    static void time(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private String name;

    public void eat(int i){
        time();
        System.out.println("eat"+i);
    }
    public void stady(int i){
        time();
        System.out.println("stady"+i);
    }
    public void play(int i){
        time();
        System.out.println("play"+i);
    }
    public void home(int i){
        time();
        System.out.println("home"+i);
    }
}

class testOne{
    static O10PhaserDemo phaserDemo = new O10PhaserDemo();

    public static void main(String[] args) {
        phaserDemo.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            final int i1= i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Person person = new Person();
                    person.eat(i1);
                    phaserDemo.arriveAndAwaitAdvance();

                    person.stady(i1);
                    phaserDemo.arriveAndAwaitAdvance();

                    person.play(i1);
                    phaserDemo.arriveAndAwaitAdvance();

                    person.home(i1);
                    phaserDemo.arriveAndAwaitAdvance();

                }
            }).start();
        }
    }
}