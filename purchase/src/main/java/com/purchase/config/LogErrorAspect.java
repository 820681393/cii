package com.purchase.config;

import com.purchase.common.log.MyLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/24 0024.
 * 全局错误日志拦截器
 */
@Aspect
@Component
public class LogErrorAspect {

    @Autowired
    ErrorMailNoticeConfig errorMailNoticeConfig;

    MyLogger myLogger=new MyLogger(this.getClass());

    @AfterThrowing(throwing = "ex",pointcut = "execution(* com.purchase..*.*(..))")
    public void afterThrow(JoinPoint joinPoint, Exception ex){
        logInput(joinPoint,ex);
        //详细错误信息
        String errorMsg = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            errorMsg += s + "\n";
        }
        String cla=joinPoint.getTarget().getClass().getName();//action
        String method=joinPoint.getSignature().getName();//method
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb=new StringBuffer();
        sb.append("错误平台：采购平台\n");
        sb.append("错误时间："+sdf.format(new Date())+"\n");
        sb.append("错误信息："+ex+"\n");
        sb.append("接口方法：["+cla+"."+method+"]\n");
        sb.append(MyTelegramBot.appendString(errorMsg)+"\n");
//        errorMailNoticeConfig.sendAliMail(sb.toString());
        MyTelegramBot.sendMsg(sb.toString());

    }

    public void logInput(JoinPoint joinPoint, Exception ex){
        //详细错误信息
        String errorMsg = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            errorMsg += "\tat " + s + "\r\n";
        }
        String cla=joinPoint.getTarget().getClass().getName();//action
        String method=joinPoint.getSignature().getName();//method
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb=new StringBuffer();
        sb.append("\r\n-----------"+sdf.format(new Date())+"------------\r\n");
        sb.append("接口方法：["+cla+"."+method+"]\r\n");
        sb.append("详细错误信息："+ex+"\r\n");
        sb.append(errorMsg+"\r\n");
        myLogger.errorAspect(sb.toString());
    }








}
