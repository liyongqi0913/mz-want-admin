package com.mz.modules.admin.util;

import com.mz.modules.admin.dao.entity.TSysPerm;
import com.mz.modules.admin.dao.entity.TSysRole;
import com.mz.modules.admin.dao.entity.TSysUser;

public class PermissionUtil {
    public static boolean hasPermission(TSysUser user, String permId){
        if(user.isAdmin()){
            return true;
        }
        for(TSysRole role : user.getRoleList()){
            for(TSysPerm perm : role.getPermList()){
                if(permId.equals(perm.getId())){
                    return true;
                }
            }
        }
        return false;
    }
}
