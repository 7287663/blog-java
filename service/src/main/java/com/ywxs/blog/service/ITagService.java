package com.ywxs.blog.service;

import com.ywxs.blog.common.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @since 2020-12-17
 */
public interface ITagService extends IService<Tag> {
    /**
     * 获取标签列表
     */
    List<Tag>  getList();

    /**
     * 添加标签
     */
    void addTag(Tag tag);

    /**
     * 删除标签
     */
    void deleteTagById(Integer id);

    /**
     * 修改标签
     */
    void updateTag(Tag tag);

    void addTagClickNum(Integer tagId);
}
