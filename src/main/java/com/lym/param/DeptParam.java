package com.lym.param;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/18 10:55
 * @Description:
 */
@Data
@ToString
public class DeptParam {

    private Integer id;

    @NotBlank(message = "部门名称不可以为null")
    @Length(max = 15, min = 2, message = "部门名称长度要在2~15之间")
    private String name;

    private Integer parentId;

    @NotNull(message = "展示顺序不可以为null")
    private Integer seq;

    @Length(max = 150, message = "备注的长度需要在150个字符之间")
    private String remark;
}
