package com.example.demo.synchronizedOne;

import java.util.concurrent.Phaser;

//其实phaser实现功能都要继承phaser这个类，主要为了达到个数的日志效果
//而具体实现是在要操作的实体类中去实现Runable然后再重写run方法里写上对应方法或者在调用的时候直接调用的thread线程类中调用实体类的方法
public class O10PhaserDemo3 extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0 :
                System.out.println("都来齐了！");
                return false;
            case 1:
                System.out.println("开始吃饭！");
                return false;
            case 2:
                System.out.println("开始玩耍！");
                return false;
            case 3:
                System.out.println("新人他们开始游戏！");
                return true;
            default:
                return true;
        }

    }
}
class Persona {
    private String name;
    public Persona(String name){
        this.name = name;
//        this.o3PhaserDemo3 = o3PhaserDemo3;
    }
    public void allArrive(){
        System.out.println(name);
//        o3PhaserDemo3.arriveAndAwaitAdvance();
    }
    public void alleat(){
        System.out.println(name);
//        o3PhaserDemo3.arriveAndAwaitAdvance();
    }
    public void allPlay(){
        System.out.println(name);
//        o3PhaserDemo3.arriveAndAwaitAdvance();
    }
    public void newGame(){
        if (name.contains("小a")||name.contains("小b")){
//            o3PhaserDemo3.arriveAndAwaitAdvance();
        }else{
            System.out.println(name);
//            o3PhaserDemo3.arriveAndDeregister();
        }
    }
   /* @Override
    public void run() {
        allArrive();
        alleat();
        allPlay();
        newGame();
    }*/
}
class tetston{
    public static void main(String[] args) {
        O10PhaserDemo3 o10PhaserDemo3 = new O10PhaserDemo3();
        o10PhaserDemo3.bulkRegister(7);
        for (int i = 0; i < 7; i++) {
            final String sd = i+"";
            final Persona persona ;
            if (i==5){
                persona  = new Persona("asd");
            }else if (i==6){
                persona= new Persona( "asdzxc");
            }else{
                persona= new Persona(sd + "");
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    persona.allArrive();
                    o10PhaserDemo3.arriveAndAwaitAdvance();
                    persona.alleat();
                    o10PhaserDemo3.arriveAndAwaitAdvance();
                    persona.allPlay();
                    o10PhaserDemo3.arriveAndAwaitAdvance();
                    persona.newGame();
                    o10PhaserDemo3.arriveAndAwaitAdvance();

                }
            }).start();
        }
    }
}