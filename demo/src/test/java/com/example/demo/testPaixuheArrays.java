package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class testPaixuheArrays {
    //获取指定集合中相加的数据符合 samplesNumber和的组合数据
    public static void main(String[] args) {
           //模拟一个数字集合
            //模拟一个数字集合
        List<TestDTO> l=new ArrayList<TestDTO>();
        for(int i=0;i<10;i++){
            TestDTO d=new TestDTO();
            d.setId(i+"");
            d.setNum(i);
            l.add(d);
        }
        List<List<TestDTO>>  list=   Test(l,1);
        for (int i=0;i<list.size();i++){
            String str="";
            for(int j=0;j<list.get(i).size();j++){
                str=str+list.get(i).get(j).getNum()+"+";
            }
            System.out.println("第"+i+"个结果："+str.substring(0,str.length()-1));
        }

    }
    static class TestDTO  {
        String id; //id
        Integer num;//数字
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

    }

    public  static List<List<TestDTO>>  Test(List<TestDTO> dtoParam, Integer samplesNumber) {
        List<List<TestDTO>>  reust = new ArrayList<List<TestDTO>>();
        int outsum=1;
        int innersum=1;
        List<TestDTO> lodesucDatas=null;
        for (int i = 0; i < dtoParam.size(); i++) {
            int numcont =  dtoParam.get(i).getNum();
//            StringBuffer   str=new StringBuffer(dtoParam.get(i).getNum()+"+");//用于控制台打印显示，和逻辑无关
            boolean outFlag=true;
            while(outFlag){
                if(outFlag=false){
                    break;
                }
                if(dtoParam.size()==outsum){
                    outFlag=false;
                    break;
                }
                boolean innerFlag=true;
                while(innerFlag){
                    if(dtoParam.size()==innersum){
                        outsum++;
                        innerFlag=false;
                        innersum=outsum;
                        break;
                    }
                    lodesucDatas = new ArrayList<TestDTO>();
                    lodesucDatas.add(dtoParam.get(i));
                    for(int j=innersum;j<dtoParam.size();j++){
                        numcont = numcont + dtoParam.get(j).getNum();
                        lodesucDatas.add(dtoParam.get(j));
//                        str.append(dtoParam.get(j).getNum()+"+");//用于控制台打印显示，和逻辑无关
//                        System.out.println(str.substring(0,str.length()-1));//用于控制台打印显示，和逻辑无关
                        if (numcont == samplesNumber) {
                            reust.add(lodesucDatas);
                            break;
                        }
                        if(dtoParam.size()-j==1){
                            numcont =  dtoParam.get(i).getNum();
//                            str=new StringBuffer(dtoParam.get(i).getNum()+"+");//用于控制台打印显示，和逻辑无关
                            innersum++;
                            break;
                        }
                    }
                }
            }
        }
        return  reust;
    }
}
