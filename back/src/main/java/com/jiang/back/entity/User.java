package com.jiang.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiang
 * @since 2021-09-15
 */
@Data
@EqualsAndHashCode
@ApiModel("用户")
public class User {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("姓名")
    private String nickname;

    @ApiModelProperty("学院ID")
    private Integer schoolId;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("班级")
    private String studentClass;


}
