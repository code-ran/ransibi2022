package com.ransibi;

import com.alibaba.fastjson.JSONArray;
import com.ransibi.pojo.StnInfoBean;
import com.ransibi.utils.ParseMngrXmlUtils;
import com.ransibi.utils.formatTimeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.DocumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: rsb
 * @description: 2022-08-24-10-14
 * @description:
 * @Version: 1.0.0
 */
public class RegulaUnitStnInfoAction {

    public static void main(String[] args) throws DocumentException {
        String path = "D:\\github\\ransibi2022\\dom4j\\m_hisstat_summary_2023_07_07.xml";
        String mngrId = "调管00";
        List<Map<String, String>> allStnInfoLst = ParseMngrXmlUtils.parseXmlMethods(path, mngrId, null);
        if (CollectionUtils.isNotEmpty(allStnInfoLst)){
            operRationStnInfo(allStnInfoLst);
        }
    }

    private static void operRationStnInfo(List<Map<String, String>> lstDetail) {
        List<StnInfoBean> stnInfoBeanList = new ArrayList<>();
        //厂站的数组
        JSONArray allStnIds = new JSONArray();
        // 一区厂站连通率
        JSONArray stn1ConnectRate = new JSONArray();
        // 二区厂站连通率
        JSONArray stn2ConnectRate = new JSONArray();
        // 厂站装置连通率
        JSONArray devConnectRate = new JSONArray();

        if (CollectionUtils.isEmpty(lstDetail)){
            return;
        }
        for (Map<String, String> map : lstDetail) {
            Map<String, String> mapTmp = new HashMap<>();
            mapTmp = map;
            StnInfoBean stnInfoBean = new StnInfoBean();
            for (Map.Entry<String, String> entry : mapTmp.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String tmps = "";
                if ("stnId".equals(key)) {
                    allStnIds.add(value);
                }
                if ("StnName".equals(key)) {
                    stnInfoBean.setStnNameMngr(value);
                }
                //装置运行统计
                if ("DevOfflineData.TotalRunTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long devRuntimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(devRuntimeTotal);
                        stnInfoBean.setDevRunTotalRunTime(tmps);
                    } else {
                        stnInfoBean.setDevRunTotalRunTime(value);
                    }
                }
                if ("DevOfflineData.TotalOfflineTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long devOfflinetimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(devOfflinetimeTotal);
                        stnInfoBean.setDevRunTotalOfflineTime(tmps);
                    } else {
                        stnInfoBean.setDevRunTotalOfflineTime(value);
                    }

                }
                if ("DevOfflineData.OfflineRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        stnInfoBean.setDevRunOfflineRate(rateTmptoStr);
                        devConnectRate.add(rateTmptoStr);
                    } else {
                        devConnectRate.add(value);
                    }

                }
                if ("DevOfflineData.TotalOverhaulTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long devOverhaultimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(devOverhaultimeTotal);
                        stnInfoBean.setDevRunTotalOverhaulTime(tmps);
                    } else {
                        stnInfoBean.setDevRunTotalOverhaulTime(value);
                    }

                }
                if ("DevOfflineData.OverhaulRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        stnInfoBean.setDevRunOverhaulRate(rateTmptoStr);
                    } else {
                        stnInfoBean.setDevRunOverhaulRate(value);
                    }

                }
                //装置接入统计
                if ("DevAccessData.TotalNum".equals(key)) {
                    stnInfoBean.setDevTotalNum(value);
                }
                if ("DevAccessData.AccessNum".equals(key)) {
                    stnInfoBean.setDevAccessNum(value);
                }
                if ("DevAccessData.AccessRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        stnInfoBean.setDevAccessRate(rateTmptoStr);
                    } else {
                        stnInfoBean.setDevAccessRate(value);
                    }

                }
                //1-厂站运行统计
                if ("StnOfflineDataI.TotalRunTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long stn1RuntimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(stn1RuntimeTotal);
                        stnInfoBean.setStn1TotalRunTime(tmps);
                    } else {
                        stnInfoBean.setStn1TotalRunTime(value);
                    }
                }
                if ("StnOfflineDataI.TotalOfflineTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long stn1OfflineTimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(stn1OfflineTimeTotal);
                        stnInfoBean.setStn1TotalOfflineTime(tmps);
                    } else {
                        stnInfoBean.setStn1TotalOfflineTime(value);
                    }

                }
                if ("StnOfflineDataI.OfflineRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        stnInfoBean.setStn1OfflineRate(rateTmptoStr);
                        stn1ConnectRate.add(rateTmptoStr);
                    } else {
                        stnInfoBean.setStn1OfflineRate(value);
                        stn1ConnectRate.add(value);
                    }
                }
                //2-厂站运行统计
                if ("StnOfflineDataII.TotalRunTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long stn2RunTimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(stn2RunTimeTotal);
                        stnInfoBean.setStn2TotalRunTime(tmps);
                    } else {
                        stnInfoBean.setStn2TotalRunTime(value);
                    }
                }
                if ("StnOfflineDataII.TotalOfflineTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long stn2OfflineTimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(stn2OfflineTimeTotal);
                        stnInfoBean.setStn2TotalOfflineTime(tmps);
                    } else {
                        stnInfoBean.setStn2TotalOfflineTime(value);
                    }
                }
                if ("StnOfflineDataII.OfflineRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        stnInfoBean.setStn2OfflineRate(rateTmptoStr);
                        stn2ConnectRate.add(rateTmptoStr);
                    } else {
                        stnInfoBean.setStn2OfflineRate(value);
                        stn2ConnectRate.add(value);
                    }
                }
            }
            stnInfoBeanList.add(stnInfoBean);

        }
        System.out.println(stnInfoBeanList);
        System.out.println(allStnIds);
        System.out.println(stn1ConnectRate);
        System.out.println(stn2ConnectRate);
        System.out.println(devConnectRate);
    }

    public static String formatDouble(String value) {
        float offlineRateTmp = Float.parseFloat(value);
        float rateTmp = offlineRateTmp * 100;
        rateTmp = (float) (Math.round(rateTmp * 10) / 10);
        return String.format("%.2f", rateTmp);
    }
}

