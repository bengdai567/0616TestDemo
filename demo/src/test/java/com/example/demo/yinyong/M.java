package com.example.demo.yinyong;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize即GC开始已经结束了！！！");
    }
}
