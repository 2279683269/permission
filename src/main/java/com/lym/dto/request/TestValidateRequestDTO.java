package com.lym.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/9 11:35
 * @Description: 测试验证RequestDTO
 */
@Data
public class TestValidateRequestDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;
}
