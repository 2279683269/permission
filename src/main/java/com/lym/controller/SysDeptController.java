package com.lym.controller;

import com.lym.common.JsonData;
import com.lym.dto.request.AddDeptRequestDTO;
import com.lym.service.SysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:00
 * @Description: 部门相关controller
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(AddDeptRequestDTO param) {
        sysDeptService.save(param);
        return JsonData.sucess();
    }
}
