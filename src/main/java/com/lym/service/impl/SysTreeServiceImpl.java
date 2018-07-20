package com.lym.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lym.dao.SysDeptMapper;
import com.lym.dto.DeptLevelDTO;
import com.lym.model.SysDept;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:43
 * @Description:
 */
@Service
public class SysTreeServiceImpl {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    public List<DeptLevelDTO> deptTree() {
        List<SysDept> sysDepts = sysDeptMapper.getAllDept();

        List<DeptLevelDTO> dtoList = Lists.newArrayList();
        for (SysDept sysDept : sysDepts) {
            DeptLevelDTO dto = DeptLevelDTO.adapt(sysDept);
            dtoList.add(dto);
        }

        return deptListToTree(dtoList);
    }

    public List<DeptLevelDTO> deptListToTree(List<DeptLevelDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        Multimap<String, DeptLevelDTO> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDTO> rootList = Lists.newArrayList();
        for (DeptLevelDTO dto : dtoList) {

        }

        return null;
    }
}
