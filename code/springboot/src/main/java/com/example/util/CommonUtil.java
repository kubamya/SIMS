package com.example.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 公用方法
 */
public class CommonUtil {

    /**
     * 获取32位UUID
     * @return
     */
    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 获取当前日期
     * @return
     */
    public static Date getCurDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurDateTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(df.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
