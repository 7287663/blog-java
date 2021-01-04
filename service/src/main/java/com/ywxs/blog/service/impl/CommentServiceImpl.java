package com.ywxs.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ywxs.blog.common.entity.Blog;
import com.ywxs.blog.common.entity.Comment;
import com.ywxs.blog.common.util.Validate;
import com.ywxs.blog.dao.CommentMapper;
import com.ywxs.blog.service.IBlogService;
import com.ywxs.blog.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 * @since 2020-12-17
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    private final CommentMapper commentMapper;
    private final IBlogService blogService;

    @Override
    public IPage<Comment> getCommentList(int page, int size, int blogId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getBlogId, blogId);
        lqw.orderByDesc(Comment::getCreateTime);
        return commentMapper.selectPage(new Page<>(page, size), lqw);
    }

    @Override
    @Transactional
    public void addComment(Comment comment) {
        String email = comment.getEmail();
        String name = comment.getName();
        Integer blogId = comment.getBlogId();
        Assert.notNull(blogId, "博客id为空！");
        Assert.notNull(name, "名称为空！");
        Assert.isTrue(Validate.checkEmail(email), "邮箱格式不正确！");
        comment.setLikeNum(0);
        comment.setCreateTime(LocalDateTime.now());
        Blog byId1 = blogService.getById(comment.getBlogId());
        byId1.setCommentNum(byId1.getCommentNum()  +  1);
        save(comment);
        blogService.updateById(byId1);
    }

    @Override
    @Transactional
    public void deleteCommentById(Integer id) {
        Comment comment = getById(id);
        Blog byId1 = blogService.getById(comment.getBlogId());
       if( byId1.getCommentNum() > 0) {
           byId1.setCommentNum(byId1.getCommentNum() - 1);
       }
        removeById(id);
        blogService.updateById(byId1);
    }

    @Override
    public void addOrSubLikeNum(Integer id, Integer type) {
        Comment comment = getById(id);
        if (type == 1) {
            comment.setLikeNum(comment.getLikeNum() + 1);
        } else {
            comment.setLikeNum(comment.getLikeNum() - 1);
        }
        updateById(comment);
    }
}
