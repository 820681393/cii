package com.purchase.interceptor;

import com.google.gson.Gson;
import com.purchase.enums.InternationalizationEnum;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.MyDateUtil;
import com.purchase.utils.ResponseCode;
import com.purchase.utils.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by South on 2020/09/14.
 */
public class AdminLoginRequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    IRoleInfoService iRoleInfoService;
    @Autowired
    IRoleToMenuService iRoleToMenuService;
    @Autowired
    IMenuInfoService iMenuInfoService;
    @Autowired
    IAdminToRoleService iAdminToRoleService;
    @Autowired
    ICarInfoService iCarInfoService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.indexOf("/admin/login")!=-1){
            return true;
        }
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        if(userAdmin == null) {
            request.getRequestDispatcher("/admin/login/login").forward(request, response);
            return false;
        }
        request.setAttribute("dateTime", new Date());
        List<CarInfo> carInfoList=iCarInfoService.list();
        List<CarInfo> carInfoNewList=new ArrayList<>();
        String week=MyDateUtil.getWeek();
        request.setAttribute("week", week);
        for (CarInfo carInfo : carInfoList) {
            if(carInfo.getLimitDay().contains(week)){
                carInfoNewList.add(carInfo);
            }
        }
        request.setAttribute("carInfoNewList", carInfoNewList);
        List<AdminToRole> adminToRoleList=iAdminToRoleService.findByAiid(userAdmin.getId());
        List<Integer> riids=new ArrayList<>();
        riids.add(-1);
        for (AdminToRole adminToRole : adminToRoleList) {
            riids.add(adminToRole.getRiid());
        }
        List<RoleToMenu> roleToMenuList=iRoleToMenuService.findByRiidIn(riids);
        List<Integer> miids=new ArrayList<>();
        miids.add(-1);
        for (RoleToMenu roleToMenu : roleToMenuList) {
            miids.add(roleToMenu.getMiid());
        }
        List<MenuInfo> menuInfos=iMenuInfoService.listByIds(miids);
        menuInfos.sort((x, y) -> Double.compare(x.getSort(), y.getSort()));
        List<MenuInfo> menuOneInfos=new ArrayList<>();
        for (MenuInfo menuInfo : menuInfos) {
            if(menuInfo.getMiid().equals(0)){
                menuOneInfos.add(menuInfo);
            }
        }
        for (MenuInfo menuOneInfo : menuOneInfos) {
            for (MenuInfo menuInfo : menuInfos) {
                if(menuOneInfo.getId().equals(menuInfo.getMiid())){
                    menuOneInfo.getMenuInfoList().add(menuInfo);
                }
            }
        }
        request.setAttribute("menuInfos",menuOneInfos);
        if(uri.indexOf("/index/index")!=-1){
            return true;
        }
        if(uri.indexOf("/error/info")!=-1){
            return true;
        }
        return true;
    }

    public boolean isPower(HttpServletRequest request, HttpServletResponse response,String uri,AdminInfo userAdmin) throws ServletException, IOException {
        List<MenuInfo> menuInfoList=iMenuInfoService.list();
        boolean isPower=true;
        for (MenuInfo menuInfo : menuInfoList) {
            if(("/"+uri.replace("/admin/","")).equals(menuInfo.getUrl())){
                isPower=false;
                break;
            }
        }
        if(isPower){
            return true;
        }
        if(userAdmin.getUserName().equals("admin")) {
            return true;
        }

        List<AdminToRole> roleList=iAdminToRoleService.findByAiid(userAdmin.getId());
        List<Integer> riids=new ArrayList<>();
        for (AdminToRole adminToRole : roleList) {
            riids.add(adminToRole.getRiid());
        }
        List<RoleToMenu> roleToMenuList=iRoleToMenuService.findByRiidIn(riids);
        List<Integer> mids=new ArrayList<>();
        for (RoleToMenu roleToMenu : roleToMenuList) {
            mids.add(roleToMenu.getMiid());
        }
        if(mids.size()==0){
            output405(request,response);
            return false;
        }

        List<MenuInfo> sqlMenuInfoList=iMenuInfoService.listByIds(mids);
        boolean flag=true;
        for (MenuInfo menuInfo : sqlMenuInfoList) {
            if(("/"+uri.replace("/admin/","")).equals(menuInfo.getUrl())){
                flag=false;
            }
        }
        if (flag){
            output405(request,response);
            return false;
        }
        return true;
    }


    public void output405(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String httpType=request.getHeader("httpType");
        if(httpType==null){
            request.setAttribute("code1","403");
            request.setAttribute("info1","暂无访问权限！");
            request.getRequestDispatcher("/admin/error/info").forward(request, response);
        }else if(httpType.equals("HTML")){
            request.setAttribute("code1","403");
            request.setAttribute("info1","暂无访问权限！");
            request.getRequestDispatcher("/admin/error/info").forward(request, response);
        }else{
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(new Gson().toJson(new ResponseResult(ResponseCode.INSUFFICIENT_AUTHORITY,"权限不足")));
            response.getWriter().flush();
            response.getWriter().close();
        }
    }


}
