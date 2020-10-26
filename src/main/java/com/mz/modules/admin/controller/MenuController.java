package com.mz.modules.admin.controller;

import com.mz.common.dto.JsonResult;
import com.mz.modules.admin.dao.entity.TSysMenu;
import com.mz.modules.admin.service.TSysMenuService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/menu")
@Api(value = "菜单请求接口类", tags = "菜单", description = "菜单")
public class MenuController extends BaseController{
    @Autowired
    TSysMenuService menuService;

    @RequiresPermissions("menu:list")
    @PostMapping("list")
    public JsonResult list(@RequestBody TSysMenu menu){
        return toJson(menuService.getUserMenu(menu));
    }

    @RequiresPermissions("menu:list")
    @PostMapping("listRole")
    public JsonResult listWithRoleStatus(@RequestBody TSysMenu menu){
        return toJson(menuService.getUserMenuWithRoleStatus(menu));
    }

    @RequiresPermissions("menu:add")
    @PostMapping("add")
    public JsonResult add(@RequestBody TSysMenu menu){
        return toJson(menuService.insert(menu));
    }

    @RequiresPermissions("menu:edit")
    @PostMapping("update")
    public JsonResult update(@RequestBody TSysMenu menu){
        return toJson(menuService.update(menu));
    }

    @RequiresPermissions("menu:remove")
    @PostMapping("delete")
    public JsonResult delete(@RequestBody TSysMenu menu){
        menuService.deleteByPid(menu);
        return toJson(menuService.deleteById(menu));
    }
}
