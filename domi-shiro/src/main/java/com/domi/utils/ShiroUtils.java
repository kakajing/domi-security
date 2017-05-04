package com.domi.utils;

import com.domi.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
public class ShiroUtils {

    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static SysUserEntity getUserEntity(){
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }
}
