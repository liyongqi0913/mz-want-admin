package com.mz.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.mz.common.utils.TreeUtil;
import com.mz.common.dto.JsonResult;
import com.mz.modules.admin.dao.entity.TSysDept;
import com.mz.modules.admin.service.TSysDeptService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sys/dept")
@Api(value = "部门请求接口类", tags = "部门", description = "部门")
public class DeptController extends BaseController{
    @Autowired
    TSysDeptService deptService;

    @RequiresPermissions("dept:list")
    @PostMapping("list")
    public JsonResult list(@RequestBody TSysDept dept){
        return toJson(TreeUtil.toTreeList(deptService.selectList(dept)));
    }

    @RequiresPermissions("dept:list")
    @PostMapping("listRole")
    public JsonResult listWithRoleStatus(@RequestBody TSysDept dept){
        List<JSONObject> deptList = TreeUtil.toTreeList(deptService.getDeptWithRoleStatus(dept));
        TreeUtil.setChildrenRoleWithParent(deptList, null);
        return toJson(deptList);
    }

    @RequiresPermissions("dept:add")
    @PostMapping("add")
    public JsonResult add(@RequestBody TSysDept dept){
        return toJson(deptService.insert(dept));
    }

    @RequiresPermissions("dept:edit")
    @PostMapping("update")
    public JsonResult update(@RequestBody TSysDept dept){
        return toJson(deptService.update(dept));
    }

    @RequiresPermissions("dept:remove")
    @PostMapping("delete")
    public JsonResult delete(@RequestBody TSysDept dept){
        return toJson(deptService.deleteById(dept));
    }
}
