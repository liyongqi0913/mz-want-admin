package com.mz.modules.admin.controller;

import com.mz.common.config.RedisUtils;
import com.mz.common.dto.JsonResult;
import com.mz.modules.admin.dao.entity.SystemInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/info")
@Api(value = "系统信息接口类", tags = "系统信息", description = "系统信息")
public class SystemInfoController extends BaseController {
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("name/update")
    @RequiresPermissions("info:name:edit")
    public JsonResult updateSystemName(@RequestBody SystemInfo systemInfo){
        return redisUtils.set("system_name", systemInfo.getSystemName())?JsonResult.success():JsonResult.error();
    }

    @GetMapping("name/get")
    public JsonResult getSystemName(){
        return toJson(redisUtils.get("system_name"));
    }


}
