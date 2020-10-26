package com.mz.modules.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSysUserRoleMapper  extends BaseMapper<TSysUserRole> {
    public TSysUserRole selectById(String id);
    public List<TSysUserRole> selectList(TSysUserRole tSysUserRole);
    public TSysUserRole selectOneByProperty(String key, Object value);
    public List<TSysUserRole> selectListByProperty(String key, Object value);
    public int insert(TSysUserRole tSysUserRole);
    public int deleteById(TSysUserRole tSysUserRole);
    public int update(TSysUserRole tSysUserRole);
}
