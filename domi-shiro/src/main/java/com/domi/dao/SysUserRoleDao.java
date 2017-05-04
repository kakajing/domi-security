package com.domi.dao;

import com.domi.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdLIst(Long userId);

}
