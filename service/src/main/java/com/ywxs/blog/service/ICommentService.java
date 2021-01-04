package com.ywxs.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ywxs.blog.common.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @since 2020-12-17
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 查询评论列表
     * @param blogId
     */
    IPage<Comment> getCommentList(int page, int size,int blogId);

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 删除评论
     */
    void deleteCommentById(Integer id);

    /**
     * 点赞或取消点赞
     */
    void addOrSubLikeNum(Integer id,Integer type);

}
