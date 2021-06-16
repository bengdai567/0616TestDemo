package com.example.demo;

import com.example.demo.testCodw.CacheEviTest;
import com.example.demo.testCodw.TestOneDemo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.regex.Pattern;

public class ValueChuandi {

    @Test
    public void testString(){
        String ww = "8:0";
        String[] split = ww.split(":");
        String sss = "100000";
        String s = StringUtils.leftPad(sss, 12, '0');
        String s1 = StringUtils.leftPad(split[0], 2,'0');
        String s2 = StringUtils.leftPad(split[1], 2,'0');
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);

    }

    @Test
    public void test(){
        String sss = null;
        change(sss);
        System.out.println(sss);

        try {
            Integer asdasd = Integer.valueOf("asdasd");
            int  i = 0 / 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("正常进行");
        String pattern = "^.{16}[1,2].{12}$";
        String content = "63624301128319103AMZ100950001";
        boolean matches = Pattern.matches(pattern, content);
        System.out.println(matches);
    }

    private static void change(String sss) {
        sss = "false";
    }

    @Test
    public void ste(){
        CacheEviTest cacheEviTest = new CacheEviTest();
        String ssss = cacheEviTest.ssss();
        System.out.println(ssss);
    }

    @Test
    public void stee(){
        TestOneDemo testOneDemo = new TestOneDemo();
//        testOneDemo.c
    }
}
