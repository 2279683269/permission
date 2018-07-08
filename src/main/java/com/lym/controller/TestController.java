package com.lym.controller;

import com.lym.common.JsonData;
import com.lym.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/5 14:43
 * @Description: 测试启动
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
//        throw new RuntimeException("test exception");
        throw new PermissionException("test exception");
//        return JsonData.sucess("hello,permission");
    }
}
