package com.purchase.config;

import com.google.gson.Gson;
import com.purchase.common.log.MyLogger;
import com.purchase.enums.InternationalizationEnum;
import com.purchase.utils.ResponseResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/24 0024.
 * 全局错误日志拦截器
 */
@Aspect
@Component
public class LogMobileErrorAspect {


    MyLogger myLogger=new MyLogger(this.getClass());

    @AfterThrowing(throwing = "ex",pointcut = "execution(* com.purchase.controller.mobile..*.*(..)))")
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        writer(response,request);
    }
    
    /**
     * 向前端返回信息
     */
    public void writer(HttpServletResponse response,HttpServletRequest request){
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(new Gson().toJson(ResponseResult.failResult(InternationalizationEnum.PROGRAM_EXCEPTION.getMsg(request))));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
