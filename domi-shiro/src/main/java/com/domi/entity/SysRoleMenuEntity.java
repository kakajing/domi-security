package com.domi.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
@Data
public class SysRoleMenuEntity implements Serializable {
    private static final long serialVersionUID = -492977002385838369L;
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;
}
