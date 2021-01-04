package com.ywxs.blog.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ywxs.blog.common.entity.Admin;
import com.ywxs.blog.common.entity.Blog;
import com.ywxs.blog.common.entity.Tag;
import com.ywxs.blog.common.entity.vo.BlogStatisticsVO;
import com.ywxs.blog.common.entity.vo.BlogVO;
import com.ywxs.blog.common.util.Result;
import com.ywxs.blog.service.IAdminService;
import com.ywxs.blog.service.IBlogService;
import com.ywxs.blog.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@Api(tags = "user接口")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final ITagService tagService;

    private final IBlogService blogService;

    private final IAdminService adminService;

    @ApiOperation(value = "获取标签列表", notes = " ")
    @GetMapping("tag/list")
    public Result tagList() {
        List<Tag> res = tagService.getList();
        return Result.ok(res);
    }

    @ApiOperation(value = "查询博客列表", notes = " ")
    @PostMapping("blog/list/{page}/{size}")
    public Result getBlogList(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody BlogVO vo) {
        IPage<Blog> res = blogService.getBlogList(page, size, vo);
        return Result.ok(res);
    }


    /**
     * 增加访问量
     *
     * @param id 1浏览 2点赞 3评论
     */
    @ApiOperation(value = "增加访问量", notes = " ")
    @PutMapping("blog/like/add/{id}/{type}")
    public Result addPageView(@PathVariable("id") Integer id, @PathVariable("type") Integer type) {
        blogService.addPageView(id, type);
        return Result.ok();
    }

    /**
     * 减少点赞数和评论数
     *
     * @param id 2点赞 3评论
     */
    @ApiOperation(value = "查询博客列表", notes = " ")
    @PutMapping("blog/like/sub/{id}/{type}")
    public Result subPageViewAndCommentNum(@PathVariable("id") Integer id, @PathVariable("type") Integer type) {
        blogService.subPageViewAndCommentNum(id, type);
        return Result.ok();
    }

    /**
     * 根据id获取博客详情
     */
    @ApiOperation(value = "查询博客列表", notes = " ")
    @PostMapping("blog/{id}")
    public Result getBlogById(@PathVariable("id") Integer id){
        Blog res = blogService.getById(id);
        return Result.ok(res);
    }

    /**
     * 根据id获取点赞详情
     */
    @ApiOperation(value = "查询博客列表", notes = " ")
    @GetMapping("blog/like/{id}")
    public Result getBlogStatisticsById(@PathVariable("id") Integer id){
        BlogStatisticsVO res = blogService.getBlogStatisticsById(id);
        return  Result.ok(res);
    }

    /**
     * 获取图片信息
     */
    @ApiOperation(value = "获取图片信息", notes = " ")
    @GetMapping("img")
    public Result code() {
        Admin code = adminService.code();
        return Result.ok(code);
    }


    @ApiOperation(value = "获取图片信息", notes = " ")
    @PutMapping("tag/{id}")
    public Result addTagClickNum(@PathVariable("id") Integer id){
        tagService.addTagClickNum(id);
        return Result.ok();
    }

    @ApiOperation(value = "获取用户信息", notes = " ")
    @GetMapping("info/{id}")
    public Result getInfoById(@PathVariable("id") Integer id){
        Admin res = adminService.getInfo(id);
        return Result.ok(res);
    }


}
