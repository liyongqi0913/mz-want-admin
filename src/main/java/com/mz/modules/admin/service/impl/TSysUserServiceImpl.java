package com.mz.modules.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mz.common.utils.ShiroKit;
import com.mz.common.utils.TreeUtil;
import com.mz.modules.admin.dao.entity.TSysRole;
import com.mz.modules.admin.dao.entity.TSysUser;
import com.mz.modules.admin.dao.entity.TSysUserRole;
import com.mz.modules.admin.dao.mapper.TSysDeptMapper;
import com.mz.modules.admin.dao.mapper.TSysRoleMapper;
import com.mz.modules.admin.dao.mapper.TSysUserMapper;
import com.mz.modules.admin.dao.mapper.TSysUserRoleMapper;
import com.mz.modules.admin.service.TSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TSysUserServiceImpl implements TSysUserService {
    @Autowired
    TSysUserMapper tSysUserMapper;
    @Autowired
    TSysUserRoleMapper tSysUserRoleMapper;
    @Autowired
    TSysRoleMapper tSysRoleMapper;
    @Autowired
    TSysDeptMapper tSysDeptMapper;

    @Override
    public TSysUser selectById(Integer id) {
        return tSysUserMapper.selectById(id);
    }

    @Override
    public List<TSysUser> selectList(TSysUser tSysUser) {
            List<TSysUser> users = tSysUserMapper.selectListByDeptIds(tSysUser);
            users.stream().forEach(user -> {
               List<TSysUserRole> userRoles = tSysUserRoleMapper.selectList(new TSysUserRole(user.getId()));
               user.setUserRoleList(userRoles);
                ArrayList<String> rolesId = new ArrayList<>();
                userRoles.stream().forEach(userRole -> {
                    rolesId.add(userRole.getRoleId());
                });
                user.setRoleIds(rolesId);
            }
        );
        return users;
    }

    @Override
    public TSysUser selectOneByProperty(String key, Object value) {
        TSysUser user = tSysUserMapper.selectOneByProperty(key, value);
        return user;
    }

    @Override
    public List<TSysUser> selectListByProperty(String key, Object value) {
        return tSysUserMapper.selectListByProperty(key, value);
    }

    @Override
    public int insert(TSysUser tSysUser) {
        String salt = ShiroKit.getRandomSalt(16);
        //第一次添加用户设置默认密码和用户名一致
        String password = tSysUser.getUserName();
        String encodedPassword = ShiroKit.md5(password, tSysUser.getUserName() + salt);
        tSysUser.setSalt(salt);
        tSysUser.setPassword(encodedPassword);
        tSysUser.setId(IdWorker.get32UUID());
        //默认用户头像
        tSysUser.setAvatar("https://www.zhsq010.com/images/admin/avatar/user.jpg");
        int result = tSysUserMapper.insert(tSysUser);
        //由于新增用户关联的角色不会太多，这里使用性能普通的方式去插入数据，优化性能可更新mapper循环写入的方式
        for (String rid : tSysUser.getRoleIds()
                ) {
            tSysUserRoleMapper.insert(new TSysUserRole(IdWorker.get32UUID(), tSysUser.getId(), rid));
        }
        return result;
    }

    @Override
    public int deleteById(TSysUser tSysUser) {
        return tSysUserMapper.deleteById(tSysUser);
    }

    @Override
    public int update(TSysUser tSysUser) {
        tSysUserRoleMapper.delete(new QueryWrapper<TSysUserRole>().eq("user_id",tSysUser.getId()));
        for (String id: tSysUser.getRoleIds()) {
            tSysUserRoleMapper.insert(new TSysUserRole(IdWorker.get32UUID(), tSysUser.getId(),id));
        }
        return tSysUserMapper.update(tSysUser);
    }

    @Override
    public boolean isUserHasPermForRole(TSysUser user, String roleId) {
        if (user.isAdmin()) {
            return true;
        }
        for (TSysRole role : user.getRoleList()) {
            if (TSysRole.PERM_TYPE.ALL.equals(role.getDeptPermType())) {
                return true;
            }
        }
        return tSysUserMapper.checkUserRoleEditPermission(user.getId(), roleId) > 0;
    }

    @Override
    public List<JSONObject> getDeptAndUserTree(String areaNumber) {
        List<JSONObject> list = tSysUserMapper.selectListAndDept();
        Set<String> deptIdSet = new HashSet<>();
        List<JSONObject> deptList = new ArrayList<>();
        List<JSONObject> userList = new ArrayList<>();
        for (JSONObject obj : list) {
            if (!deptIdSet.contains(obj.getString("deptId"))) {
                deptIdSet.add(obj.getString("deptId"));
                JSONObject dept = new JSONObject();
                dept.put("id", obj.getString("deptId"));
                dept.put("pid", obj.getString("deptPid"));
                dept.put("name", obj.getString("deptName"));
                deptList.add(dept);
            }
            JSONObject user = new JSONObject();
            user.put("id", obj.getString("userId"));
            user.put("pid", obj.getString("deptId"));
            user.put("name", obj.getString("userNick"));
            user.put("phone", obj.getString("userPhone"));
            user.put("idcard", obj.getString("userIdcard"));
            userList.add(user);
        }
        deptList.addAll(userList);
        System.out.println(JSONObject.toJSONString(deptList));
        return TreeUtil.parseMenuTree(deptList);
    }
}
