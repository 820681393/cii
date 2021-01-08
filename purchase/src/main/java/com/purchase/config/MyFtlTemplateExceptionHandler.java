package com.purchase.config;

import com.purchase.common.log.MyLogger;
import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Administrator on 2019/10/24 0024.
 * 全局ftl模板错误拦截器
 */
public class MyFtlTemplateExceptionHandler implements TemplateExceptionHandler {

    MyLogger myLogger=new MyLogger(this.getClass());

    @Override
    public void handleTemplateException(TemplateException te, Environment environment, Writer writer) throws TemplateException {
        try {
            myLogger.errorFtl(te.getMessage());
            writer.write("[ERROR: " + te.getMessage() + "]");
        } catch (IOException e) {
            myLogger.error(e);
            throw new TemplateException("Failed to print error message. Cause: " + e, environment);
        }
    }
}
