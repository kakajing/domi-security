package com.domi.shiro;

import com.domi.entity.SysMenuEntity;
import com.domi.entity.SysUserEntity;
import com.domi.service.SysMenuService;
import com.domi.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限认证
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUserEntity user = (SysUserEntity) principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        List<String> permsList;

        if (userId == 1){
            List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<>());
            permsList = menuList.stream()
                    .parallel()
                    .map(SysMenuEntity::getPerms)
                    .collect(Collectors.toList());

            /*permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }*/
        }else {
            permsList = sysUserService.queryAllPerms(userId);
        }

        //用户权限列表
        /* Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }*/
        Set<String> permsSet = permsList.stream()
                .parallel()
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(s -> s.split(","))
                .map(Arrays::asList)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
