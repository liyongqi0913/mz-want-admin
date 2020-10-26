package com.mz.modules.wechat.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mz.modules.wechat.dao.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author uncle-luo
 * @since 2020-10-26
 */
public interface IUserService  {

    /**
     * 微信注册接口
     * @param user
     * @return
     */
    public void Register(User user);



    /** 
     * 更新微信用户数据
     * @param user
     * @return
     */
    public void updateUser(User user);


    /**
     *微信用户数据查询
     * @param openid
     * @return
     */
    public User findByUserInfo(String openid);



}
