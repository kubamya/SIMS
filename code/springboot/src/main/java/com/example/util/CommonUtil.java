package com.example.util;

import com.example.dept.service.impl.DeptServiceImpl;
import com.example.model.Com;
import com.example.model.Dept;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static List<Map<String, Object>> getDeptTreeByComId(String deptid, List<Dept> depts){
        List<Map<String, Object>> tree = new ArrayList<>();
        for(Dept dept : depts){
            if(deptid.equals(dept.getCPid())){
                Map<String, Object> treeNode = new HashMap<>();
                treeNode.put("label", dept.getCName());
                treeNode.put("id", dept.getCId());
                treeNode.put("children",getDeptTreeByComId(dept.getCId(), depts));

                tree.add(treeNode);
            }
        }
        return tree;
    }
}
