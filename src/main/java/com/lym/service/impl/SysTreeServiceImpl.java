package com.lym.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lym.dao.SysDeptMapper;
import com.lym.dto.DeptLevelDTO;
import com.lym.model.SysDept;
import com.lym.service.SysTreeService;
import com.lym.util.LevelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:43
 * @Description:
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {
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
        //level ->[dept1,dept2,...]
        Multimap<String, DeptLevelDTO> levelDeptMap = ArrayListMultimap.create();
        List<DeptLevelDTO> rootList = Lists.newArrayList();
        for (DeptLevelDTO dto : dtoList) {
            levelDeptMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        //按照seq从小到大排序
        Collections.sort(rootList, new Comparator<DeptLevelDTO>() {
            @Override
            public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);

        return rootList;
    }

    public void transformDeptTree(List<DeptLevelDTO> deptLevelList, String level, Multimap<String, DeptLevelDTO> levelDeptMap) {
        for (int i = 0; i < deptLevelList.size(); i++) {
            //遍历该层的每个元素
            DeptLevelDTO deptLevelDTO = deptLevelList.get(i);
            //处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, deptLevelDTO.getId());
            //处理下一层
            List<DeptLevelDTO> tempDeptList = (List<DeptLevelDTO>) levelDeptMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempDeptList)) {
                //排序
                Collections.sort(tempDeptList, deptSeqComparator);
                //设置下一个部门
                deptLevelDTO.setDeptList(tempDeptList);
                //进入下一层处理
                transformDeptTree(tempDeptList, nextLevel, levelDeptMap);
            }
        }
    }

    public Comparator<DeptLevelDTO> deptSeqComparator = new Comparator<DeptLevelDTO>() {
        @Override
        public int compare(DeptLevelDTO o1, DeptLevelDTO o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
