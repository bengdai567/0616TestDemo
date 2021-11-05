package com.example.demo.util;

import com.example.demo.testCodw.TestTwoDemo;
import com.example.demo.testCodw.TestOneDemo;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BeanCopyTest {
        public static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap();

        public BeanCopyTest() {
        }

        public static void copyProperties(Object source, Object target) {
            String beanCooierMapKey = buildKey(source.getClass(), target.getClass(), Boolean.FALSE);

            try {
                BeanCopier beanCopier;
                if (beanCopierMap.containsKey(beanCooierMapKey)) {
                    beanCopier = (BeanCopier)beanCopierMap.get(beanCooierMapKey);
                } else {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), Boolean.FALSE);
                    beanCopierMap.put(buildKey(source.getClass(), target.getClass(), Boolean.FALSE), beanCopier);
                }

                beanCopier.copy(source, target, (Converter)null);
            } catch (Exception var5) {
            }

        }

        public static void copyProperties(Object source, Object target, Converter converter) {
            String beanCooierMapKey = buildKey(source.getClass(), target.getClass(), Boolean.TRUE);

            try {
                BeanCopier beanCopier;
                if (beanCopierMap.containsKey(beanCooierMapKey)) {
                    beanCopier = (BeanCopier)beanCopierMap.get(beanCooierMapKey);
                } else {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), Boolean.TRUE);
                    beanCopierMap.put(buildKey(source.getClass(), target.getClass(), Boolean.TRUE), beanCopier);
                }

                beanCopier.copy(source, target, converter);
            } catch (Exception var6) {
            }

        }

        public static List BatchCopyProperties(List sources, Class clazz) {
            if (CollectionUtils.isEmpty(sources)) {
                return Collections.emptyList();
            } else {
                List targets = new ArrayList(sources.size());
                Iterator i$ = sources.iterator();

                while(i$.hasNext()) {
                    Object source = i$.next();
                    String beanCooierMapKey = buildKey(source.getClass(), clazz, Boolean.FALSE);

                    try {
                        BeanCopier beanCopier;
                        if (beanCopierMap.containsKey(beanCooierMapKey)) {
                            beanCopier = (BeanCopier)beanCopierMap.get(beanCooierMapKey);
                        } else {
                            beanCopier = BeanCopier.create(source.getClass(), clazz, Boolean.FALSE);
                            beanCopierMap.put(buildKey(source.getClass(), clazz, Boolean.FALSE), beanCopier);
                        }

                        Object target = clazz.newInstance();
                        targets.add(target);
                        beanCopier.copy(source, target, (Converter)null);
                    } catch (Exception var8) {
                    }
                }

                return targets;
            }
        }

        private static String buildKey(Class<?> sourceClass, Class<?> targetClass, Boolean needConverter) {
            StringBuilder sb = new StringBuilder("");
            sb.append(sourceClass.toString()).append(targetClass.toString()).append(needConverter.toString());
            return sb.toString();
        }


    public static void main(String[] args) {
        TestOneDemo testOneDemo = new TestOneDemo();
        testOneDemo.setFlagCount("2");
        testOneDemo.setClassHome("2");
        TestTwoDemo testTwoDemo = new TestTwoDemo();
//        copyProperties(testOneDemo,testTwoDemo);
        BeanUtils.copyProperties(testTwoDemo,testOneDemo);
        System.out.println();
    }
}
