package com.mz.common.shiro;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyExceptionHandler implements HandlerExceptionResolver {
    Log log = LogFactory.getLog(MyExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        if (ex instanceof UnauthenticatedException) {
            httpServletResponse.setStatus(401);
            attributes.put("code", "401");
            attributes.put("msg", "权限不足");
        } else if (ex instanceof UnauthorizedException) {
            httpServletResponse.setStatus(403);
            attributes.put("code", "403");
            attributes.put("msg", "禁止访问");
        } else if(ex instanceof MaxUploadSizeExceededException){
            //文件过大
            log.error(ex.getMessage(),ex);
            attributes.put("code", "400");
            attributes.put("msg", "文件过大，单个文件不得超过10MB，全部文件不得超过100MB");
        }else {
            log.error(ex.getMessage(),ex);
            attributes.put("code", "400");
            attributes.put("msg", ex.getMessage());
        }

        view.setAttributesMap(attributes);
        mv.setView(view);
        return mv;
    }}
