package com.mz.modules.admin.dao.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mz.modules.admin.dao.entity.TSysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSysUserMapper extends BaseMapper<TSysUser> {
    public TSysUser selectById(Integer id);
    public List<TSysUser> selectList(TSysUser tSysUser);
    public List<TSysUser> selectListByDeptIds(TSysUser tSysUser);
    public TSysUser selectOneByProperty(String key, Object value);
    public List<TSysUser> selectListByProperty(String key, Object value);
    public int insert(TSysUser tSysUser);
    public int deleteById(TSysUser tSysUser);
    public int update(TSysUser tSysUser);
    public int checkUserRoleEditPermission(String userId,String roleId);

    List<JSONObject> selectListAndDept();
}
