package com.domi.service;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
public interface SysUserRoleService {

    /**
     * 根据用户ID和角色idlist进行保存或修改
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据用户ID，删除
     */
    void delete(Long userId);
}
