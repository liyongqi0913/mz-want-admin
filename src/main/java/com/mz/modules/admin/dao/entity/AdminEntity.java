package com.mz.modules.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.mz.common.entity.DataEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class AdminEntity extends DataEntity implements Serializable {

    @TableField(exist = false)
    private Integer pageNo;
    @TableField(exist = false)
    private Integer pageSize;

    //基础
    @TableField(exist = false)
    private String beginTime;
    @TableField(exist = false)
    private String endTime;


    @TableField( fill = FieldFill.INSERT, exist = false)
    protected TSysUser createdBy;	// 创建者
    @TableField( fill = FieldFill.INSERT_UPDATE, exist = false)
    protected TSysUser updatedBy;	// 更新者

    @TableField(exist = false)
    protected String remarks;	// 备注

    @TableField(exist = false)
    private Map<String,Object> params;


    public Map<String, Object> getParams() {
        if(params == null){
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
