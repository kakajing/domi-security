package com.domi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 *
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
@Data
public class SysUserRoleEntity implements Serializable {

    private static final long serialVersionUID = -1016205852225935441L;
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


}
