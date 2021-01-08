package com.purchase.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ben on 2017-03-20 0020.
 */
public class WebPathUtil {


    /**
     * 获取工程web 基本路径，当是80端口时不显示端口 如得到 http://localhost:8080/gpc
     * @param request
     * @return
     */
    public static final String getWebBasePath(HttpServletRequest request) {
        String projectContext = request.getContextPath();
        String basePath = getHostBasePath(request) +  projectContext;
        return basePath;
    }

    /**
     * 获取服务器 基本路径，当是80端口时不显示端口 如得到 http://localhost:8080
     * @param request
     * @return
     */
    public static final String getHostBasePath(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        String port = request.getServerPort() == 80?"":(":"+ request.getServerPort());
        String basePath = scheme + "://" + serverName + port;
        return basePath;
    }

}
