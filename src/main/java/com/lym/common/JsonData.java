package com.lym.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/8 22:31
 * @Description: 返回json数据格式
 */
@Data
public class JsonData {
    /**
     * 返回结果是否成功
     */
    private boolean ret;

    /**
     * 返回结果的相关处理消息
     */
    private String msg;

    /**
     * 返回结果的对象
     */
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    /**
     * 成功时,返回消息处理
     *
     * @param msg
     * @return
     */
    public static JsonData sucess(String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.setMsg(msg);
        return jsonData;
    }

    /**
     * 成功时,返回数据和处理消息
     *
     * @param obj
     * @param msg
     * @return
     */
    public static JsonData sucess(Object obj, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(obj);
        jsonData.setMsg(msg);
        return jsonData;
    }

    /**
     * 成功时,返回数据
     *
     * @param obj
     * @return
     */
    public static JsonData sucess(Object obj) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(obj);
        return jsonData;
    }

    /**
     * 成功时,不带任何参数
     *
     * @return
     */
    public static JsonData sucess() {
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }

    /**
     * 失败时返回错误信息
     *
     * @param msg
     * @return
     */
    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
