package com.lym.controller;

import com.lym.common.JsonData;
import com.lym.dto.DeptLevelDTO;
import com.lym.dto.request.AddDeptRequestDTO;
import com.lym.dto.request.UpdateDeptRequestDTO;
import com.lym.service.SysDeptService;
import com.lym.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private SysTreeService sysTreeService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(@RequestBody AddDeptRequestDTO param) {
        sysDeptService.save(param);
        return JsonData.sucess();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData tree() {
        List<DeptLevelDTO> deptLevelList = sysTreeService.deptTree();
        return JsonData.sucess();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateDept(UpdateDeptRequestDTO param) {
        sysDeptService.update(param);
        return JsonData.sucess();
    }
}
