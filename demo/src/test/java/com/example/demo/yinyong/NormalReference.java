package com.example.demo.yinyong;

import java.io.IOException;

public class NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        //执行GC垃圾回收
        System.gc();
        System.in.read();
    }
}
