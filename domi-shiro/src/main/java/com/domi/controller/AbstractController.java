package com.domi.controller;

import com.domi.entity.SysUserEntity;
import com.domi.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
public class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(AbstractController.class);

    protected SysUserEntity getUser(){
        return ShiroUtils.getUserEntity();
    }

    protected Long getUserId(){
        return getUser().getUserId();
    }
}
