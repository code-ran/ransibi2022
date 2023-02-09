package com.ransibi.utils;

import com.alibaba.fastjson.JSONObject;
import com.ransibi.pojo.PreaAlarmInfoBean;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @description:
 * @author: rsb
 * @description: 2023-02-09-11-06
 * @description:
 * @Version: 1.0.0
 */
public class ParsePreAlarmXmlUtil {
    public static List<Map<String, String>> parseXmlMethods(String path, String areaName, String stationId) {
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new File(path));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        if (doc != null) {
            //地区节点
            List<Element> lstArea = doc.selectNodes("Statistic/Area");
            if (CollectionUtils.isNotEmpty(lstArea)) {
                for (Element eleArea : lstArea) {
                    PreaAlarmInfoBean areaBean = new PreaAlarmInfoBean();
                    areaBean.setAreaId(eleArea.attribute(0).getValue());
                    areaBean.setAreaName(eleArea.attribute(1).getValue());
                    //定位到厂站节点
                    List<Element> lstStn = eleArea.selectNodes("Station");
                    for (Element eleStn : lstStn) {
                        PreaAlarmInfoBean stnBean = new PreaAlarmInfoBean();
                        stnBean.setStnId(eleStn.attribute(0).getValue());
                        stnBean.setStnName(eleStn.attribute(1).getValue());
                        //装置保护节点
                        List<Element> lstDev = eleStn.selectNodes("Dev");
                        for (Element eleDev : lstDev) {
                            PreaAlarmInfoBean devBean = new PreaAlarmInfoBean();
                            devBean.setDevId(eleDev.attribute(0).getValue());
                            devBean.setDevName(eleDev.attribute(1).getValue());
                            List<Element> lstLimitInfo = eleDev.selectNodes("OverLimitInfo");
                            int alarmType = 0;
                            for (Element elOverLimt : lstLimitInfo) {
                                alarmType = Integer.parseInt(lstLimitInfo.get(0).attribute(0).getValue());
                                if (alarmType == 2) {
                                    List<Element> alarmInfoLst = elOverLimt.selectNodes("Alarm");
                                    for (Element eleAlarm : alarmInfoLst) {
                                        List<Element> alarmTmp = eleAlarm.selectNodes("InfofunAlarm");
                                        for (Element tmps : alarmTmp) {
                                            List<Element> alarmTmps = tmps.selectNodes("AlarmDetail");
                                            int num = 0 ;
                                            String msg = null;
                                            String b = null;
                                            for (Element x : alarmTmps) {
                                                String a = x.attribute(0).getValue();
                                                 b = a;
                                                 if (b.equals(a)){
                                                     num++;
                                                 }
                                                 msg = b;
                                            }
                                            System.out.println("地区名称：" + areaBean.getAreaName());
                                            System.out.println("厂站名称：" + stnBean.getStnName());
                                            System.out.println("设备名称：" + devBean.getDevName());
                                            System.out.println("消息内容：" + msg);
                                            System.out.println("次数：" + num);
                                            jsonObject.put("areaName",areaBean.getAreaName());
                                            jsonObject.put("stnName", stnBean.getStnName());
                                            jsonObject.put("devName",devBean.getDevName());
                                            jsonObject.put("MsgInfo",msg);
                                            jsonObject.put("alarmNum",num);

                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return null;
    }


    //每个调管单位下的统计
    public static Map<String, String> setDataByNode(Element mngrEle) {

        Map<String, String> mpBaseData = new HashMap();
        String key = "";
        String MngrName = mngrEle.attributeValue("MngrName", "");
        String stnNum = mngrEle.attributeValue("StnNum", "0");
        List lstAreaSum = mngrEle.selectNodes("MngrSum/*");//MngrSum节点下所有标签的信息

        for (Object o : lstAreaSum) {
            Element mngrSumEle = (Element) o;
            key = mngrSumEle.getName();
            Iterator ite = mngrSumEle.attributeIterator();

            while (ite.hasNext()) {
                Attribute attr = (Attribute) ite.next();
                String k = key + "." + attr.getName();
                mpBaseData.put(k, attr.getValue());
            }
        }
        return mpBaseData;
    }


    //获取调管单位下所有厂站的信息
    public static Map<String, String> setDataByNodeForByMngr(Element stnEle) {
        Map<String, String> mpData = new HashMap();
        List lstStData = stnEle.selectNodes("StnDataSum/*");//乔嘉下厂站和装置信息统计
        String key = "";
        for (Object lstStDatum : lstStData) {
            Element i$ = (Element) lstStDatum;
            key = i$.getName();
            Iterator stDataEle = i$.attributeIterator();

            while (stDataEle.hasNext()) {
                Attribute attr = (Attribute) stDataEle.next();
                String k = key + "." + attr.getName();
                mpData.put(k, attr.getValue());
            }
        }
        return mpData;
    }


    public static List<Map<String, String>> getReguLaUnitDevInfo(Element stnEle) {
        List lstStData = stnEle.selectNodes("DevDataSum/*");
        Iterator dev2Info = null;
        Iterator i$1 = lstStData.iterator();
        Map<String, String> mpData = new HashMap<>();
        List<Map<String, String>> lstTmp = new ArrayList<>();

        while (i$1.hasNext()) {
            Element stDataEle1 = (Element) i$1.next();
            mpData = setDataByNodeForDevInfo(stDataEle1);
            String devName = stDataEle1.attributeValue("DevName");
            String devId = stDataEle1.attributeValue("DevId");
            mpData.put("DevId", devId);
            mpData.put("DevName", devName);
            lstTmp.add(mpData);
        }
        return lstTmp;
    }


    public static Map<String, String> setDataByNodeForDevInfo(Element devEle) {
        List lstStData = devEle.selectNodes("DevOfflineData");
        String key = "";
        Iterator i$ = lstStData.iterator();
        Map<String, String> mpSummaryData = new HashMap();

        while (i$.hasNext()) {
            Element stDataEle = (Element) i$.next();
            key = stDataEle.getName();
            Iterator ite = stDataEle.attributeIterator();

            while (ite.hasNext()) {
                Attribute attr = (Attribute) ite.next();
                String k = key + "." + attr.getName();
                mpSummaryData.put(k, attr.getValue());
            }
        }
        return mpSummaryData;
    }

}
