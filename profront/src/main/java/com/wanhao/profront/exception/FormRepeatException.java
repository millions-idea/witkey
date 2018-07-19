package com.wanhao.profront.exception;

/**
 * Created by LiuLiHao on 2018/7/19 23:48.
 * 描述：重复提交异常
 * 作者： LiuLiHao
 */
public class FormRepeatException extends RuntimeException {
    public FormRepeatException(String message){ super(message);}

    public FormRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
