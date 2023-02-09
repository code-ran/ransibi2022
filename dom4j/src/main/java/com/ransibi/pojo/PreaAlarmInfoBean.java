package com.ransibi.pojo;

/**
 * @description:
 * @author: rsb
 * @description: 2023-02-09-15-21
 * @description:
 * @Version: 1.0.0
 */
public class PreaAlarmInfoBean {
    // 地区id
    private String areaId;
    // 地区名称
    private String areaName;
    //厂站id
    private String stnId;
    //厂站名称
    private String stnName;
    //装置id
    private String devId;
    //装置名称
    private String devName;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStnId() {
        return stnId;
    }

    public void setStnId(String stnId) {
        this.stnId = stnId;
    }

    public String getStnName() {
        return stnName;
    }

    public void setStnName(String stnName) {
        this.stnName = stnName;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    @Override
    public String toString() {
        return "PreaAlarmInfoBean{" +
                "areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", stnId='" + stnId + '\'' +
                ", stnName='" + stnName + '\'' +
                ", devId='" + devId + '\'' +
                ", devName='" + devName + '\'' +
                '}';
    }
}
