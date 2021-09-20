package com.jiang.back.entity;


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
public class RolePermission{

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    private Integer permissionId;


}
