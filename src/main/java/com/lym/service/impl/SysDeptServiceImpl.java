package com.lym.service.impl;

import com.lym.dao.SysDeptMapper;
import com.lym.dto.request.AddDeptRequestDTO;
import com.lym.exception.ParamException;
import com.lym.model.SysDept;
import com.lym.service.SysDeptService;
import com.lym.util.BeanValidator;
import com.lym.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    private boolean checkExist(Integer parentId, String deptName, Integer deptId) {
        //TODO
        return true;
    }

    private String getLevel(Integer deptId) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptId);
        if (null == sysDept) {
            return null;
        }
        return sysDept.getLevel();
    }
}
