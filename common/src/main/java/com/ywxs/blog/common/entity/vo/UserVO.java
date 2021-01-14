package com.ywxs.blog.common.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserVO对象", description="")
public class UserVO {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty(value = "职位")
    private String position;

    @ApiModelProperty("密码")
    private String password;
}
