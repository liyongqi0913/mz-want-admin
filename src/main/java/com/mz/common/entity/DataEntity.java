/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mz.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class DataEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.UUID)
	protected String id;
	protected String remarks;	// 备注
	@TableField( fill = FieldFill.INSERT)
	protected Date createdTime;	// 创建日期
	@TableField( fill = FieldFill.INSERT_UPDATE)
	protected Date updatedTime;	// 更新日期
	@TableField(exist = false)
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

	public DataEntity() {
		super();
	}

	public DataEntity(String id) {
		this.id = id;
	}


}
