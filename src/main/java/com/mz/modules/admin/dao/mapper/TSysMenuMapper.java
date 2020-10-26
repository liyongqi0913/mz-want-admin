package com.mz.modules.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysMenu;

import java.util.List;

public interface TSysMenuMapper  extends BaseMapper<TSysMenu> {
    public TSysMenu selectById(TSysMenu tSysMenu);
    public List<TSysMenu> selectList(TSysMenu tSysMenu);
    public TSysMenu selectOneByProperty(String key, Object value);
    public List<TSysMenu> selectListByProperty(String key, Object value);
    public int insert(TSysMenu tSysMenu);
    public int deleteById(TSysMenu tSysMenu);
    public int update(TSysMenu tSysMenu);

    int deleteByPid(TSysMenu sysMenu);

    List<TSysMenu> getUserMenu(TSysMenu tSysMenu);
    List<TSysMenu> getUserMenuWithRoleStatus(TSysMenu tSysMenu);
}
