package com.mz.modules.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "部门表", description = "部门表")
@Data
public class TSysDept extends AdminEntity {

    @TableId(value="id", type= IdType.UUID)
    private String id;
    private String name;
    private String pid;
    private String pids;
    private Integer state;
    private String leader;
    private String phone;
    private String mail;
    @TableField(exist = false)
    private String roleId;
    @TableField(exist = false)
    private String permId;
    private String areaCode;
    private static final long serialVersionUID = 1L;

    public TSysDept() {
    }

    public TSysDept(String id) {

        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", pids=").append(pids);
        sb.append(", state=").append(state);
        sb.append(", leader=").append(leader);
        sb.append(", phone=").append(phone);
        sb.append(", mail=").append(mail);
        sb.append("]");
        return sb.toString();
    }
}
