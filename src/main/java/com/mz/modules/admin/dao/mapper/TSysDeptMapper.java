package com.mz.modules.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysDept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSysDeptMapper  extends BaseMapper<TSysDept> {
    public TSysDept selectById(TSysDept tSysDept);
    public List<TSysDept> selectList(TSysDept tSysDept);
    public TSysDept selectOneByProperty(String key, Object value);
    public List<TSysDept> selectListByProperty(String key, Object value);
    public int insert(TSysDept tSysDept);
    public int deleteById(TSysDept tSysDept);
    public int update(TSysDept tSysDept);

    List<TSysDept> getDeptWithRoleStatus(TSysDept dept);
    List<TSysDept> getChildrenDept(TSysDept dept);

    public int updateDeptListPids(List<TSysDept> childDeptList);
}
