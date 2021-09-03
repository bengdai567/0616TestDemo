package com.example.demo.synchronizedOne;

public class Lanjiazaiandanquan {

    private Lanjiazaiandanquan(){
        System.out.println("我被初始化了！！");
    }

    private static class InnerClass{
        private static Lanjiazaiandanquan lanjiazaiandanquan = new Lanjiazaiandanquan();
    }
    public static Lanjiazaiandanquan getUse(){
        return InnerClass.lanjiazaiandanquan;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                System.out.println(Lanjiazaiandanquan.getUse());
            }).start();
        }

    }
}
