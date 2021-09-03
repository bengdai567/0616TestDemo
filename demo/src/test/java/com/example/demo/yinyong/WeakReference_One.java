package com.example.demo.yinyong;


import java.lang.ref.WeakReference;

/**
 * 弱引用遭到gc就会回收
 *
 */
public class WeakReference_One {
    public static void main(String[] args) {
        //即相当于一个弱引用维持着一个强引用
        WeakReference weakReference = new WeakReference<>(new M());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set(new M());
        threadLocal.remove();
    }
}
