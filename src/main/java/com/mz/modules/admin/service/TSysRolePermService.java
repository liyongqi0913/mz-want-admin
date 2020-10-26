package com.mz.modules.admin.service;

import com.mz.modules.admin.dao.entity.TSysRolePerm;

import java.util.List;

public interface TSysRolePermService {
    public TSysRolePerm selectById(Integer id);
    public List<TSysRolePerm> selectList(TSysRolePerm tSysRolePerm);
    public TSysRolePerm selectOneByProperty(String key, Object value);
    public List<TSysRolePerm> selectListByProperty(String key, Object value);
    public int insert(TSysRolePerm tSysRolePerm);
    public int deleteById(TSysRolePerm tSysRolePerm);
    public int update(TSysRolePerm tSysRolePerm);
}
