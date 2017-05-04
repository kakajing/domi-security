package com.domi.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
@Data
public class RRException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -601494750574444665L;

    private String msg;
    private int code = 500;

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
