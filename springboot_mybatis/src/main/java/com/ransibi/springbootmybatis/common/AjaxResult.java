package com.ransibi.springbootmybatis.common;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "message";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 保护装置名称为空
     */
    public static AjaxResult errordev(String msg) {
        return new AjaxResult(HttpStatus.prodevError, msg);
    }

    /**
     * 厂站名称为空
     */
    public static AjaxResult errorstn(String msg) {
        return new AjaxResult(HttpStatus.stnError, msg);
    }

    /**
     * 没有开始时间
     */
    public static AjaxResult errorBeginTime(String msg) {
        return new AjaxResult(HttpStatus.beginTimeError, msg);
    }

    /**
     * 没有结束时间
     */
    public static AjaxResult errorEndTime(String msg) {
        return new AjaxResult(HttpStatus.endTimestnError, msg);
    }

    /**
     * 时间范围不对，不得超过一小时
     */
    public static AjaxResult errorPeriod(String msg) {
        return new AjaxResult(HttpStatus.period, msg);
    }

    /**
     * 没有找到录波文件路径
     *
     * @param msg
     * @return
     */
    public static AjaxResult errorPath(String msg) {
        return new AjaxResult(HttpStatus.pathError, msg);
    }

    /**
     * 没找到录波文件
     */
    public static AjaxResult errorPathFile(String msg) {
        return new AjaxResult(HttpStatus.pathFileError, msg);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }
}
