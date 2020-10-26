package com.mz.modules.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSysRoleMapper  extends BaseMapper<TSysRole> {
    public TSysRole selectById(String id);
    public List<TSysRole> selectList(TSysRole tSysRole);
    public TSysRole selectOneByProperty(String key, Object value);
    public List<TSysRole> selectListByProperty(String key, Object value);
//    public int insert(TSysRole tSysRole);
    public int deleteById(TSysRole tSysRole);
    public int update(TSysRole tSysRole);

    public List<TSysRole> getUserRoleInfo(String userId,String permType);

}
