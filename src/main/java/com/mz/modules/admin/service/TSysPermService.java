package com.mz.modules.admin.service;

import com.mz.modules.admin.dao.entity.TSysPerm;

import java.util.List;

public interface TSysPermService {
    public TSysPerm selectById(String id);
    public List<TSysPerm> selectList(TSysPerm tSysPerm);
    public TSysPerm selectOneByProperty(String key, Object value);
    public List<TSysPerm> selectListByProperty(String key, Object value);
    public int insert(TSysPerm tSysPerm);
    public int deleteById(TSysPerm tSysPerm);
    public int update(TSysPerm tSysPerm);
}
