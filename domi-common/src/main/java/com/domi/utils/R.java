package com.domi.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * Author 卡卡
 * Created by jing on 2017/6/4.
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
    }

    public static R error(){
        return error(500, "未知异常，请联系管理员");
    }

    public static R error(String message){
        return error(500, message);
    }

    public static R error(int code, String message){
        R r = new R();
        r.put("code", code);
        r.put("message", message);
        return r;
    }

    public static R ok(){
        return new R();
    }

    public static R ok(String msg){
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map){
        R r = new R();
        r.putAll(map);
        return r;
    }

    public  R put(String key, Object value){
        super.put(key, value);
        return this;
    }
}
