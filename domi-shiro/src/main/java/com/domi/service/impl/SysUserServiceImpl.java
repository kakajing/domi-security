package com.domi.service.impl;

import com.domi.dao.SysUserDao;
import com.domi.entity.SysUserEntity;
import com.domi.service.SysRoleService;
import com.domi.service.SysUserRoleService;
import com.domi.service.SysUserService;
import com.domi.utils.Constant;
import com.domi.utils.RRException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());

        //sha256加密
        user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        sysUserDao.save(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());


    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {

        if (StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserDao.update(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userIds) {
        sysUserDao.deleteBatch(userIds);

    }

    @Override
    @Transactional
    public int updatePassword(Long userId, String password, String newPassword) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);

    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user){
        if (user.getCreateUserId() == Constant.SUPER_ADMIN){
            return;
        }

        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        if (!roleIdList.containsAll(user.getRoleIdList())){
            throw new RRException("新增用户所选角色，不是本人创建");
        }

    }
}
