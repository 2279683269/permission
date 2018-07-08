package com.lym.Enum;

import com.alibaba.druid.support.spring.stat.annotation.Stat;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/8 22:47
 * @Description:
 */
public enum ExceptionEnum {

    DEFAULT_MSG("001", "DEFAULT_MSG", "系统异常");

    public String errorNo;

    public String errorCode;

    public String errorDesc;

    ExceptionEnum(String errorNo, String errorCode, String errorDesc) {
        this.errorNo = errorNo;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    /**
     * 根据errorCode获取errorNo
     *
     * @param errorCode
     * @return
     */
    public static String getKeyByValue(String errorCode) {
        String key = "000";
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.errorCode.equals(errorCode)) {
                key = exceptionEnum.errorNo;
                break;
            }
        }
        return key;
    }

    /**
     * 根据errorCode获取errorDesc
     *
     * @param errorCode
     * @return
     */
    public static String getDescByValue(String errorCode) {
        String desc = "";
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.errorCode.equals(errorCode)) {
                desc = exceptionEnum.errorDesc;
                break;
            }
        }
        return desc;
    }

    /**
     * 根据errorKey获取errorCode
     *
     * @param errorNo
     * @return
     */
    public static String getValueByKey(String errorNo) {
        String code = "";
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (exceptionEnum.errorNo.equals(errorNo)) {
                code = exceptionEnum.errorNo;
                break;
            }
        }
        return code;
    }
}
