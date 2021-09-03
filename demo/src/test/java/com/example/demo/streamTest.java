package com.example.demo;

import com.example.demo.testCodw.TestOneDemo;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamTest {

    @Test
    public void streamToMap(){
        List<TestOneDemo> newList = new ArrayList<>();
        TestOneDemo testOneDemo = new TestOneDemo();
        testOneDemo.setName("one");
        testOneDemo.setClassHome("2l");
        newList.add(testOneDemo);
        TestOneDemo testOneDemo1 = new TestOneDemo();
        testOneDemo1.setName("two");
        testOneDemo1.setClassHome("asd");
        newList.add(testOneDemo1);
        TestOneDemo testOneDemo2 = new TestOneDemo();
        testOneDemo2.setName("three");
        testOneDemo2.setClassHome("4l");
        newList.add(testOneDemo2);
        List<TestOneDemo> listist = new ArrayList<>();
        for (TestOneDemo oneDemo : newList) {
            if (oneDemo.getName().equals("two")){
                System.out.println(oneDemo);
                continue;
            }
            System.out.println("继续执行");
        }
//        newList.parallelStream().forEach(e-> listist.add(BeanCopyTest.BatchCopyProperties().(new TestOneDemo(),e)));
        newList.parallelStream().forEach(e->e.setFlagCount(e.getClassHome()));
        System.out.println(newList);

       /* for (TestOneDemo oneDemo : newList) {
            try {
                Integer integer = Integer.valueOf(oneDemo.getClassHome());
            } catch (Exception e) {
                System.out.println("====="+oneDemo.getClassHome());
            }
            System.out.println(oneDemo);
        }*/

      /*  Map<String, String> collect = newList.stream().sorted(Comparator.comparing(TestOneDemo::getClassHome).reversed()).collect(Collectors.toMap(e -> e.getName(), e -> e.getClassHome()));
        System.out.println("accResult_: " + collect);*/
    }

    @Test
    public void streamGroupBy(){
        List<TestOneDemo> newList = new ArrayList<>();
        TestOneDemo testOneDemo = new TestOneDemo();
        testOneDemo.setName("one");
        testOneDemo.setClassHome("2l");
        newList.add(testOneDemo);
        TestOneDemo testOneDemo1 = new TestOneDemo();
        testOneDemo1.setName("two");
        testOneDemo1.setClassHome("3l");
        newList.add(testOneDemo1);
        TestOneDemo testOneDemo2 = new TestOneDemo();
        testOneDemo2.setName("one");
        testOneDemo2.setClassHome("4l");
        newList.add(testOneDemo2);
        Map<String, String> errorDatas = newList.stream().collect(
                Collectors.groupingBy(e -> e.getName(), Collectors.mapping(TestOneDemo::getClassHome, Collectors.joining(","))));


        System.out.println("accResult_: " + errorDatas);
    }

    @Test
    public void streamReduce(){
        ArrayList<Integer> newList = new ArrayList<>();

        ArrayList<Integer> accResult_ = Stream.of(2, 3, 4)
                .reduce(newList,
                        (acc, item) -> {
                            acc.add(item);
                            System.out.println("item: " + item);
                            System.out.println("acc+ : " + acc);
                            System.out.println("BiFunction");
                            return acc;
                        }, (acc, item) -> null);
        System.out.println("accResult_: " + accResult_);
        List<TestOneDemo> objects = new ArrayList<>();
        TestOneDemo testOneDemo = new TestOneDemo();
        testOneDemo.setId(1l);
        testOneDemo.setName("one");
        objects.add(testOneDemo);
        TestOneDemo testOneDemo1 = new TestOneDemo();
        testOneDemo1.setId(1l);
        testOneDemo1.setName("two");
        objects.add(testOneDemo1);
        TestOneDemo testOneDemo2 = new TestOneDemo();
        testOneDemo2.setId(1l);
        testOneDemo2.setName("two");
        objects.add(testOneDemo2);
        Map<Long, TestOneDemo> collect = objects.stream().collect(Collectors.toMap(e -> e.getId(), e -> e, (a, b) -> a));
        System.out.println(collect);

    }

    @Test
    public void streamallMatch(){
        //只要有一个条件满足即返回true anyMatch ;必须全部都满足才会返回true allMatch;全都不满足才会返回true noneMatch
        System.out.println(new BigDecimal(0));
        ArrayList<String> strings = Lists.newArrayList("a", "a", "a");
        boolean a = strings.stream().noneMatch(e -> e.equals("b"));
        System.out.println(a);
    }
   @Test
    public void mesage(){
       List<TestOneDemo> objects = new ArrayList<>();
       TestOneDemo testOneDemo = new TestOneDemo();
       testOneDemo.setId(1l);
       testOneDemo.setName("one");
       objects.add(testOneDemo);
       TestOneDemo testOneDemo1 = new TestOneDemo();
       testOneDemo1.setId(2l);
       testOneDemo1.setName("two");
       objects.add(testOneDemo1);
       Object[] ones = objects.stream().filter(t -> t.getName().equals("one")&&t.getId()==1l).map(TestOneDemo::getId).toArray();

       List<TestOneDemo> one = objects.stream().filter(t -> t.getName().equals("one")).collect(Collectors.toList());
       System.out.println(one);
       System.out.println(MessageFormat.format("请把【{1}】带走，然后我要拽他去【{0}】", "小子", "打个字","Nong"));
    }

    @Test
    public void streamcollect(){
    ArrayList<Object> objects = com.google.common.collect.Lists.newArrayList(10);
   /* List<TestOneDemo> arrayList = new ArrayList<TestOneDemo>(10);
    TestOneDemo testOneDemo = new TestOneDemo();
    testOneDemo.setId("1");
    testOneDemo.setName("xi奥");
    arrayList.add(testOneDemo);
    TestOneDemo testOneDemo1 = new TestOneDemo();
    testOneDemo1.setId("2");
    testOneDemo1.setName("asda");
    arrayList.add(testOneDemo1);
    arrayList.stream().forEach(e-> System.out.println(e.getName()));
    arrayList.stream().map(a->a.getName()).collect(Collectors.toList());*/
    }

}
