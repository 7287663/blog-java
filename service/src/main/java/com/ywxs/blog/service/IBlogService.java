package com.ywxs.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ywxs.blog.common.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywxs.blog.common.entity.vo.BlogStatisticsVO;
import com.ywxs.blog.common.entity.vo.BlogVO;

/**
 * @since 2020-12-17
 */
public interface IBlogService extends IService<Blog> {

    //总后台

    /**
     * 查询博客列表
     *
     * @param vo private String title;
     *           private String authorName;
     *           private Integer isTop;
     *           private Integer isPutWay;
     *           private Integer tagId;
     *           private Integer hot;
     */
    IPage<Blog> getBlogList(int page, int size, BlogVO vo);

    /**
     * 添加博客
     *
     */
    void addBlog(Blog blog);

    /**
     * 修改博客
     *
     */
    void updateBlog(Blog blog);

    /**
     * 上架博客
     */
    void putWay(Integer id);

    /**
     * 增加访问量
     *
     * @param id 1浏览 2点赞 3评论
     */
    void addPageView(Integer id, Integer type);

    /**
     * 减少点赞数和评论数
     *
     * @param id 2点赞 3评论
     */
    void subPageViewAndCommentNum(Integer id, Integer type);

    /**
     * 置顶或取消置顶
     */
    void top(Integer id);

    /**
     * 根据userId获取博客被点赞&&阅读数
     *
     * @param userId userId
     */
    BlogStatisticsVO getBlogStatisticsById(Integer userId);
}
