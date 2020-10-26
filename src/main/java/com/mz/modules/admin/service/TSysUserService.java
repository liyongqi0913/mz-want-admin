package com.mz.modules.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.mz.modules.admin.dao.entity.TSysUser;

import java.util.List;

public interface TSysUserService {
    public TSysUser selectById(Integer id);
    public List<TSysUser> selectList(TSysUser tSysUser);
    public TSysUser selectOneByProperty(String key, Object value);
    public List<TSysUser> selectListByProperty(String key, Object value);
    public int insert(TSysUser tSysUser);
    public int deleteById(TSysUser tSysUser);
    public int update(TSysUser tSysUser);

    boolean isUserHasPermForRole(TSysUser user, String roleId);

    /**
     * 获取单位和用户树
     * @param areaNumber 行政区划代码
     * @return 单位和用户树
     */
    List<JSONObject> getDeptAndUserTree(String areaNumber);
}
