package com.lym.controller;

import com.lym.common.JsonData;
import com.lym.dto.request.TestValidateRequestDTO;
import com.lym.exception.PermissionException;
import com.lym.util.BeanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

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

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestValidateRequestDTO testValidateRequestDTO) {
        log.info("validate");
        try {
            Map<String, String> map = BeanValidator.validateObject(testValidateRequestDTO);
            if (map != null && map.entrySet().size() > 0) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    log.info("{}-->{}", entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {

        }
        return JsonData.sucess("test validate");
    }


}
