package com.example.demo.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Date: 2021/8/10 下午18:50
 */
/**
 * @describe 报文中有特殊需求的时候使用
 * @Author: huhongyang
 * @Date: 2021/8/11 下午17:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
 @interface FieldMapping {

    /**
     * 将报文中与实体类不对应的特殊字段写进 value
     *
     * @return
     */
    String value();
}

public class ToggleCaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ToggleCaseUtil.class);

    private static ThreadLocal<Map> threadLocal = new ThreadLocal();

    private static ThreadLocal<Integer> caseWhatThreadLocal = new ThreadLocal();

    /**
     *  出库单专用方法
     * @param taskJson
     * @param caseToWhat    转换方向 0、大写转驼峰，1、驼峰转大写
     * @return
     */
    public static String transformation(String taskJson,Integer caseToWhat){
       /* getCaseWhatThreadLocal(caseToWhat);
        String[] key = getKey("taskJson","deliveryOrder");
        Map map = new HashMap();
        String transformation = transformation(taskJson, SellerOrder.class,caseToWhat,"deliveryOrder","packageTagMessage");
        map.put(key[0],transformation);
        Map taskJsonMap = (Map) JSON.parse(transformation);
        Object deliveryOrder = taskJsonMap.get(key[1]);
        String transformation1 = transformation(deliveryOrder.toString(), DeliveryOrder.class,caseToWhat,"senderInfo","receiverInfo");
        taskJsonMap.put(key[1], JSON.parse(transformation1));
        map.put(key[0],taskJsonMap);
        return map.get(key[0]).toString();*/
       return null;
    }

    public static String transformation(String str, Class cla,Integer caseToWhat, String ...strIdentification){
        getCaseWhatThreadLocal(caseToWhat);
        logger.error(MessageFormat.format("转换接收报文【{0}】",str));
        try {
            Map maps = (Map) JSON.parse(str);
            threadLocal.set(maps);
            test(cla, maps,strIdentification);
        }catch (Exception e){
            logger.error(MessageFormat.format("报文【{0}】转换失败，异常信息【{1}】", JSON.toJSONString(threadLocal.get()),e));
            e.printStackTrace();
        }
        return JSON.toJSONString(threadLocal.get() == null ? str : threadLocal.get());
    }

    private static void test(Class<?> cla, Map maps, String... fieldNames) throws Exception {
        Object sourceObj = cla.newInstance();
        Field[] declaredFields = sourceObj.getClass().getDeclaredFields();
        List<Field> stringList = Stream.of(declaredFields).collect(Collectors.toList());
        stringList.forEach(item -> {
            each(maps, item.getName(),"");
        });
        for (Field field : declaredFields) {
            field.setAccessible(true);
            FieldMapping declaredAnnotation = field.getDeclaredAnnotation(FieldMapping.class);
            if (declaredAnnotation != null) {
                each(maps, field.getName(), declaredAnnotation.value());
            }
            if (field.getType().equals(List.class)) {
                Type type = field.getGenericType();
                ParameterizedType p = (ParameterizedType) type;
                Class paradigmClass = (Class) p.getActualTypeArguments()[0];
                threadLocal.set(maps);
                String key = getKey(field.getName());
                if(getMapsValue(maps, key)){
                    continue;
                }
                JSONArray objects = JSON.parseArray(maps.get(key).toString());
                Map[] arr = new Map[objects.size()];
                for (int i = 0; i < objects.size(); i++) {
                    Map listMap = (Map) JSON.parse(JSON.toJSONString(objects.get(i)));
                    arr[i] = listMap;
                    test(paradigmClass, listMap);
                }
                Map map = threadLocal.get();
                map.put(getKey(field.getName()), arr);
            }
            for (String name : fieldNames) {
                threadLocal.set(maps);
                if (!field.getName().equals(name) || getMapsValue(maps,getKey(name))) {
                    continue;
                }
                if(maps.get(getKey(name)) == null){
                    continue;
                }
                Map userInfo = (Map) JSON.parse(maps.get(getKey(name)).toString());
                test(field.getType().newInstance().getClass(), userInfo);
                threadLocal.get().put(getKey(name), userInfo);
                break;
            }
        }
    }

    private static boolean getMapsValue(Map maps,String name){
        return maps.get(name) == null;
    }

    private static void each(Map maps, String fieldName, String annotationName) {
        for (Object obj1 : maps.keySet()) {
            String obj = (String) obj1;
            String objStr = obj.replaceAll("[^a-zA-Z0-9]", "");
            if( caseWhatThreadLocal.get() != null && caseWhatThreadLocal.get() > 0){
                if(obj.equals(fieldName)&& StringUtils.isEmpty(annotationName)){
                    maps.put(fieldName.toUpperCase(Locale.ROOT),maps.remove(obj1));
                    return;
                }else if(obj.equals(annotationName)){
                    maps.put(annotationName.toUpperCase(Locale.ROOT),maps.remove(obj1));
                    return;
                }else if(obj.equalsIgnoreCase(fieldName) && StringUtils.isNotEmpty(annotationName)){
                    maps.put(annotationName.toUpperCase(Locale.ROOT),maps.remove(obj1));
                    return;
                }
            }else{
                if(objStr.equals(fieldName.toUpperCase(Locale.ROOT)) || obj.equals(annotationName)){
                    maps.put(fieldName,maps.remove(obj));
                    return;
                }
            }
        }
        Map map = threadLocal.get();
        if (map == null) {
            threadLocal.set(maps);
        }
    }

    public static String getKey(String name){
        return getKey(new String[]{name})[0];
    }

    public static String[] getKey(String ...strIdentification){
        if(caseWhatThreadLocal.get() != null && caseWhatThreadLocal.get() > 0){
            for (int i = 0;i < strIdentification.length;i ++){
                strIdentification[i] = strIdentification[i].toUpperCase(Locale.ROOT);
            }
        }
        return strIdentification;
    }

    private static void getCaseWhatThreadLocal(Integer caseWhat){
        if(caseWhatThreadLocal.get() == null){
            caseWhatThreadLocal.set(caseWhat);
        }
    }

}

class TestBean implements Serializable {
    private String s;
    private Integer studentId;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}

class testOne{
    public static void main(String[] args) {
        String sss = "{\"STUDENTID\":123,\n" +
                "\"S\":\"你来自哪\"}";
        final String transformation = ToggleCaseUtil.transformation(sss, TestBean.class, 0);
        System.out.println(transformation);
    }
}
