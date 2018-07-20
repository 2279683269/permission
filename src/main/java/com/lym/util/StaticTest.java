package com.lym.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/19 14:00
 * @Description: 导出PDF
 */
@Slf4j
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }

    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    static int b = 112;

}
