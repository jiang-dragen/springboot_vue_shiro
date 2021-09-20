package com.jiang.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Permission{

    private static final long serialVersionUID = 1L;

    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    private String permissionCode;

    private String permissionName;

    private Integer fatherId;

    private String path;

    private Integer isMenu;


}
