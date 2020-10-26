package com.mz.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseEntity {

    @TableField(exist = false)
    private Integer pageNo;
    @TableField(exist = false)
    private Integer pageSize;

    @TableField(exist = false)
    private String beginTime;
    @TableField(exist = false)
    private String endTime;
}
