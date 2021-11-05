package com.example.demo;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DateUtil {
    public static void main(String[] args) throws ParseException {
        String sss="2000-01-01 00:00:00";
        final Date date = DateUtils.parseDate(sss, "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
    }
}
