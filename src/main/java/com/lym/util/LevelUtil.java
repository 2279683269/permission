package com.lym.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:21
 * @Description:
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    //0
    //0.1
    //0.1.2
    //0.1.3
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
