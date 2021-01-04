package com.ywxs.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ywxs.blog.common.entity.Blog;
import com.ywxs.blog.common.entity.vo.BlogStatisticsVO;
import com.ywxs.blog.common.entity.vo.BlogVO;
import com.ywxs.blog.common.util.IdUtils;
import com.ywxs.blog.dao.BlogMapper;
import com.ywxs.blog.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @since 2020-12-17
 */
@Service
@RequiredArgsConstructor
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    private final BlogMapper blogMapper;

    @Override
    public IPage<Blog> getBlogList(int page, int size, BlogVO vo) {
        String title = vo.getTitle();
        String authorName = vo.getAuthorName();
        Integer isPutWay = vo.getIsPutWay();
        Integer isTop = vo.getIsTop();
        String tagName = vo.getTagName();
        Integer hot = vo.getHot();
        LambdaQueryWrapper<Blog> lqw = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(title)) {
            lqw.like(Blog::getTitle, title);
        }
        if (StrUtil.isNotBlank(authorName)) {
            lqw.like(Blog::getAuthorName, authorName);
        }
        if (isPutWay != null) {
            lqw.eq(Blog::getIsPutWay, isPutWay);
        }
        if (isTop != null) {
            lqw.eq(Blog::getIsTop, isTop);
        }
        if (StrUtil.isNotBlank(tagName)) {
            lqw.eq(Blog::getTagName, tagName);
        }
        if (hot != null) {
            if (hot == 1) {
                lqw.orderByDesc(Blog::getLikeNum);
            } else {
                lqw.orderByDesc(Blog::getCreateTime);
            }
        } else {
            lqw.orderByDesc(Blog::getCreateTime);
        }
        lqw.orderByDesc(Blog::getIsTop);
        return blogMapper.selectPage(new Page<>(page, size), lqw);
    }

    @Override
    public void addBlog(Blog blog) {
        blog.setAuthorId(IdUtils.getId());
        blog.setAuthorName(IdUtils.getUserName());
        blog.setCreateTime(LocalDateTime.now());
        blog.setReadNum(0);
        blog.setIsTop(0);
        blog.setUpdateTime(LocalDateTime.now());
        blog.setLikeNum(0);
        blog.setCommentNum(0);
        save(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        updateById(blog);
    }

    @Override
    public void putWay(Integer id) {
        Blog blog = getById(id);
        Integer status = blog.getIsPutWay() == 0 ? 1 : 0;
        blog.setIsPutWay(status);
        updateById(blog);
    }

    @Override
    public void addPageView(Integer id, Integer type) {
        Assert.notNull(type, "类型为空");
        Blog blog = getById(id);
        switch (type) {
            case 1:
                blog.setReadNum(blog.getReadNum() + 1);
                break;
            case 2:
                blog.setLikeNum(blog.getLikeNum() + 1);
                break;
            case 3:
                blog.setCommentNum(blog.getCommentNum() + 1);
                break;
            default:
                break;
        }
        updateById(blog);
    }

    @Override
    public void subPageViewAndCommentNum(Integer id, Integer type) {
        Assert.notNull(type, "类型为空");
        Blog blog = getById(id);
        switch (type) {
            case 2:
                blog.setLikeNum(blog.getLikeNum() - 1);
                break;
            case 3:
                blog.setCommentNum(blog.getCommentNum() - 1);
                break;
            default:
                break;
        }
        updateById(blog);
    }


    @Override
    public void top(Integer id) {
        Blog blog = getById(id);
        Integer isTop = blog.getIsTop();
        int status = isTop == 0 ? 1 : 0;
//        if (status == 1) {
//            LambdaQueryWrapper<Blog> lqw = new LambdaQueryWrapper<>();
//            lqw.eq(Blog::getIsTop, status);
//            Integer count = blogMapper.selectCount(lqw);
//            Assert.isTrue(count == 0, "已经有置顶作品若想置顶此作品请先取消注定作品");
//        }
        blog.setIsTop(status);
        updateById(blog);
    }

    @Override
    public BlogStatisticsVO getBlogStatisticsById(Integer id) {
        return blogMapper.getBlogStatistics(id);
    }

}
