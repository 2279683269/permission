package com.lym.service.impl;

import com.google.common.base.Preconditions;
import com.lym.dao.SysDeptMapper;
import com.lym.dto.request.AddDeptRequestDTO;
import com.lym.dto.request.UpdateDeptRequestDTO;
import com.lym.exception.ParamException;
import com.lym.model.SysDept;
import com.lym.service.SysDeptService;
import com.lym.util.BeanValidator;
import com.lym.util.LevelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:03
 * @Description:
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void save(AddDeptRequestDTO param) {
        BeanValidator.check(param);
        /** 同一级层级下不能存在相同名称的部门*/
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept sysDept = SysDept.builder()
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        sysDept.setOperator("system"); //TODO 先写死
        sysDept.setOperateIp("127.0.0.1"); //TODO
        sysDept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(sysDept);
    }

    @Override
    public void update(UpdateDeptRequestDTO param) {
        BeanValidator.check(param);
        /** 同一级层级下不能存在相同名称的部门*/
        if (checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的部门不存在");

        SysDept after = SysDept.builder()
                .id(param.getId())
                .name(param.getName())
                .parentId(param.getParentId())
                .seq(param.getSeq())
                .remark(param.getRemark())
                .build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator("systemUpdate"); //TODO 先写死
        after.setOperateIp("127.0.0.1"); //TODO
        after.setOperateTime(new Date());

        updateWithChild(before, after);
    }

    @Transactional
    public void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (SysDept sysDept : deptList) {
                    String level = sysDept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        sysDept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

    private String getLevel(Integer deptId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (null == sysDept) {
            return null;
        }
        return sysDept.getLevel();
    }
}
