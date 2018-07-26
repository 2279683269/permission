package com.lym.service;

import com.lym.dto.DeptLevelDTO;

import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/25 16:34
 * @Description: 计算树结构的Service
 */
public interface SysTreeService {

    public List<DeptLevelDTO> deptTree();

    public List<DeptLevelDTO> deptListToTree(List<DeptLevelDTO> dtoList);
}
