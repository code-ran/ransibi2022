package com.ransibi.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @description: 解析按调管单位统计
 * @author: rsb
 * @description: 2022-08-25-13-44
 * @description: 解析按调管单位统计
 * @Version: 1.0.0
 */
public class ParseMngrXmlUtils {

    public static List<Map<String, String>> parseXmlMethods(String path, String mngrId, String stationId) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(path));
            Node mngrNode = document.selectSingleNode("Statistic/MngrNode");
//            String mngrId = "调管00";
//            String stationId = "新志";
            List lstMngr = mngrNode.selectNodes("Mngr[@MngrName=\"" + mngrId + "\"]");
            Iterator i$ = lstMngr.iterator();
            if (StringUtils.isNotEmpty(mngrId) && StringUtils.isEmpty(stationId)) {
                Map<String, String> mapTmp = new HashMap<>();
                List<Map<String, String>> mngrInfoList = new ArrayList<>();
                List<Map<String, String>> lst = new ArrayList<>();
                while (i$.hasNext()) {
                    Element mngrEle = (Element) i$.next();
                    mapTmp = setDataByNode(mngrEle);//每个调管单位下的统计
                    mngrInfoList.add(mapTmp);
                    List lstArea = mngrEle.selectNodes("StnSum/Station");//获取StnSum节点下所有Station标签的信息
                    for (Object o : lstArea) {
                        Element stnEle = (Element) o;
                        String stnName = stnEle.attributeValue("StnName", "");//获取Station标签里，StnId的属性值
                        String stnId = stnEle.attributeValue("StnId", "");//获取Station标签里，StnId的属性值
                        Map<String, String> mapTmp1 = setDataByNodeForByMngr(stnEle);
                        mapTmp1.put("StnName", stnName);
                        mapTmp1.put("stnId", stnId);
                        lst.add(mapTmp1);
                    }
                }
                return lst;
            } else if (StringUtils.isNotEmpty(mngrId) && StringUtils.isNotEmpty(stationId)) {
                //厂站的详细--查询设备相关信息
                List<Map<String, String>> lst = new ArrayList<>();
                List<Map<String, String>> mapTmpLst = new ArrayList<>();
                while (i$.hasNext()) {
                    Element mngrEle = (Element) i$.next();
                    List lstStn = mngrEle.selectNodes("StnSum/Station[@StnId=\"" + stationId + "\"]");
                    for (Object o : lstStn) {
                        Element stnEle = (Element) o;
                        String stnName = stnEle.attributeValue("StnName", "");//获取Station标签里，StnId的属性值
                        String stnId = stnEle.attributeValue("StnId", "");//获取Station标签里，StnId的属性值
                        mapTmpLst = getReguLaUnitDevInfo(stnEle);
                        lst.addAll(mapTmpLst);
                    }
                }
                return lst;
            }


        } catch (Exception exception) {
            exception.printStackTrace();
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
