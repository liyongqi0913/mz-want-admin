package com.mz.modules.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@TableName("t_sys_user")
@ApiModel(value = "用户表", description = "用户表")
@Data
public class TSysUser extends AdminEntity {

    private String id;
    private String userName;
    private String password;
    private String nick;
    private String salt;
    private Integer state;
    private String avatar;
    private String deptId;
    @TableField(exist = false)
    private List<String> roleIds;
    @TableField(exist = false)
    private TSysDept dept;
    @TableField( fill = FieldFill.INSERT)
    protected Date createdTime;	// 创建日期
    @TableField( fill = FieldFill.INSERT_UPDATE)
    protected Date updatedTime;	// 更新日期

    @TableField(exist = false)
    private List<TSysRole> roleList;
    @TableField(exist = false)
    private List<TSysUserRole> userRoleList;
    @TableField(exist = false)
    private String captcha;
    @TableField(exist = false)
    private String key;

    private String idCard; //身份证


    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 办公电话
     */
    private String phone;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 绑定的微信号
     */
    private String wxOpenid;

    /**
     * 绑定的手机串号
     */
    private String mobileImei;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户类型引用编号
     */
    private String refCode;

    /**
     * 用户类型引用姓名
     */
    private String refName;

    /**
     * 管理员类型（0非管理员 1系统管理员  2二级管理员）
     */
    private String mgrType;

    /**
     * 密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
     */
    private BigDecimal pwdSecurityLevel;

    /**
     * 密码最后更新时间
     */
    private LocalDateTime pwdUpdateDate;

    /**
     * 密码修改记录
     */
    private String pwdUpdateRecord;

    /**
     * 密保问题
     */
    private String pwdQuestion;

    /**
     * 密保问题答案
     */
    private String pwdQuestionAnswer;

    /**
     * 密保问题2
     */
    @TableField("pwd_question_2")
    private String pwdQuestion2;

    /**
     * 密保问题答案2
     */
    @TableField("pwd_question_answer_2")
    private String pwdQuestionAnswer2;

    /**
     * 密保问题3
     */
    @TableField("pwd_question_3")
    private String pwdQuestion3;

    /**
     * 密保问题答案3
     */
    @TableField("pwd_question_answer_3")
    private String pwdQuestionAnswer3;

    /**
     * 密码问题修改时间
     */
    private LocalDateTime pwdQuestUpdateDate;

    /**
     * 最后登陆IP
     */
    private String lastLoginIp;

    /**
     * 最后登陆时间
     */
    private LocalDateTime lastLoginDate;

    /**
     * 冻结时间
     */
    private LocalDateTime freezeDate;

    /**
     * 冻结原因
     */
    private String freezeCause;

    /**
     * 用户权重（降序）
     */
    private BigDecimal userWeight;

    /**
     * 状态（0正常 1删除 2停用 3冻结）
     */
    private String status;

    /**
     * 租户代码
     */
    private String corpCode;

    /**
     * 租户名称
     */
    private String corpName;

    /**
     * 扩展 String 1
     */
    private String extendS1;

    /**
     * 扩展 String 2
     */
    private String extendS2;

    /**
     * 扩展 String 3
     */
    private String extendS3;

    /**
     * 扩展 String 4
     */
    private String extendS4;

    /**
     * 扩展 String 5
     */
    private String extendS5;

    /**
     * 扩展 String 6
     */
    private String extendS6;

    /**
     * 扩展 String 7
     */
    private String extendS7;

    /**
     * 扩展 String 8
     */
    private String extendS8;

    /**
     * 扩展 Integer 1
     */
    private BigDecimal extendI1;

    /**
     * 扩展 Integer 2
     */
    private BigDecimal extendI2;

    /**
     * 扩展 Integer 3
     */
    private BigDecimal extendI3;

    /**
     * 扩展 Integer 4
     */
    private BigDecimal extendI4;

    /**
     * 扩展 Float 1
     */
    private BigDecimal extendF1;

    /**
     * 扩展 Float 2
     */
    private BigDecimal extendF2;

    /**
     * 扩展 Float 3
     */
    private BigDecimal extendF3;

    /**
     * 扩展 Float 4
     */
    private BigDecimal extendF4;

    /**
     * 扩展 Date 1
     */
    private LocalDateTime extendD1;

    /**
     * 扩展 Date 2
     */
    private LocalDateTime extendD2;

    /**
     * 扩展 Date 3
     */
    private LocalDateTime extendD3;

    /**
     * 扩展 Date 4
     */
    private LocalDateTime extendD4;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 备注信息
     */
    private String remarks;


    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", nick=").append(nick);
        sb.append(", salt=").append(salt);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", state=").append(state);
        sb.append(", avatar=").append(avatar);
        sb.append(", deptId=").append(deptId);
        sb.append("]");
        return sb.toString();
    }

    public boolean isAdmin() {
        return id != null && id.equals("1");
    }

    public interface STATE {
        int NORMAL = 0;
        int INVALID = 1;
    }

    protected Serializable pkVal() {
        return this.id;
    }
}
