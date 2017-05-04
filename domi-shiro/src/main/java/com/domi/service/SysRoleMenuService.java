package com.domi.service;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * Author 卡卡
 * Created by jing on 2017/5/3.
 */
public interface SysRoleMenuService {
    /**
     * 根据角色ID和菜单idlist进行保存或修改
     * @param roleId
     * @param menuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
