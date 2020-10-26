package com.mz.modules.admin.dao.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "菜单表", description = "菜单表")
@Data
public class TSysMenu extends AdminEntity {
    public interface TYPE{
        final String MENU = "M";
        final String BUTTON = "B";
        final String DIRECTORY = "D";
    }

    private String name;
    private String pid;
    private String type;
    private String path;
    private String component;
    private String icon;
    private Integer state;
    private Integer sort;
    private String userId;
    private String permName;
    private String roleId;
    private String permId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", type=").append(type);
        sb.append(", path=").append(path);
        sb.append(", component=").append(component);
        sb.append(", icon=").append(icon);
        sb.append(", state=").append(state);
        sb.append(", sort=").append(sort);
        sb.append(", permName=").append(sort);
        sb.append("]");
        return sb.toString();
    }
}
