package com.wanhao.proback.exception;

/**
 * Created by LiuLiHao on 2018/7/25 10:26.
 * 描述： token无效异常
 * 作者： LiuLiHao
 */
public class TokenInvalidException extends RuntimeException{

    public TokenInvalidException(String message){ super(message);}

    public TokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
