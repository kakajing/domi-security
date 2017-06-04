package com.domi.service.impl;

import com.domi.dao.SysMenuDao;
import com.domi.entity.SysMenuEntity;
import com.domi.service.SysMenuService;
import com.domi.service.SysUserService;
import com.domi.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author 卡卡
 * Created by jing on 2017/6/4.
 */
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {

        List<SysMenuEntity> menuList = sysMenuDao.queryListParentId(parentId);
        if (menuList == null){
            return menuList;
        }

        /* List<SysMenuEntity> userMenuList = new ArrayList<>();

        for(SysMenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }*/

        return menuList.stream()
                .parallel()
                .filter(sysMenuEntity -> menuIdList.contains(sysMenuEntity.getMenuId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {

        //系统管理员，拥有最高权限
        if (userId == 1){
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);

    }

    @Override
    public SysMenuEntity queryObject(Long menuId) {
        return null;
    }

    @Override
    public List<SysMenuEntity> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(SysMenuEntity menu) {

    }

    @Override
    public void update(SysMenuEntity menu) {

    }

    @Override
    public void deleteBatch(Long[] menuIds) {

    }

    @Override
    public List<SysMenuEntity> queryUserList(Long userId) {
        return null;
    }



    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){

        //查询根菜单列表
        List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;

    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){

        List<SysMenuEntity> subMenuList = new ArrayList<>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == Constant.MenuType.CATALOG.getValue()){ //目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }


}
