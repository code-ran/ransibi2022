package com.ransibi.pojo;

import lombok.Data;

/**
 * @description:厂站下的装置相关属性bean
 * @author: rsb
 * @description: 2022-08-25-15-18
 * @description:
 * @Version: 1.0.0
 */
@Data
public class DevInfoBean {
    private String devName;//装置名称
    private String devId;//装置Id
    private String devTotalRunTime;//总运行时间
    private String devTotalOfflineTime;//中断时间
    private String devOfflineRate;//可用率
    private String devTotalOverhaulTime;//检修时间
    private String devOverhaulRate;//检修率
}
