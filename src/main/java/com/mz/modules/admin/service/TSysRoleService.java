package com.mz.modules.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.mz.modules.admin.dao.entity.TSysRole;
import com.mz.modules.admin.dao.entity.TSysUser;

import java.util.List;

public interface TSysRoleService {
    public TSysRole selectById(String id);
    public List<TSysRole> selectList(TSysRole tSysRole);
    public TSysRole selectOneByProperty(String key, Object value);
    public List<TSysRole> selectListByProperty(String key, Object value);
    public int insert(TSysRole tSysRole);
    public int deleteById(TSysRole tSysRole);
    public int update(TSysRole tSysRole);

    public List<TSysRole> getUserRoleInfo(String userId,String permType);

    boolean editMenu(TSysUser user, JSONObject json);

    boolean editDept(TSysUser user, JSONObject json);
}
