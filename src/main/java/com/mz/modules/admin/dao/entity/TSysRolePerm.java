package com.mz.modules.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@TableName("t_sys_role_perm")
@ApiModel(value = "角色权限表", description = "角色权限表")
@Data
public class TSysRolePerm extends AdminEntity {

    private String id;
    private String roleId;
    private String permId;
    private String resourceId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", permId=").append(permId);
        sb.append("]");
        return sb.toString();
    }
}
