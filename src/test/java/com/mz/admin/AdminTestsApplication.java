package com.mz.admin;

import com.mz.common.utils.ShiroKit;
import com.mz.modules.admin.dao.entity.TSysDept;
import com.mz.modules.admin.dao.entity.TSysRole;
import com.mz.modules.admin.dao.entity.TSysUser;
import com.mz.modules.admin.dao.entity.TSysUserRole;
import com.mz.modules.admin.service.TSysRoleService;
import com.mz.modules.admin.service.TSysUserRoleService;
import com.mz.modules.admin.service.TSysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTestsApplication {


    @Autowired
    private TSysUserService userService;
    @Autowired
    private TSysRoleService roleService;
    @Autowired
    private TSysUserRoleService userRoleService;



    @Test
    public void insert() {
        String password = md5("123456", "AXSX");
        System.out.println(password);
        List<TSysRole> roles = new ArrayList<>();
        roles.add(new TSysRole("1"));
        TSysUser user = new TSysUser();
        user.setNick("郭齐勇");
        user.setUserName("qiyongguo");
        user.setPassword(password);
        user.setSalt("AXSX");
        user.setDept(new TSysDept("1"));
        user.setRoleList(roles);
        userService.insert(user);
        for (TSysRole role: roles
             ) {
            userRoleService.insert(new TSysUserRole(user.getId(),role.getId()));
        }
    }


    public static final String md5(String password, String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }


    @Test
    public void generatorSimple(){
        //盐（用户名+随机数）
        String username = "qiyongguo";
        String salt = ShiroKit.getRandomSalt(16);
        //原密码
        String password = "12345678";
        String encodedPassword = ShiroKit.md5(password, username + salt);
        TSysUser user = new TSysUser();
        user.setNick("郭齐勇");
        user.setUserName("qiyongguo");
        user.setPassword(encodedPassword);
        user.setSalt(salt);
        user.setDeptId("1");
        userService.insert(user);
        List<TSysRole> roles = new ArrayList<>();
        roles.add(new TSysRole("1"));
        for (TSysRole role: roles
                ) {
            userRoleService.insert(new TSysUserRole(user.getId(),role.getId()));
        }
    }

    @Test
    public void generatorPassword(){
        //盐（用户名+随机数）
        String username = "admin";
        String salt = ShiroKit.getRandomSalt(16);
        //原密码
        String password = "admin";
        String encodedPassword = ShiroKit.md5(password, username + salt);
        System.out.println("这个是保存进数据库的密码:" + encodedPassword);
        System.out.println("这个是保存进数据库的盐:" + salt);
    }
}
