package com.ywxs.blog.service;

import com.ywxs.blog.common.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywxs.blog.common.entity.vo.AdminVO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @since 2020-12-17
 */
public interface IAdminService extends IService<Admin> {
    /**
     * @param vo 登录
     */
    Map<String,Object> login(AdminVO vo);

    /**
     * 获取个人信息
     * @return
     */
     Admin getInfo();

    /**
     * 修改密码
     */
     void updatePwd(Admin admin);

     void updateAdmin(Admin admin);

     //前台获取图片信息

    /**
     * 前台获取图片信息
     */
     Admin code();


    /**
     * 根据id获取头像和用户名
     */
     Admin getInfo(Integer id);
}
