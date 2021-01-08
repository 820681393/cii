package com.purchase.config;

import com.google.gson.Gson;
import com.purchase.annotation.CheckParameter;
import com.purchase.common.log.MyLogger;
import com.purchase.enums.InternationalizationEnum;
import com.purchase.utils.IpUtil;
import com.purchase.utils.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Miracle on 2020/8/16.
 */
@Aspect
@Component
public class CheckParameterAspect {

    MyLogger myLogger=new MyLogger(CheckParameterAspect.class);

    @Around("execution(* com.purchase.controller.mobile..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = attributes.getRequest();
        myLogger.info("=================================================================================================================");
        myLogger.info("【接口AOP】访问接口 : " + request.getRequestURL().toString());
        myLogger.info("【接口AOP】请求方式 : " + request.getMethod());
        myLogger.info("【接口AOP】请求IP : " + IpUtil.getIpAddr(request));
        myLogger.info("【接口AOP】执行的业务方法名= : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        myLogger.info("【接口AOP】业务方法获得的参数= : " + Arrays.toString(joinPoint.getArgs()));
        myLogger.info("=================================================================================================================");
        myLogger.info(" ");
        myLogger.info(" ");
        Class clazz=joinPoint.getSignature().getDeclaringType();
        Method[] methods=clazz.getMethods();
        for (Method method : methods) {
            if(method.getName().equals(joinPoint.getSignature().getName())){
                CheckParameter checkParameter=method.getAnnotation(CheckParameter.class);
                if(checkParameter==null){
                    return joinPoint.proceed();
                }
                ApiImplicitParams apiImplicitParams=method.getAnnotation(ApiImplicitParams.class);
                if(apiImplicitParams!=null){
                    for (ApiImplicitParam apiImplicitParam : apiImplicitParams.value()) {
                        Object param=request.getParameter(apiImplicitParam.name());
                        if(apiImplicitParam.required()){
                            if(param==null){
                                writer(response,apiImplicitParam.name()+ InternationalizationEnum.IS_NOT_NULL.getMsg(request));
                                return null;
                            }
                            if(param instanceof String){
                                String paramStr= (String) param;
                                if(paramStr.equals("")||paramStr.length()==0){
                                    writer(response,apiImplicitParam.name()+InternationalizationEnum.IS_NOT_NULL.getMsg(request));
                                    return null;
                                }
                            }
                        }
                    }
                }
                ApiImplicitParam apiImplicitParam=method.getAnnotation(ApiImplicitParam.class);
                if(apiImplicitParam!=null){
                    Object param=request.getParameter(apiImplicitParam.name());
                    if(apiImplicitParam.required()){
                        if(param==null){
                            writer(response,apiImplicitParam.name()+InternationalizationEnum.IS_NOT_NULL.getMsg(request));
                            return null;
                        }
                        if(param instanceof String){
                            String paramStr= (String) param;
                            if(paramStr.equals("")||paramStr.length()==0){
                                writer(response,apiImplicitParam.name()+InternationalizationEnum.IS_NOT_NULL.getMsg(request));
                                return null;
                            }
                        }
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 向前端返回信息
     * @param info
     */
    public void writer(HttpServletResponse response,String info){
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(new Gson().toJson(ResponseResult.failResult(info)));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
