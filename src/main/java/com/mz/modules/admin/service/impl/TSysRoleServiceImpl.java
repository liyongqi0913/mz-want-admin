package com.mz.modules.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mz.common.annotation.DataScope;
import com.mz.modules.admin.dao.entity.*;
import com.mz.modules.admin.dao.mapper.TSysPermMapper;
import com.mz.modules.admin.dao.mapper.TSysRoleMapper;
import com.mz.modules.admin.dao.mapper.TSysRolePermMapper;
import com.mz.modules.admin.service.TSysDeptService;
import com.mz.modules.admin.service.TSysUserService;
import com.mz.modules.admin.util.PermissionUtil;
import com.mz.modules.admin.service.TSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TSysRoleServiceImpl implements TSysRoleService {
    @Autowired
    TSysRoleMapper tSysRoleMapper;
    @Autowired
    TSysRolePermMapper tSysRolePermMapper;
    @Autowired
    TSysPermMapper tSysPermMapper;
    @Autowired
    TSysDeptService tSysDeptService;
    @Autowired
    TSysUserService tSysUserService;

    @Override
    public TSysRole selectById(String id) {
        return tSysRoleMapper.selectById(id);
    }

    @Override
    @DataScope
    public List<TSysRole> selectList(TSysRole tSysRole) {
        return tSysRoleMapper.selectList(tSysRole);
    }

    @Override
    public TSysRole selectOneByProperty(String key, Object value) {
        return tSysRoleMapper.selectOneByProperty(key,value);
    }

    @Override
    public List<TSysRole> selectListByProperty(String key, Object value) {
        return tSysRoleMapper.selectListByProperty(key,value);
    }

    @Override
    public int insert(TSysRole tSysRole) {
        tSysRole.setId(IdWorker.get32UUID());
        return tSysRoleMapper.insert(tSysRole);
    }

    @Override
    @DataScope
    public int deleteById(TSysRole tSysRole) {
        return tSysRoleMapper.delete(new QueryWrapper<TSysRole>().eq("id",tSysRole.getId()));
    }

    @Override
    @DataScope
    public int update(TSysRole tSysRole) {
        return tSysRoleMapper.update(tSysRole);
    }

    @Override
    public List<TSysRole> getUserRoleInfo(String userId,String permType){
        return tSysRoleMapper.getUserRoleInfo(userId,permType);
    }

    @Override
    @Transactional
    public boolean editMenu(TSysUser user, JSONObject json) {
        String id = json.getString("id");

        if (!tSysUserService.isUserHasPermForRole(user,id)) {
            throw new RuntimeException("没有权限");
        }

        JSONArray permList = json.getJSONArray("changeList");
        for(int i = 0; i < permList.size(); i++){
            JSONObject perm = permList.getJSONObject(i);
            String permId = perm.getString("permId");
            Boolean isNew = perm.getBoolean("new");
            if(!PermissionUtil.hasPermission(user, permId)){
                throw new RuntimeException("用户无此权限");
            }
            if(isNew){
                TSysRolePerm rolePerm = new TSysRolePerm();
                rolePerm.setId(IdWorker.get32UUID());
                rolePerm.setPermId(permId);
                rolePerm.setRoleId(id);
                tSysRolePermMapper.insert(rolePerm);
            }else{
                TSysRolePerm rolePermCond = new TSysRolePerm();
                rolePermCond.setPermId(permId);
                rolePermCond.setRoleId(id);
                tSysRolePermMapper.delete(rolePermCond);
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean editDept(TSysUser user, JSONObject json) {
        String id = json.getString("id");
        TSysRole role = tSysRoleMapper.selectById(id);
        if(role == null){
            throw new RuntimeException("角色不存在");
        }

        if (!tSysUserService.isUserHasPermForRole(user,id)) {
            throw new RuntimeException("没有权限");
        }

        JSONArray changeList = json.getJSONArray("changeList");
        editDeptInternal(id, changeList);
        return true;
    }

    private void editDeptInternal(String roleId,JSONArray changeList){
        //前台已处理过，因为父部门有权限会继承到子部门，前台只会返回没有被父部门覆盖权限的数据
        for(int i = 0; i < changeList.size(); i++){
            JSONObject change = changeList.getJSONObject(i);
            String deptId = change.getString("id");
            boolean checked = change.getBoolean("check");
            TSysDept deptCond = new TSysDept();
            deptCond.setId(deptId);
            TSysDept dept = tSysDeptService.selectById(deptCond);
            if(dept == null){
                //判断是否有权限
                throw new RuntimeException("查询不到部门，id=" + deptId);
            }

            if(change.containsKey("children")){
                //先处理子部门，父部门会影响孩子部门权限
                editDeptInternal(roleId,change.getJSONArray("children"));
            }

            TSysRolePerm rolePermCond = new TSysRolePerm();
            rolePermCond.setRoleId(roleId);
            rolePermCond.setResourceId(deptId);
            rolePermCond.getParams().put("permType","DEPT");
            TSysRolePerm rolePerm = tSysRolePermMapper.selectRolePermWithResourceAndRole(rolePermCond);

            if(checked){
                String uuidPerm = IdWorker.get32UUID();
                //添加部门权限
                if(rolePerm == null) {
                    // t_sys_perm没数据
                    TSysPerm perm = new TSysPerm();
                    perm.setId(uuidPerm);
                    perm.setResourceId(deptId);
                    perm.setPermType(TSysPerm.PERM_TYPE.DEPT);
                    tSysPermMapper.insert(perm);

                    rolePerm = new TSysRolePerm();
                    rolePerm.setId(IdWorker.get32UUID());
                    rolePerm.setRoleId(roleId);
                    rolePerm.setPermId(perm.getId());
                    tSysRolePermMapper.insert(rolePerm);
                }else if(rolePerm.getId() == null && rolePerm.getPermId() != null){
                    // t_sys_perm有数据，但t_sys_role_perm没数据
                    rolePerm.setRoleId(roleId);
                    rolePerm.setId(uuidPerm);
                    tSysRolePermMapper.insert(rolePerm);
                }
            }else{
                //删除部门权限
                if(rolePerm != null && rolePerm.getId() != null){
                    tSysRolePermMapper.deleteById(rolePerm);
                }
            }
        }
    }


}
