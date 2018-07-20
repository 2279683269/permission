package com.lym.dto;

import com.google.common.collect.Lists;
import com.lym.model.SysDept;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:35
 * @Description:
 */
@Data
@ToString
public class DeptLevelDTO extends SysDept {

    private List<DeptLevelDTO> deptList = Lists.newArrayList();

    public static DeptLevelDTO adapt(SysDept dept) {
        DeptLevelDTO dto = new DeptLevelDTO();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
