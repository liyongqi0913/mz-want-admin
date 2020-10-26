package com.mz.modules.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysRolePerm;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TSysRolePermMapper  extends BaseMapper<TSysRolePerm> {
    public TSysRolePerm selectById(Integer id);
    public List<TSysRolePerm> selectList(TSysRolePerm tSysRolePerm);
    public TSysRolePerm selectOneByProperty(String key, Object value);
    public List<TSysRolePerm> selectListByProperty(String key, Object value);
    public int insert(TSysRolePerm tSysRolePerm);
    public int deleteById(TSysRolePerm tSysRolePerm);
    public int update(TSysRolePerm tSysRolePerm);

    public int delete(TSysRolePerm rolePermCond);
    public TSysRolePerm selectRolePermWithResourceAndRole(TSysRolePerm rolePermCond);
}
