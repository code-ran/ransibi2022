package com.ransibi.pojo;

import lombok.Data;

/**
 * @description:调管单位下的厂站相关属性bean
 * @author: rsb
 * @description: 2022-08-24-13-48
 * @description:
 * @Version: 1.0.0
 */
@Data
public class StnInfoBean {

    private String stnNameMngr;   //厂站名称
    //装置接入统计
    private String devTotalNum;   //总数
    private String devAccessNum;  //接入数
    private String devAccessRate; //接入率
    //装置运行统计
    private String devRunTotalRunTime; //总运行时间
    private String devRunTotalOfflineTime; //中断时间
    private String devRunOfflineRate; //可用率
    private String devRunTotalOverhaulTime; //检修时间
    private String devRunOverhaulRate; //检修率
    //1-厂站运行统计
    private String stn1TotalRunTime;     //总运行时间
    private String stn1TotalOfflineTime; //中断时间
    private String stn1OfflineRate;      //可用率
    //2-厂站运行统计
    private String stn2TotalRunTime;      //总运行时间
    private String stn2TotalOfflineTime;  //中断时间
    private String stn2OfflineRate;       //可用率

}
