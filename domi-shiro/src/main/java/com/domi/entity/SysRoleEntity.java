package com.domi.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
@Data
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = 4358013151231997214L;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    @NotBlank(message="角色名称不能为空")
    private String roleName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者ID
     */
    private Long createUserId;
    private List<Long> menuIdList;
    /**
     * 创建时间
     */
    private Date createTime;
}
