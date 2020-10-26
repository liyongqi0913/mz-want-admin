package com.mz.modules.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(value = "角色表", description = "角色表")
@Data
public class TSysRole extends AdminEntity {
    public interface PERM_TYPE {
        String ALL = "A";//所有权限
        String PART = "P";//部分权限
    }

    @TableId(value="id", type= IdType.UUID)
    private String id;
    private String roleName;
    private String roleDesc;
    private Integer state;
    private String deptPermType;
    @TableField(exist = false)
    private List<TSysPerm> permList;
    private String userId;

    public TSysRole() {
    }

    public TSysRole(String id) {
        this.id = id;
    }

    public TSysRole(String id,String userId) {
        this.id = id;
        this.userId = userId;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleDesc=").append(roleDesc);
        sb.append(", state=").append(state);
        sb.append(", deptPermType=").append(deptPermType);
        sb.append("]");
        return sb.toString();
    }
}
