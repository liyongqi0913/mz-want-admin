package com.mz.modules.admin.controller;


import com.alibaba.fastjson.JSONObject;
import com.mz.common.generator.service.GenService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Api(value = "代码生成接口类", tags = "代码生成", description = "代码生成")
public class GenController {
    @Autowired
    GenService genService;

    @PostMapping("/gen")
    public void genTable(@RequestBody JSONObject json, HttpServletResponse response) throws IOException {
        String table = json.getString("table");
        String packageName = json.getString("package");
        byte[] data = genService.generateCode(table,packageName);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
