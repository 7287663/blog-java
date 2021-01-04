package com.ywxs.blog.dao;

import com.ywxs.blog.common.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @since 2020-12-17
 */
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where username  =#{username}")
    Admin login(String username);

    @Select("select via,code1,code2  from admin where id =1 ")
    Admin getCode();

    @Select("select via,username from admin where id = #{id}")
    Admin getInfo(Integer id);
}
