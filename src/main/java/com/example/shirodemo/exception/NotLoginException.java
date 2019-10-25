package com.example.shirodemo.exception;

/**
 * description:
 *
 * @author qingyu.lan@ucarinc.com
 * @version 1.0
 * @date: 2019-10-25 17:17:47
 **/
public class NotLoginException extends RuntimeException {

    private static final long SeserialVersionUID = -1916988533870733041L;

    public NotLoginException() {
        super((String)null);
    }

    public NotLoginException(String msg) {
        super(msg);
    }
}
