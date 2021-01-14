package com.ywxs.blog.common.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @since 2021-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Collect对象", description="")
public class CollectVO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "博客id")
    @TableField("blogId")
    private Integer blogId;

    @ApiModelProperty(value = "类型：1点赞 2收藏")
    private Integer type;

    @ApiModelProperty(value = "类型：1确定 2取消")
    private Integer status;

}
