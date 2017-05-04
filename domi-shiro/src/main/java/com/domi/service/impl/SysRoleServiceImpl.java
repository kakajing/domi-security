package com.domi.service.impl;

import com.domi.dao.SysRoleDao;
import com.domi.entity.SysRoleEntity;
import com.domi.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public SysRoleEntity queryObject(Long roleId) {
        return sysRoleDao.queryObject(roleId);
    }

    @Override
    public List<SysRoleEntity> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysRoleDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysRoleEntity role) {

    }

    @Override
    public void update(SysRoleEntity role) {

    }

    @Override
    public void deleteBatch(Long[] roleIds) {

    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return null;
    }
}
