package com.ywxs.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ywxs.blog.common.entity.Blog;
import com.ywxs.blog.common.entity.Tag;
import com.ywxs.blog.dao.BlogMapper;
import com.ywxs.blog.dao.TagMapper;
import com.ywxs.blog.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @since 2020-12-17
 */
@RequiredArgsConstructor
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    private final TagMapper tagMapper;
    private final BlogMapper blogMapper;

    @Override
    public List<Tag> getList() {
        LambdaQueryWrapper<Tag> lqw = new LambdaQueryWrapper<>();
        lqw.orderByAsc(Tag::getTagSort);
        return tagMapper.selectList(lqw);
    }

    @Override
    public void addTag(Tag tag) {
        Integer tagSort = tag.getTagSort();
        if (tagSort == null) {
            tag.setTagSort(0);
        }
        tag.setClickNum(0);
        save(tag);
    }

    @Override
    public void deleteTagById(Integer id) {
        LambdaQueryWrapper<Blog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Blog::getTagId, id);
        Integer count = blogMapper.selectCount(lqw);
        Assert.isTrue(count == 0, "请先删除包含该标签的文章！");
        removeById(id);
    }

    @Override
    public void updateTag(Tag tag) {
        updateById(tag);
    }

    @Override
    public void addTagClickNum(Integer tagId) {
        Tag tag = tagMapper.selectById(tagId);
        tag.setClickNum(tag.getClickNum() + 1);
        tagMapper.updateById(tag);
    }
}
