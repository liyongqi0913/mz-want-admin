package com.mz.modules.admin.dao.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "用户角色表", description = "用户角色表")
@Data
public class TSysUserRole extends AdminEntity {

    private String userId;
    private String roleId;
    private TSysRole role;

    private static final long serialVersionUID = 1L;

    public TSysUserRole() {
    }

    public TSysUserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public TSysUserRole(String userId) {
        this.userId = userId;
    }

    public TSysUserRole(String id, String userId, String roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}
