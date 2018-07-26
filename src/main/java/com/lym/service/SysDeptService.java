package com.lym.service;

import com.lym.dto.request.AddDeptRequestDTO;
import com.lym.dto.request.UpdateDeptRequestDTO;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 11:02
 * @Description:
 */
public interface SysDeptService {

    public void save(AddDeptRequestDTO param);


    public void update(UpdateDeptRequestDTO param);
}
