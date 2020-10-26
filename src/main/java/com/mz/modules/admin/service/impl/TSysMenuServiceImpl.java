package com.mz.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mz.common.annotation.DataScope;
import com.mz.common.utils.TreeUtil;
import com.mz.modules.admin.dao.entity.TSysMenu;
import com.mz.modules.admin.dao.entity.TSysPerm;
import com.mz.modules.admin.dao.mapper.TSysMenuMapper;
import com.mz.modules.admin.dao.mapper.TSysPermMapper;
import com.mz.modules.admin.service.TSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TSysMenuServiceImpl implements TSysMenuService {
    @Autowired
    TSysMenuMapper tSysMenuMapper;
    @Autowired
    TSysPermMapper tSysPermMapper;

    @Override
    public TSysMenu selectById(TSysMenu tSysMenu) {
        return tSysMenuMapper.selectById(tSysMenu);
    }

    @Override
    public List<TSysMenu> selectList(TSysMenu tSysMenu) {
        return tSysMenuMapper.selectList(tSysMenu);
    }

    @Override
    public TSysMenu selectOneByProperty(String key, Object value) {
        return tSysMenuMapper.selectOneByProperty(key,value);
    }

    @Override
    public List<TSysMenu> selectListByProperty(String key, Object value) {
        return tSysMenuMapper.selectListByProperty(key,value);
    }

    @Override
    @Transactional
    public int insert(TSysMenu tSysMenu) {
        tSysMenu.setId(IdWorker.get32UUID());
        tSysMenuMapper.insert(tSysMenu);
        //添加权限
        TSysPerm perm = new TSysPerm();
        perm.setPermName(tSysMenu.getPermName());
        perm.setResourceId(tSysMenu.getId());
        perm.setPermType(TSysPerm.PERM_TYPE.MENU);
        perm.setId(IdWorker.get32UUID());
        return tSysPermMapper.insert(perm);
    }

    @Override
    @DataScope(type = "menu")
    public int deleteById(TSysMenu tSysMenu) {
        return tSysMenuMapper.deleteById(tSysMenu);
    }

    @Override
    @DataScope(type = "menu")
    public int update(TSysMenu tSysMenu) {
        TSysMenu tmpMenu = tSysMenuMapper.selectById(tSysMenu);
        if(tmpMenu == null){
            throw new RuntimeException("菜单不存在~");
        }
        //添加权限
        TSysPerm perm = tSysPermMapper.selectOneByProperty("resource_id",tSysMenu.getId());
        if(perm == null){
            //添加权限
            perm = new TSysPerm();
            perm.setPermName(tSysMenu.getPermName());
            perm.setResourceId(tSysMenu.getId());
            perm.setPermType(TSysPerm.PERM_TYPE.MENU);
            tSysPermMapper.insert(perm);
        }else if(!tSysMenu.getPermName().equals(perm.getPermName())){
            //修改权限标识
            perm.setPermName(tSysMenu.getPermName());
            tSysPermMapper.update(perm);
        }

        return tSysMenuMapper.update(tSysMenu);
    }

    @Override
    @DataScope(type = "menu")
    public int deleteByPid(TSysMenu sysMenu) {
        TSysMenu menu = new TSysMenu();
        menu.setPid(sysMenu.getId());
        List<TSysMenu> list = tSysMenuMapper.selectList(menu);
        for (TSysMenu s : list) {
            tSysMenuMapper.deleteByPid(s);
        }
        return tSysMenuMapper.deleteByPid(sysMenu);
    }

    @Override
    @DataScope(type = "menu")
    public List<JSONObject> getUserMenu(TSysMenu tSysMenu) {
        List<TSysMenu> menuList = tSysMenuMapper.getUserMenu(tSysMenu);
        return TreeUtil.toTreeList(menuList);
    }

    @Override
    @DataScope(type = "menu")
    public List<JSONObject> getUserMenuWithRoleStatus(TSysMenu tSysMenu) {
        List<TSysMenu> menuList = tSysMenuMapper.getUserMenuWithRoleStatus(tSysMenu);
        return TreeUtil.toTreeList(menuList);
    }
}
