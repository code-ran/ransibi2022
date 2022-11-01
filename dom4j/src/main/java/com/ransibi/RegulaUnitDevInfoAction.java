package com.ransibi;

import com.ransibi.pojo.DevInfoBean;
import com.ransibi.utils.ParseMngrXmlUtils;
import com.ransibi.utils.formatTimeUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

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
public class RegulaUnitDevInfoAction {

    public static void main(String[] args) {
        String path = "D:\\github\\ransibi2022\\dom4j\\m_hisstat_summary_2023_07_07.xml";
        String mngrId = "调管00";
        String stationId = "新志";
        List<Map<String, String>> devInfoLst = ParseMngrXmlUtils.parseXmlMethods(path, mngrId, stationId);
        if (CollectionUtils.isNotEmpty(devInfoLst)){
            operationDevInfo(devInfoLst);
        }
    }

    private static void operationDevInfo(List<Map<String, String>> devInfoLst) {

        List<DevInfoBean> devInfoBeanList = new ArrayList<>();

        for (Map<String, String> stringStringMap : devInfoLst) {
            Map<String, String> mapdevInfo = new HashMap<>();
            mapdevInfo = stringStringMap;
            DevInfoBean devInfoBean = new DevInfoBean();
            for (Map.Entry<String, String> entry : mapdevInfo.entrySet()) {
                String tmps;
                String key = entry.getKey();
                String value = entry.getValue();
                if ("DevId".equals(key)) {
                    devInfoBean.setDevId(value);
                }
                if ("DevName".equals(key)) {
                    devInfoBean.setDevName(value);
                }
                if ("DevOfflineData.TotalRunTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long devRuntimeTotal = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(devRuntimeTotal);
                        devInfoBean.setDevTotalRunTime(tmps);
                    } else {
                        devInfoBean.setDevTotalRunTime(value);
                    }

                }
                if ("DevOfflineData.TotalOfflineTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long devOfflineTime = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(devOfflineTime);
                        devInfoBean.setDevTotalOfflineTime(tmps);
                    } else {
                        devInfoBean.setDevTotalOfflineTime(value);
                    }

                }
                if ("DevOfflineData.OfflineRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        devInfoBean.setDevOfflineRate(rateTmptoStr);
                    } else {
                        devInfoBean.setDevOfflineRate(value);
                    }

                }
                if ("DevOfflineData.TotalOverhaulTime".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        long devOverhaulTime = Long.parseLong(value);
                        tmps = formatTimeUtils.formatTime(devOverhaulTime);
                        devInfoBean.setDevTotalOverhaulTime(tmps);
                    } else {
                        devInfoBean.setDevTotalOverhaulTime(value);
                    }

                }
                if ("DevOfflineData.OverhaulRate".equals(key)) {
                    if (NumberUtils.isNumber(value)) {
                        String rateTmptoStr = formatDouble(value);
                        devInfoBean.setDevOverhaulRate(rateTmptoStr);
                    } else {
                        devInfoBean.setDevOverhaulRate(value);
                    }
                }

            }
            devInfoBeanList.add(devInfoBean);
        }
        System.out.println(devInfoBeanList);
    }

    public static String formatDouble(String value) {
        float offlineRateTmp = Float.parseFloat(value);
        float rateTmp = offlineRateTmp * 100;
        rateTmp = (float) (Math.round(rateTmp * 10) / 10);

        return String.format("%.2f", rateTmp);
    }
}

