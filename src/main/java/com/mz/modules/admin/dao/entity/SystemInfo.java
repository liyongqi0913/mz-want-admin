package com.mz.modules.admin.dao.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "系统信息表", description = "系统信息表")
@Data
public class SystemInfo {

    private String systemName;

}
