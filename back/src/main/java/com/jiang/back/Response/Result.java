package com.jiang.back.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/*
@project name back
@author jiang
@time   2021-09-16-14:55

*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("统一响应对象")
public class Result<T>{
    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("信息")
    private String message;

    @ApiModelProperty("数据")
    private T data;
}

