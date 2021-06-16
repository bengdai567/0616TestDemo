package com.example.demo;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.testCodw.TestOneDemo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapArrayTest {

    @Test
    public void testteos(){
        //查看字符串数组转成集合
        String tes = "one;two;three;";
        List<String> strings = Arrays.asList(tes.split(";"));
        System.out.println(strings);
        try {
            throw new SecurityException("sdasd");
        }catch (SecurityException  | IllegalArgumentException ex){
            System.out.println(ex);
        }
    }

    @Test
    public void testoen(){
        String ss="1,2,3";
        List<String> strings = Arrays.asList(ss);
        System.out.println(strings);

        try {
            String sr = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAM2DSqwUg6gCWt1RLREh2ECr8HwjyeehmvPbjyPAVzedzm6AYctNKRK4+1qhhi5puo27aFB5YazdSL8wA6p1jykCAwEAAQ==";
            String[] keyPair = ConfigTools.genKeyPair(512);
            //私钥
            String privateKey = keyPair[0];
            //公钥
            String publicKey = keyPair[1];
            System.out.println("publicKey:"+publicKey);
            String hai2016MA1007WIN = ConfigTools.encrypt(privateKey,"root");
            System.out.println(hai2016MA1007WIN);
            String decrypt = ConfigTools.decrypt(publicKey,hai2016MA1007WIN);
            System.out.println(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void mapkey1(){
/*
        try {
            //阻塞式 等待被连接 不间断连接
            ServerSocket socketServer = new ServerSocket(8089);
            Socket accept = socketServer.accept();
            accept.

        } catch (IOException e) {
            e.printStackTrace();
        }*/
       String s = "sss";
        String[] split = s.split(",");
        for (String s1 : split) {

            System.out.println(s1);
        }

        TestOneDemo testOneDemo = new TestOneDemo();
        testOneDemo.setClassHome("one");
        Object o = JSONObject.toJSON(testOneDemo);
        System.out.println(o);

        ArrayList<String> arrayList = Lists.newArrayList();
        TestOneDemo testOne = new TestOneDemo();
        testOne.setName("one");
//        arrayList.add(testOne);
        arrayList.add("one");
        arrayList.add("two");
        System.out.println(arrayList);
        System.out.println( JSONObject.toJSON(arrayList));

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        ClassLoader classLoader = resourceLoader.getClassLoader();
        System.out.println(classLoader);
        Resource resource = resourceLoader.getResource("D:\\Mebook\\笔记整理\\个人总结idea快捷键使用.txt");
        System.out.println(resource+"==="+ (resource instanceof FileSystemResource));

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            System.out.println("==="+e);
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void arrays(){
        String s ="one";
        String[] split = s.split(",");
        System.out.println(Arrays.asList(split));
    }

    @Test
    public void mapkey(){
        ArrayList<Map> arrayList = Lists.newArrayList();
        TestOneDemo testOne = new TestOneDemo();
//        testOne.setName("one");
//        arrayList.add(testOne);
//        arrayList.add("one");
//        arrayList.add("two");
        System.out.println(arrayList.toString());
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name","曹操"+i+"");
            map.put("sex","男"+i+"");
            map.put("age","21"+i+"");
            arrayList.add(map);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("XLD","ASD");
        map.put("listMap",arrayList);
        String s = JSON.toJSONString(map);
        testOne.setName("ssss");
        testOne.setClassHome(s);
        String s1 = JSON.toJSONString(testOne);
        System.out.println(s1);

//        Set<String> strings = map.keySet();
//        System.out.println(strings);
    }

}
