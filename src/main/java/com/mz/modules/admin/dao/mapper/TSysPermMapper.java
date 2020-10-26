package com.mz.modules.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysPerm;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TSysPermMapper  extends BaseMapper<TSysPerm> {
    public TSysPerm selectById(String id);
    public List<TSysPerm> selectList(TSysPerm tSysPerm);
    public TSysPerm selectOneByProperty(String key, Object value);
    public List<TSysPerm> selectListByProperty(String key, Object value);
    public int insert(TSysPerm tSysPerm);
    public int deleteById(TSysPerm tSysPerm);
    public int update(TSysPerm tSysPerm);
}
