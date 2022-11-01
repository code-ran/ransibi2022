package com.ransibi.easyexceldemo;

import com.alibaba.excel.EasyExcel;

import java.util.*;

/**
 * @description:
 * @author: rsb
 * @description: 2022-09-06-10-44
 * @description:
 * @Version: 1.0.0
 */
public class Demo01 {
    private final static String fileName = "E:\\" + "template.xlsx";
    public static void main(String[] args) {
        //表头信息
        List<String> heads = new ArrayList<>();
        heads.add("横坐标");
        heads.add("纵坐标1");
        heads.add("纵坐标2");
        heads.add("纵坐标3");
        easyUtil(heads);
    }
    public static void easyUtil(List<String> heads){
        List<List<String>> hs = new ArrayList<>();
        for (String s : heads) {
            hs.add(Arrays.asList(s));
        }
        EasyExcel.write(fileName).head(hs).sheet("template").doWrite(null);
        System.out.println("导出成功");
    }
}


