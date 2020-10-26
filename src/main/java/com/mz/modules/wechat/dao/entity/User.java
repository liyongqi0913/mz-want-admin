package com.mz.modules.wechat.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author uncle-luo
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wechat_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.UUID)
    private String id;

    /**
     * 用户账号（openid）
     */
    @TableId(value = "open_id",type = IdType.INPUT)
    private String openid;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 性别（0.未知 1.男 2.女）
     */
    private String gender;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 身份证号
     */
    @TableField("id_number")
    private String idNumber;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 国家
     */
    @TableField("country")
    private String country;

    /**
     * 省份
     */
    @TableField("province")
    private String province;

    /**
     * 城市
     */
    @TableField("city")
    private String city;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdTime;

    /**
     * 修改时间
     */
    @TableField("updated_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 料值
     */
    private Integer feed;

    /**
     * 登录时间
     */
    @TableField("login_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime loginTime;

    /**
     * 邀请人openid
     */
    private String inviter;


}
