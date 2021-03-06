package com.domi.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
@Data
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = -167607904051963460L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    @NotBlank(message="用户名不能为空"/*, groups = {AddGroup.class, UpdateGroup.class}*/)
    private String username;
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空"/*, groups = AddGroup.class*/)
    private transient String password;
    /**
     * 邮箱
     */
    @NotBlank(message="邮箱不能为空"/*, groups = {AddGroup.class, UpdateGroup.class}*/)
    @Email(message="邮箱格式不正确"/*, groups = {AddGroup.class, UpdateGroup.class}*/)
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;
    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;
    /**
     * 创建者ID
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
}
