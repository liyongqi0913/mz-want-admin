package com.mz.modules.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mz.common.config.RedisUtils;
import com.mz.common.dto.JsonResult;
import com.mz.common.utils.CaptchaUtil;
import com.mz.common.utils.StringUtils;
import com.mz.modules.admin.dao.entity.*;
import com.mz.modules.admin.service.TSysMenuService;
import com.mz.modules.admin.service.TSysRoleService;
import com.mz.modules.admin.service.TSysUserRoleService;
import com.mz.modules.admin.service.TSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Api(value = "登录请求接口类", tags = "登录", description = "用户请求登录获取Token")
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
@RequestMapping("/user")
public class UserController extends BaseController{
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    TSysUserService userService;
    @Autowired
    TSysMenuService menuService;
    @Autowired
    TSysRoleService roleService;
    @Autowired
    TSysUserRoleService userRoleService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    private DefaultKaptcha captchaProducer;

    @ApiOperation(value = "获取用户基础信息", notes = "接口提供权限列表、菜单列表、角色信息、用户信息", produces = "application/json")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JsonResult getUserInfo(){
        JsonResult ret = JsonResult.success();
        TSysUser user = (TSysUser) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            return JsonResult.error("用户过期，请重新登录");
        }

        TSysMenu menuParam = new TSysMenu();
        menuParam.setUserId(user.getId());
        menuParam.setType("MENU");
        menuParam.setState(0);
        ret.put("menus",menuService.getUserMenu(menuParam));
        JSONObject userJson = (JSONObject) JSONObject.toJSON(user);
        userJson.remove("password");
        userJson.remove("salt");
        ret.put("user",userJson);

        List<TSysRole> roleList = roleService.getUserRoleInfo(user.getId(),TSysPerm.PERM_TYPE.MENU);
        ret.put("roles",roleList);
        return ret;
    }

    @ApiOperation(value = "登录认证接口", notes = "先调用此接口拿到Token，然后再通过Token调用相应接口，最好使用HTTPS协议。", produces = "application/json")
    @PostMapping("/login")
    public JsonResult login(@RequestBody TSysUser user){
        if(StringUtils.isBlank(user.getCaptcha())){
            return JsonResult.error(1001, "验证码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        String sessionId = (String) subject.getSession().getId();
        Object randomStr = redisUtils.get(user.getKey());
        if(randomStr==null){
            return JsonResult.error(1002, "验证码已过期，请重新输入");
        }
        if(!Objects.equals(user.getCaptcha().toUpperCase(), randomStr)){
            return JsonResult.error(1002, "验证码输入错误，请重新输入");
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            subject.login(token);
        }catch (Exception e){
            throw new RuntimeException("账号或密码错误");
        }
        redisUtils.remove(user.getKey());
        JsonResult retJson = JsonResult.success();
        retJson.put("token", sessionId);
        return  retJson;
    }

    @ApiOperation(value = "登录认证接口", notes = "先调用此接口拿到Token，然后再通过Token调用相应接口，最好使用HTTPS协议。", produces = "application/json")
    @PostMapping("/getToken")
    public JsonResult getToken(@RequestBody TSysUser user){
        Subject subject = SecurityUtils.getSubject();
        String sessionId = (String) subject.getSession().getId();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
            subject.login(token);
        }catch (Exception e){
            throw new RuntimeException("账号或密码错误");
        }
        JsonResult retJson = JsonResult.success();
        retJson.put("token", sessionId);
        return  retJson;
    }

    @PostMapping("/add")
    public JsonResult addUser(@RequestBody TSysUser user){
        return toJson(userService.insert(user));
    }

    @PostMapping("update")
    public JsonResult update(@RequestBody TSysUser user){
        return toJson(userService.update(user));
    }


    @PostMapping("/list")
    public JsonResult list(@RequestBody TSysUser user){
        List<TSysUser> users = userService.selectList(user);
        return  toJson(users);
    }

    @PostMapping("delete")
    public JsonResult delete(@RequestBody TSysUser user){
        //这里的用户权限关联只删除与用户相关的关联
        userRoleService.delete(new TSysUserRole(user.getId(),""));
        return toJson(userService.deleteById(user));
    }


    @GetMapping("/generateCaptcha")
    public JsonResult getKaptcha(@RequestParam String key, HttpServletResponse response) {
        // 需要将生产谜底存入session中
        /*response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String key = request.getParameter("key");

        String text = captchaProducer.createText();

        // 存入session中，60秒后过期

        redisUtils.set(key, text, 60);//保存60秒

        try {
            ServletOutputStream out = response.getOutputStream();
            BufferedImage image = captchaProducer.createImage(text);
            ImageIO.write(image, "jpg", out);
            out.flush();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.error("验证码生成出错");
        }*/
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        try {
            String text = CaptchaUtil.outputCaptcha(response, 5);
            redisUtils.set(key, text, 60);//保存60秒
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.error("验证码生成出错");
        }
    }

    @GetMapping(value = {"/getUserAndDeptTree"})
    public JsonResult getUserAndDeptTree() {
        return toJson(userService.getDeptAndUserTree(""));
    }
}
