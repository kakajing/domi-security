package com.domi.dao;

import com.domi.entity.SysRoleEntity;

import java.util.List;

/**
 * 角色管理
 *
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
