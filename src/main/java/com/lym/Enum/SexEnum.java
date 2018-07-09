package com.lym.Enum;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/9 15:02
 * @Description: 性别
 */
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    public int key;
    public String sex;

    SexEnum(int key, String sex) {
        this.key = key;
        this.sex = sex;
    }

    public static String getValueByKey(int key) {
        String sex = "";
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.key == key) {
                sex = sexEnum.sex;
                break;
            }
        }
        return sex;
    }

    public static int getKeyByValue(String sex) {
        int key = 0;
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.sex.equals(sex)) {
                key = sexEnum.key;
                break;
            }
        }
        return key;
    }
}
