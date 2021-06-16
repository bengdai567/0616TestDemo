package com.example.demo;

import com.example.demo.testCodw.TestOneDemo;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class TimeBigdecimalTest {

    @Test
    public void sdsasdas(){

    }

    @Test
    public void timeFomdate(){
    /*    String format ="yyyy-MM-dd hh:mm:ss";
        String modifyTime = "2021-01-02 03:06:55";
        int time = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(modifyTime);
            String strTime = date.getTime() + "";
            strTime = strTime.substring(0, 10);
            time = Integer.parseInt(strTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(time);*/
//        String s = null;
//        BigDecimal bigDecimal = new BigDecimal(s);
//        System.out.println(bigDecimal);
        long sss= 222;
        BigDecimal bigDecimals = new BigDecimal(sss);
        System.out.println(bigDecimals);
    }

 @Test
    public void timeDatetest(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(1592967899000l);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = format.format(date);
        System.out.println(dateString);
    }

    @Test
    public void begdicemal(){
        BigDecimal zero = new BigDecimal(0);
        BigDecimal totalUnCheckQty = BigDecimal.ZERO;
        BigDecimal subtract = totalUnCheckQty.add(zero.subtract(new BigDecimal(1.0000)));
        System.out.println(subtract);
        int i = new BigDecimal(1.0).compareTo(subtract);
        System.out.println(i);
    }

    @Test
    public void compaBigdecemal(){
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(2);
        //前提为a、b均不能为null
        if(a.compareTo(b) == -1){
            System.out.println("a小于b");
        }

        if(a.compareTo(b) == 0){
            System.out.println("a等于b");
        }

        if(a.compareTo(b) == 1){
            System.out.println("a大于b");
        }

        if(a.compareTo(b) > -1){
            System.out.println("a大于等于b");
        }

        if(a.compareTo(b) < 1){
            System.out.println("a小于等于b");
        }
    }

    @Test
    public void begdicemal2(){
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
        }

        BigDecimal test = BigDecimal.TEN;
        System.out.println(test);
    }

}
