package com.example.demo.synchronizedOne;

import java.lang.invoke.MethodHandles;

public class HelloVarHandle {
    //需要gdk1.9之后才会采用varHandle,以前采用反射方式检查，繁琐
    /*int x = 8;

    private static VarHandle handle;

    static {
        try {
            handle = MethodHandles.lookup().findVarHandle(HelloVarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        HelloVarHandle t = new HelloVarHandle();

        //plain read / write
        System.out.println((int) handle.get(t));
        handle.set(t, 9);
        System.out.println(t.x);

        handle.compareAndSet(t, 9, 10);
        System.out.println(t.x);

        handle.getAndAdd(t, 10);
        System.out.println(t.x);
    }*/
}
