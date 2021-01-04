package com.ywxs.blog.common.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "BlogVO对象", description = "")
public class BlogStatisticsVO {
    private Integer likeNum;
    private Integer readNum;
    private String authorName;
}
