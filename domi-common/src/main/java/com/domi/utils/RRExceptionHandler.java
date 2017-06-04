package com.domi.utils;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常处理器
 *
 * Author 卡卡
 * Created by jing on 2017/6/4.
 */
public class RRExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {

        R r = new R();
        try {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.setCharacterEncoding("utf-8");

            if (e instanceof  RRException){
                r.put("code", ((RRException) e).getCode());
                r.put("msg", ((RRException) e).getMsg());
            }else if (e instanceof DuplicateKeyException){
                r = R.error("数据库中已存在该记录");
            }else if (e instanceof AuthorizationException){
                r = R.error("没有权限，请联系管理员授权");
            }else {
                r = R.error();
            }

            //记录异常日志
            logger.error(e.getMessage(), e);

            String json = JSON.toJSONString(r);
            httpServletResponse.getWriter().print(json);
        } catch (IOException e1) {
            logger.error("RRExceptionHandler 异常处理失败", e);
        }
        return new ModelAndView();
    }
}
