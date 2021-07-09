package com.example.demo.synchronizedOne;

import java.util.concurrent.Phaser;

public class O10PhaserDemo2 extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                System.out.println("a"+registeredParties);
                return false;
            case 1:
                System.out.println("b"+registeredParties);
                return false;
            case 2:
                System.out.println("c"+registeredParties);
                return false;
            case 3:
                System.out.println("d"+registeredParties);
                return true;
            default:
                return true;
        }
    }
}

class Ponese implements Runnable{
    private String name ;
    private O10PhaserDemo2 o10PhaserDemo2;

    public Ponese(String name,O10PhaserDemo2 o10PhaserDemo2){
        this.name = name ;
        this.o10PhaserDemo2 = o10PhaserDemo2 ;
    }
    public void eat(){
        System.out.println(name+"来吃饭！");
        o10PhaserDemo2.arriveAndAwaitAdvance();
    }
    public void stady(){
        System.out.println(name+"来学习！");
        o10PhaserDemo2.arriveAndAwaitAdvance();
    }
    public void play(){
        System.out.println(name+"来玩耍！");
        o10PhaserDemo2.arriveAndAwaitAdvance();
    }
    public void home(){
        if (name.equals("小a")||name.contains("小b")){
            System.out.println("小a与小b一起牵手手");
            o10PhaserDemo2.arriveAndAwaitAdvance();
        }else{
//            System.out.println(name+"来回家！");
            //不参与后面流程
            o10PhaserDemo2.arriveAndDeregister();
        }
    }

    @Override
    public void run() {
        eat();
        stady();
        play();
        home();
    }
}

class PhaserOne{
    public static void main(String[] args) {
        O10PhaserDemo2 o10PhaserDemo2 = new O10PhaserDemo2();
        o10PhaserDemo2.bulkRegister(7);
        Thread[] threads = new Thread[7];
        for (int i = 0; i < threads.length; i++) {
            if (i==5){
                threads[i] = new Thread(new Ponese("小a",o10PhaserDemo2));
            }else if(i==6){
                threads[i] =  new Thread(new Ponese("小b",o10PhaserDemo2));
            }else{
                threads[i] =  new Thread(new Ponese(i+"",o10PhaserDemo2));
            }
//            new Thread(new Ponese(i+"",o10PhaserDemo2)).start();
        }

//        new Thread(new Ponese("小a",o10PhaserDemo2)).start();
//        new Thread(new Ponese("小b",o10PhaserDemo2)).start();
        for (Thread thread : threads) thread.start();
    }
}