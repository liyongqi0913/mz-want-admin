package com.mz.modules.admin.dao.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "权限表", description = "权限表")
@Data
public class TSysPerm extends AdminEntity {
    public interface PERM_TYPE{
        String MENU = "MENU";
        String DEPT = "DEPT";
    }
    private String resourceId;
    private String permName;
    private String permType;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", permName=").append(permName);
        sb.append(", permType=").append(permType);
        sb.append("]");
        return sb.toString();
    }
}
