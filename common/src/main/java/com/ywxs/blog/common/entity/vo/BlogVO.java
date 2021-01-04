package com.ywxs.blog.common.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BlogVO对象", description="")
public class BlogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者名称")
    @TableField("authorName")
    private String authorName;

    @ApiModelProperty(value = "是否置顶 0否1是")
    @TableField("isTop")
    private Integer isTop;

    @ApiModelProperty(value = "是否上架 0否1是")
    @TableField("isPutWay")
    private Integer isPutWay;

    @ApiModelProperty(value = "标签名称")
    @TableField("tagName")
    private String tagName;
    @ApiModelProperty(value = "按热门排序还是时间排序 1热门 2时间")
    private Integer hot;
    

}
