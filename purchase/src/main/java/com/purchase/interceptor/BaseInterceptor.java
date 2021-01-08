package com.purchase.interceptor;

import com.purchase.model.LanguageInfo;
import com.purchase.service.ILanguageInfoService;
import com.purchase.utils.WebPathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Miracle on 2020/8/12.
 */
public class BaseInterceptor implements HandlerInterceptor {


    @Autowired
    ILanguageInfoService iLanguageInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("basePath", WebPathUtil.getWebBasePath(request));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept,"
                + "content-Type,origin,x-requested-with,content-type,accept,authorization,token,id,X-Custom-Header,X-Cookie,Connection,User-Agent,Cookie,*");
        response.setHeader("Access-Control-Request-Headers", "Authorization,Origin, X-Requested-With,content-Type,Accept");
        response.setHeader("Access-Control-Expose-Headers", "*");
        List<LanguageInfo> languageInfoList=iLanguageInfoService.list();
        request.setAttribute("languageInfoSessionList",languageInfoList);
        return true;
    }


}
