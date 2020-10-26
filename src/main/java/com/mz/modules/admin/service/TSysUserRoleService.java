package com.mz.modules.admin.service;

import com.mz.modules.admin.dao.entity.TSysUserRole;

import java.util.List;

public interface TSysUserRoleService {
    public TSysUserRole selectById(String id);
    public List<TSysUserRole> selectList(TSysUserRole tSysUserRole);
    public TSysUserRole selectOneByProperty(String key, Object value);
    public List<TSysUserRole> selectListByProperty(String key, Object value);
    public int insert(TSysUserRole tSysUserRole);
    public int deleteById(TSysUserRole tSysUserRole);
    public int delete(TSysUserRole tSysUserRole);
    public int update(TSysUserRole tSysUserRole);
}
