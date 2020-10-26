package com.mz.common.handler;

import com.mz.common.dto.JsonResult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常统一拦截器
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕捉valid异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult getMessage(MethodArgumentNotValidException exception){
        return JsonResult.error(exception.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult handleRuntimeException(RuntimeException exception){
        return JsonResult.error(exception.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public JsonResult handleAuthorizationException(AuthorizationException exception){
        return JsonResult.instance(401, "请联系管理员授权后操作");
    }

    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception exception){
        return JsonResult.instance(1, exception.getMessage());
    }
}
