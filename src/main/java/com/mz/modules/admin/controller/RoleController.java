package com.mz.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.mz.common.dto.JsonResult;
import com.mz.modules.admin.dao.entity.TSysRole;
import com.mz.modules.admin.dao.entity.TSysUser;
import com.mz.modules.admin.service.TSysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "角色请求接口类", tags = "角色", description = "角色")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")}
)
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {
    @Autowired
    TSysRoleService roleService;

    @RequiresPermissions("role:list")
    @PostMapping("list")
    public JsonResult list(@RequestBody TSysRole role){
        return toJson(roleService.selectList(role));
    }

    @RequiresPermissions("role:add")
    @PostMapping("add")
    public JsonResult addRole(@RequestBody TSysRole role){
        return toJson(roleService.insert(role));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("perm/update")
    public JsonResult editMenu(@RequestBody JSONObject json){
        TSysUser user = (TSysUser) SecurityUtils.getSubject().getPrincipal();
        return toJson(roleService.editMenu(user, json));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("dept/update")
    public JsonResult editDept(@RequestBody JSONObject json){
        TSysUser user = (TSysUser) SecurityUtils.getSubject().getPrincipal();
        return toJson(roleService.editDept(user, json));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("update")
    public JsonResult updateRole(@RequestBody TSysRole role){
        return toJson(roleService.update(role));
    }

    @RequiresPermissions("role:remove")
    @PostMapping("delete")
    public JsonResult deleteRole(@RequestBody TSysRole role){
        return toJson(roleService.deleteById(role));
    }
}
