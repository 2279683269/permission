package com.lym.controller;

import com.lym.common.JsonData;
import com.lym.param.DeptParam;
import com.lym.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:00
 * @Description:
 */
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.sucess();
    }
}
