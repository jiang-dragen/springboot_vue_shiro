package com.jiang.back.exception;

import com.jiang.back.Response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
@project name back
@author jiang
@time   2021-09-17-9:08
        处理全局异常
*/
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = UnauthorizedException.class)
    public Result handler(UnauthorizedException e) {
        log.error("运行时异常------------{}" + e.getMessage());
        return new Result().setCode(401).setMessage("没有权限访问");
    }

    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Result handler(ExpiredCredentialsException e) {
        log.error("运行时异常------------{}" + e.getMessage());
        return new Result().setCode(401).setMessage("登录已过期，请重新登录");
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public Result handler(UnauthenticatedException e) {
        log.error("运行时异常------------{}" + e.getMessage());
        return new Result().setCode(401).setMessage("未登录");
    }
}
