package com.ywxs.blog.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ywxs.blog.common.entity.Admin;
import com.ywxs.blog.common.entity.Blog;
import com.ywxs.blog.common.entity.Comment;
import com.ywxs.blog.common.entity.Tag;
import com.ywxs.blog.common.entity.vo.AdminVO;
import com.ywxs.blog.common.entity.vo.BlogVO;
import com.ywxs.blog.common.util.Result;
import com.ywxs.blog.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
@Api(tags = "admin接口")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final IAdminService adminService;
    private final IBlogService blogService;
    private final ICommentService commentService;
    private final ITagService tagService;
    private final BaseService baseService;

    /**
     * @param vo 登录
     */
    @ApiOperation(value = "登录", notes = "传参：username,password")
    @PostMapping("login")
    public Result login(@RequestBody AdminVO vo) {
        Map<String, Object> login = adminService.login(vo);
        return Result.ok(login);
    }

    /**
     * 获取个人信息
     *
     */
    @ApiOperation(value = "获取个人信息", notes = " ")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result getInfo() {
        Admin info = adminService.getInfo();
        return Result.ok(info);
    }

    /**
     * 修改个人信息
     */
    @ApiOperation(value = "修改个人信息", notes = " ")
    @PutMapping("/info")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updateAdmin(@RequestBody Admin admin) {
        adminService.updateAdmin(admin);
        return Result.ok();
    }

    /**
     * 修改密码
     */
    @ApiOperation(value = "修改个人信息", notes = " ")
    @PutMapping("/pwd")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updatePwd(@RequestBody Admin admin) {
        adminService.updatePwd(admin);
        return Result.ok();
    }
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
    @ApiOperation(value = "查询博客列表", notes = " ")
    @PostMapping("blog/list/{page}/{size}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result getBlogList(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody BlogVO vo) {
        IPage<Blog> res = blogService.getBlogList(page, size, vo);
        return Result.ok(res);
    }

    /**
     * 添加博客
     *
     */
    @ApiOperation(value = "添加博客", notes = " ")
    @PostMapping("blog")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addBlog(@RequestBody Blog blog) {
        blogService.addBlog(blog);
        return Result.ok();
    }

    @ApiOperation(value = "删除博客", notes = " ")
    @DeleteMapping("blog/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteBlog(@PathVariable("id") Integer id) {
        blogService.removeById(id);
        return Result.ok();
    }

    /**
     * 修改博客
     *
     */
    @ApiOperation(value = "修改博客", notes = " ")
    @PutMapping("blog")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updateBlog(@RequestBody Blog blog) {
        blogService.updateBlog(blog);
        return Result.ok();
    }

    /**
     * 上架博客
     */
    @ApiOperation(value = "上架博客", notes = " ")
    @PutMapping("blog/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result putWay(@PathVariable("id") Integer id) {
        blogService.putWay(id);
        return Result.ok();
    }

    /**
     * 获取博客详情
     */
    @ApiOperation(value = "获取博客详情", notes = " ")
    @GetMapping("blog/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result getBlogById(@PathVariable("id") Integer id) {
        Blog res = blogService.getById(id);
        return Result.ok(res);
    }

    /**
     * 增加访问量
     *
     * @param id 1浏览 2点赞 3评论
     */
    @ApiOperation(value = "增加访问量", notes = "1浏览 2点赞 3评论")
    @PutMapping("blog/{id}/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addPageView(@PathVariable("id") Integer id, @PathVariable("type") Integer type) {
        blogService.addPageView(id, type);
        return Result.ok();
    }

    /**
     * 减少点赞数和评论数
     *
     * @param id 2点赞 3评论
     */
    @ApiOperation(value = "减少点赞数和评论数", notes = " 2点赞 3评论")
    @PutMapping("comment/{id}/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result subPageViewAndCommentNum(@PathVariable("id") Integer id, @PathVariable("type") Integer type) {
        commentService.addOrSubLikeNum(id, type);
        return Result.ok();
    }

    /**
     * 置顶或取消置顶
     */
    @ApiOperation(value = "置顶或取消置顶", notes = " ")
    @PutMapping("blog/top/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result top(@PathVariable("id") Integer id) {
        blogService.top(id);
        return Result.ok();
    }

    /**
     * 查询评论列表
     */
    @ApiOperation(value = "查询评论列表", notes = " ")
    @PostMapping("comment/{page}/{size}/{blogId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result getCommentList(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("blogId") int blogId) {
        IPage<Comment> res = commentService.getCommentList(page, size, blogId);
        return Result.ok(res);
    }

    /**
     * 添加评论
     *
     */
    @ApiOperation(value = "添加评论", notes = " ")
    @PostMapping("comment")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return Result.ok();
    }

    /**
     * 删除评论
     */
    @ApiOperation(value = "删除评论", notes = " ")
    @DeleteMapping("comment/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteCommentById(@PathVariable("id") Integer id) {
        commentService.deleteCommentById(id);
        return Result.ok();
    }

    /**
     * 点赞或取消点赞 1点赞 2取消点赞
     */
    @ApiOperation(value = "点赞或取消点赞 1点赞 2取消点赞", notes = " ")
    @DeleteMapping("comment/{id}/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addOrSubLikeNum(@PathVariable("id") Integer id, @PathVariable("type") Integer type) {
        commentService.addOrSubLikeNum(id, type);
        return Result.ok();
    }

    /**
     * 获取标签列表
     */
    @ApiOperation(value = "获取标签列表", notes = " ")
    @PostMapping("tag/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result getList() {
        List<Tag> list = tagService.getList();
        return Result.ok(list);
    }

    /**
     * 获取标签
     */
    @ApiOperation(value = "获取标签列表", notes = " ")
    @GetMapping("tag/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result getById(@PathVariable("id") Integer id) {
        Tag res = tagService.getById(id);
        return Result.ok(res);
    }

    /**
     * 添加标签
     */
    @ApiOperation(value = "添加标签", notes = " ")
    @PostMapping("tag")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addTag(@RequestBody Tag tag) {
        tagService.addTag(tag);
        return Result.ok();
    }

    /**
     * 删除标签
     */
    @ApiOperation(value = "删除标签", notes = " ")
    @DeleteMapping("tag/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteTagById(@PathVariable("id") Integer id) {
        tagService.deleteTagById(id);
        return Result.ok();
    }

    /**
     * 修改标签
     */
    @ApiOperation(value = "修改标签", notes = " ")
    @PutMapping("tag")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updateTag(@RequestBody Tag tag) {
        tagService.updateTag(tag);
        return Result.ok();
    }

    @ApiOperation(value = "上传图片", notes = " ")
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        return Result.ok(baseService.upload(file));
    }

    @ApiOperation(value = "删除图片", notes = " ")
    @DeleteMapping("/upload/{url}")
    public Result deleteImg(@PathVariable  String url){
        System.out.println(url);
        baseService.deleteImg(url);
        return Result.ok();
    }

}
