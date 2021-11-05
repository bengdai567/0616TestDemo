package com.example.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.testCodw.TestOneDemo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringJsonTest {
    @Test
    public void stringTest1(){
        String str="awdawdswe";//待处理字符串

        if(str.length()>=4){// 判断是否长度大于等于4
            String strsub=str.substring(str.length()- 4);//一个参数表示截取传递的序号之后的部分
            System.out.println(strsub);
            String strsub1=str.substring(str.length()- 4,str.length());//截取两个数字之间的部分
        }

        TestOneDemo testOneDemo = new TestOneDemo();
        testOneDemo.setName("1");
        List<TestOneDemo> objects = Lists.newArrayList();
        objects.add(testOneDemo);
        TestOneDemo testOneDemo1 = new TestOneDemo();
        testOneDemo1.setName("3");
        objects.add(testOneDemo1);
        Integer collect = objects.stream().collect(Collectors.summingInt(e -> Integer.parseInt(StringUtils.isBlank(e.getName())?"0":e.getName())));
        String s = objects.stream().collect(Collectors.summingInt(e -> Integer.parseInt(StringUtils.isBlank(e.getName()) ? "0" : e.getName()))).toString();
        System.out.println(s);
        System.out.println(collect);

        System.out.println("====================");
        String s1 = "http://m.t3315.com/?fw=94300251607322753BSF000120001";
        int i = s1.indexOf("=");
        String substring = s1.substring(i+18, i+19);
        String substring1 = s1.substring(s1.length() - 4);
        System.out.println("正确截取："+substring1);
        System.out.println(substring);

   /* String sss3= "2";
    PackageEnums parse = PackageEnums.parse(sss3);
    System.out.println(parse.getName());*/

    }

    @Test
    public void jsonTest(){

   /* String js = "[{\"key\":\"batchNo\",\"name\":\"波次号\",\"show\":true,\"id\":1},{\"key\":\"packageNo\",\"name\":\"包裹号\",\"show\":true,\"id\":2}," +
            "{\"key\":\"packageStatus\",\"name\":\"包裹状态\",\"show\":true,\"id\":3},{\"key\":\"orderNo\",\"name\":\"出库单号\",\"show\":true,\"id\":4}," +
            "{\"key\":\"channelsOrderNo\",\"name\":\"ISV单号\",\"show\":false,\"id\":5},{\"key\":\"billType\",\"name\":\"单据类型\",\"show\":false,\"id\":6}," +
            "{\"key\":\"ownerName\",\"name\":\"货主\",\"show\":false,\"id\":7},{\"key\":\"carrierName\",\"name\":\"承运商\",\"show\":true,\"id\":8}," +
            "{\"key\":\"customerName\",\"name\":\"收货客户\",\"show\":true,\"id\":9},{\"key\":\"skuNo\",\"name\":\"商品编码\",\"show\":true,\"id\":10}," +
            "{\"key\":\"isvSkuNo\",\"name\":\"ISV商品编码\",\"show\":true,\"id\":11},{\"key\":\"skuName\",\"name\":\"商品名称\",\"show\":true,\"id\":12}," +
            "{\"key\":\"packageQty\",\"name\":\"装箱数量\",\"show\":true,\"id\":13},{\"key\":\"operator\",\"name\":\"操作人\",\"show\":true,\"id\":14}," +
            "{\"key\":\"operateTime\",\"name\":\"操作时间\",\"show\":true,\"id\":15},{\"key\":\"waybillNo\",\"name\":\"运单号\",\"show\":false,\"id\":16}," +
            "{\"key\":\"driverName\",\"name\":\"承运人\",\"show\":false,\"id\":17},{\"key\":\"driverCarNo\",\"name\":\"车牌号\",\"show\":false,\"id\":18}," +
            "{\"key\":\"rowNo\",\"name\":\"集货排\",\"show\":false,\"id\":19},{\"key\":\"createTime\",\"name\":\"接收时间\",\"show\":false,\"id\":20}," +
            "{\"key\":\"deliveryTime\",\"name\":\"发货时间\",\"show\":false,\"id\":21}]";
    List<Map<String,Object>> parse = (List<Map<String,Object>>) JSONArray.parse(js);
    for (Map<String, Object> stringStringMap : parse) {
      Object name = stringStringMap.get("name");
      boolean show = Boolean.parseBoolean(stringStringMap.get("show").toString());
      System.out.println(name+"=="+show);
    }
    Stream<Map<String, Object>> show = parse.stream().filter(e -> Boolean.parseBoolean(e.get("show").toString())==true);
    List<Object> key = show.map(e -> e.get("key")).collect(Collectors.toList());
    System.out.println(key);
*/

        String mm = "{\n" +
                "\t\"status\": 0,\n" +
                "\t\"statusText\": \"Success\",\n" +
                "\t\"data\": [{\n" +
                "\t\t\"respCode\": \"0\",\n" +
                "\t\t\"pageInfoMap\": [{\n" +
                "\t\t\t\"02727A44-316E-9891-99A9-631639496FCA\": {\n" +
                "\t\t\t\t\"pageId\": \"02727A44-316E-9891-99A9-631639496FCA\",\n" +
                "\t\t\t\t\"createUser\": \"A000011635\",\n" +
                "\t\t\t\t\"createUserName\": \"操作员姓名\",\n" +
                "\t\t\t\t\"createTime\": \"2019-11-05 11:10:23.112\",\n" +
                "\t\t\t\t\"modifyUser\": \"操作员姓名\",\n" +
                "\t\t\t\t\"modifyTime\": \"2019-11-15 11:10:23.112\",\n" +
                "\t\t\t\t\"pageUrl\": \"037D75C2-4437-6B07-1DEE-314A01B59068.jpg\",\n" +
                "\t\t\t\t\"thumUrl\": \"037D75C2-4437-6B07-1DEE-314A01B59068.jpg.jpg\",\n" +
                "\t\t\t\t\"isLocal\": \"1\",\n" +
                "\t\t\t\t\"pageVer\": \"1\",\n" +
                "\t\t\t\t\"pageDesc\": \"备注\",\n" +
                "\t\t\t\t\"uploadOrg\": \"0000\",\n" +
                "\t\t\t\t\"pageCrc\": \"7a5e81983fcfa6abbfffc96d09b5834e\",\n" +
                "\t\t\t\t\"pageSize\": \"282752\",\n" +
                "\t\t\t\t\"pageFormat\": \"image/jpeg\",\n" +
                "\t\t\t\t\"pageEncrypt\": \"0\",\n" +
                "\t\t\t\t\"originalName\": \"1111.jpg\",\n" +
                "\t\t\t\t\"pageExts\": {},\n" +
                "\t\t\t\t\"pageDescs\": {},\n" +
                "\t\t\t\t\"psInfoMap\": {}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"02727A44-316E-9891-99A9-631639496FC\": {\n" +
                "\t\t\t\t\"pageId\": \"02727A44-316E-9891-99A9-631639496FCA\",\n" +
                "\t\t\t\t\"createUser\": \"A000011635\",\n" +
                "\t\t\t\t\"createUserName\": \"操作员姓名\",\n" +
                "\t\t\t\t\"createTime\": \"2019-11-05 11:10:23.112\",\n" +
                "\t\t\t\t\"modifyUser\": \"操作员姓名\",\n" +
                "\t\t\t\t\"modifyTime\": \"2020-11-11 11:10:23.112\",\n" +
                "\t\t\t\t\"pageUrl\": \"037D75C2-4437-6B07-1DEE-314A01B59068.jpg\",\n" +
                "\t\t\t\t\"thumUrl\": \"037D75C2-4437-6B07-1DEE-314A01B59068.jpg.jpg\",\n" +
                "\t\t\t\t\"isLocal\": \"1\",\n" +
                "\t\t\t\t\"pageVer\": \"1\",\n" +
                "\t\t\t\t\"pageDesc\": \"备注\",\n" +
                "\t\t\t\t\"uploadOrg\": \"0000\",\n" +
                "\t\t\t\t\"pageCrc\": \"7a5e81983fcfa6abbfffc96d09b5834e\",\n" +
                "\t\t\t\t\"pageSize\": \"282752\",\n" +
                "\t\t\t\t\"pageFormat\": \"image/jpeg\",\n" +
                "\t\t\t\t\"pageEncrypt\": \"0\",\n" +
                "\t\t\t\t\"originalName\": \"1111.jpg\",\n" +
                "\t\t\t\t\"pageExts\": {},\n" +
                "\t\t\t\t\"pageDescs\": {},\n" +
                "\t\t\t\t\"psInfoMap\": {}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"02727A44-316E-9891-99A9-631639496F\": {\n" +
                "\t\t\t\t\"pageId\": \"02727A44-316E-9891-99A9-631639496FCA\",\n" +
                "\t\t\t\t\"createUser\": \"A000011635\",\n" +
                "\t\t\t\t\"createUserName\": \"操作员姓名\",\n" +
                "\t\t\t\t\"createTime\": \"2019-11-05 11:10:23.112\",\n" +
                "\t\t\t\t\"modifyUser\": \"操作员姓名\",\n" +
                "\t\t\t\t\"modifyTime\": \"2021-11-05 11:10:23.112\",\n" +
                "\t\t\t\t\"pageUrl\": \"037D75C2-4437-6B07-1DEE-314A01B59068.jpg\",\n" +
                "\t\t\t\t\"thumUrl\": \"037D75C2-4437-6B07-1DEE-314A01B59068.jpg.jpg\",\n" +
                "\t\t\t\t\"isLocal\": \"1\",\n" +
                "\t\t\t\t\"pageVer\": \"1\",\n" +
                "\t\t\t\t\"pageDesc\": \"备注\",\n" +
                "\t\t\t\t\"uploadOrg\": \"0000\",\n" +
                "\t\t\t\t\"pageCrc\": \"7a5e81983fcfa6abbfffc96d09b5834e\",\n" +
                "\t\t\t\t\"pageSize\": \"282752\",\n" +
                "\t\t\t\t\"pageFormat\": \"image/jpeg\",\n" +
                "\t\t\t\t\"pageEncrypt\": \"0\",\n" +
                "\t\t\t\t\"originalName\": \"1111.jpg\",\n" +
                "\t\t\t\t\"pageExts\": {},\n" +
                "\t\t\t\t\"pageDescs\": {},\n" +
                "\t\t\t\t\"psInfoMap\": {}\n" +
                "\t\t\t}\n" +
                "\t\t}],\n" +
                "\t}]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(mm);
        JSONArray pageInfoMaps = (JSONArray) jsonObject.get("data");
        JSONObject uo = (JSONObject) pageInfoMaps.get(0);
        JSONArray pageInfoMap = (JSONArray) uo.get("pageInfoMap");
        ArrayList<Object> objects = Lists.newArrayList();
        Object o1 = pageInfoMap.get(0);
        JSONObject jsonObject1 = JSONObject.parseObject(o1.toString());

        for (int i = 0; i < jsonObject1.size(); i++) {
            for (int j = i + 1; j < jsonObject1.size(); j++) {
                Object o = jsonObject1.get(j);
                System.out.println(JSONObject.parseObject(jsonObject1.get(j).toString()).get("modifyTime"));
                if (JSONObject.parseObject(objects.get(j).toString()).get("modifyTime").toString().compareTo(JSONObject.parseObject(objects.get(i).toString()).get("modifyTime").toString()) > 0) {
                    Object temp = objects.get(i);
                    objects.set(i, objects.get(j));
                    objects.set(j, temp);
                }
            }
        }
//      System.out.println(maps);
  /*  for (int i = 0; i <pageInfoMap.size()-1 ; i++) {
      for (int j = i+1; j < pageInfoMap.size(); j++) {
        int modifyTime = find("yyyy-MM-dd hh:mm:ss:SS", JSONObject.parseObject(pageInfoMap.get(j).toString()).get("modifyTime").toString());
        int modifyTime2 = find("yyyy-MM-dd hh:mm:ss:SS", JSONObject.parseObject(pageInfoMap.get(i).toString()).get("modifyTime").toString());
        if (modifyTime<=modifyTime2){
          Object temp = pageInfoMap.get(i);
          maps[i]= maps.get(j);
          maps.get(j) = temp;

        }

      }

    }*/

//    System.out.println(pageInfoMap);
    }

    private static int find(String format, String modifyTime) {
        int time = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(modifyTime);
            String strTime = date.getTime() + "";
            strTime = strTime.substring(0, 10);
            time = Integer.parseInt(strTime);

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

}
