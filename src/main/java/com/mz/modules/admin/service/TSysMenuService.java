package com.mz.modules.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.mz.modules.admin.dao.entity.TSysMenu;

import java.util.List;

public interface TSysMenuService {
    public TSysMenu selectById(TSysMenu tSysMenu);
    public List<TSysMenu> selectList(TSysMenu tSysMenu);
    public TSysMenu selectOneByProperty(String key, Object value);
    public List<TSysMenu> selectListByProperty(String key, Object value);
    public int insert(TSysMenu tSysMenu);
    public int deleteById(TSysMenu tSysMenu);
    public int update(TSysMenu tSysMenu);

    int deleteByPid(TSysMenu sysMenu);

    public List<JSONObject> getUserMenu(TSysMenu tSysMenu);

    public List<JSONObject> getUserMenuWithRoleStatus(TSysMenu tSysMenu);
}
