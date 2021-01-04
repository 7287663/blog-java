package com.ywxs.blog.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Blog对象", description="")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "作者id")
    @TableField("authorId")
    private String authorId;

    @ApiModelProperty(value = "作者名称")
    @TableField("authorName")
    private String authorName;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "阅读数量")
    @TableField("readNum")
    private Integer readNum;

    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "是否置顶 0否1是")
    @TableField("isTop")
    private Integer isTop;

    @ApiModelProperty(value = "是否上架 0否1是")
    @TableField("isPutWay")
    private Integer isPutWay;

    @ApiModelProperty(value = "修改时间")
    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "标签Id")
    @TableField("tagId")
    private Integer tagId;

    @ApiModelProperty(value = "标签名称")
    @TableField("tagName")
    private String tagName;

    @ApiModelProperty(value = "点赞数")
    @TableField("likeNum")
    private Integer likeNum;


    @ApiModelProperty(value = "评论数")
    @TableField("commentNum")
    private Integer commentNum;

    @ApiModelProperty(value = "封面图片")
    @TableField("coverUrl")
    private String coverUrl;

}
