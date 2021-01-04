package com.ywxs.blog.dao;

import com.ywxs.blog.common.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywxs.blog.common.entity.vo.BlogStatisticsVO;
import com.ywxs.blog.common.entity.vo.BlogVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-12-17
 */
public interface BlogMapper extends BaseMapper<Blog> {
    BlogStatisticsVO getBlogStatistics(Integer adminId);
}
