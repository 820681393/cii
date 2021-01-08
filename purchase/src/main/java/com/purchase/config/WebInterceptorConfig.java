package com.purchase.config;

import com.purchase.interceptor.AdminLoginRequestInterceptor;
import com.purchase.interceptor.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Miracle on 2020/8/12.
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //全局basePath路径拦截器
        registry.addInterceptor(getBaseInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/statics/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/logs/**")
                .excludePathPatterns("/doc.html");
        registry.addInterceptor(getAdminLoginRequestInterceptor())
                .addPathPatterns("/admin/**","/merchants/**")
                .excludePathPatterns("/statics/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/logs/**")
                .excludePathPatterns("/doc.html");
    }
    @Bean
    public HandlerInterceptor getAdminLoginRequestInterceptor(){
        //返回自定义的拦截类
        return new AdminLoginRequestInterceptor();
    }
    @Bean
    public HandlerInterceptor getBaseInterceptor(){
        //返回自定义的拦截类
        return new BaseInterceptor();
    }
    //配置swagger2页面
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/statics/");
        // 解决swagger无法访问
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/logs/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")+"\\logs");
    }

    /**
     * 统一时间处理
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }
}
