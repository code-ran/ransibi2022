package com.ransibi;

import com.ransibi.utils.ParsePreAlarmXmlUtil;

/**
 * @description:
 * @author: rsb
 * @description: 2023-02-09-11-37
 * @description:
 * @Version: 1.0.0
 */
public class test {
    public static void main(String[] args) {
        String path = "src/overlmt_warning.xml";
        ParsePreAlarmXmlUtil.parseXmlMethods(path,null,null);
    }
}
