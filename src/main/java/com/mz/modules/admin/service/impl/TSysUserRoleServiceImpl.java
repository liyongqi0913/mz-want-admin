package com.mz.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mz.modules.admin.dao.entity.TSysUserRole;
import com.mz.modules.admin.dao.mapper.TSysUserRoleMapper;
import com.mz.modules.admin.service.TSysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TSysUserRoleServiceImpl implements TSysUserRoleService {
    @Autowired
    TSysUserRoleMapper tSysUserRoleMapper;
    @Override
    public TSysUserRole selectById(String id) {
        return tSysUserRoleMapper.selectById(id);
    }

    @Override
    public List<TSysUserRole> selectList(TSysUserRole tSysUserRole) {
        return tSysUserRoleMapper.selectList(tSysUserRole);
    }

    @Override
    public TSysUserRole selectOneByProperty(String key, Object value) {
        return tSysUserRoleMapper.selectOneByProperty(key,value);
    }

    @Override
    public List<TSysUserRole> selectListByProperty(String key, Object value) {
        return tSysUserRoleMapper.selectListByProperty(key,value);
    }

    @Override
    public int insert(TSysUserRole tSysUserRole) {
        tSysUserRole.setId(IdWorker.get32UUID());
        return tSysUserRoleMapper.insert(tSysUserRole);
    }

    @Override
    public int deleteById(TSysUserRole tSysUserRole) {
        return tSysUserRoleMapper.deleteById(tSysUserRole);
    }

    @Override
    public int delete(TSysUserRole tSysUserRole) {
        QueryWrapper queryWrapper = new QueryWrapper<TSysUserRole>();
        queryWrapper.eq("user_id",tSysUserRole.getUserId());
        return tSysUserRoleMapper.delete(queryWrapper);
    }

    @Override
    public int update(TSysUserRole tSysUserRole) {
        return tSysUserRoleMapper.update(tSysUserRole);
    }

}
