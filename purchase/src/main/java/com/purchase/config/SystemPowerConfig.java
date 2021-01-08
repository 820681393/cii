package com.purchase.config;

import com.purchase.model.MenuInfo;
import com.purchase.service.IMenuInfoService;
import com.purchase.service.IRoleToMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Miracle
 * @date 2020/12/1 13:55
 * 后台权限配置类
 */
@Component
public class SystemPowerConfig implements ApplicationListener<ContextRefreshedEvent> {

    private String[] pages={"com.purchase.controller.admin","com.purchase.controller.merchants"};
    public static List<MenuInfo> menuInfoList = new ArrayList<>();

    @Autowired
    IMenuInfoService iMenuInfoService;
    @Autowired
    IRoleToMenuService iRoleToMenuService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        System.out.println("菜单扫描执行====="+new Date().getTime());
//        if(event.getApplicationContext().getParent()==null){
//            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(Api.class);
//            for(Object bean : beans.values()){
//                Api api = bean.getClass().getAnnotation(Api.class);
//                ApiIgnore aii=AnnotationUtils.findAnnotation(bean.getClass(), ApiIgnore.class);
//                if(aii!=null){
//                    continue;
//                }
//                boolean isPage=false;
//                for (String page : pages) {
//                    if(bean.getClass().getName().contains(page)){
//                        isPage=true;
//                        break;
//                    }
//                }
//                if(!isPage){
//                    continue;
//                }
//
//                MenuInfo menuInfoOne=new MenuInfo(api.tags()[0],api.value(),api.value());
//                Method[] declaredMethods = bean.getClass().getDeclaredMethods();
//                for (Method declaredMethod : declaredMethods) {
//                    ApiOperation ao = AnnotationUtils.findAnnotation(declaredMethod, ApiOperation.class);
//                    if(ao == null){
//                        continue;
//                    }
//                    ApiIgnore ai=AnnotationUtils.findAnnotation(declaredMethod, ApiIgnore.class);
//                    if(ai!=null){
//                        continue;
//                    }
//                    String url="";
//                    PostMapping postMapping=AnnotationUtils.findAnnotation(declaredMethod, PostMapping.class);
//                    if(postMapping!=null){
//                        String[] values=postMapping.value();
//                        if(values.length>0){
//                            url=values[0];
//                        }
//                    }
//                    GetMapping getMapping=AnnotationUtils.findAnnotation(declaredMethod, GetMapping.class);
//                    if(getMapping!=null){
//                        String[] values=getMapping.value();
//                        if(values.length>0){
//                            url=values[0];
//                        }
//                    }
//                    RequestMapping requestMapping=AnnotationUtils.findAnnotation(declaredMethod, RequestMapping.class);
//                    if(requestMapping!=null){
//                        String[] values=requestMapping.value();
//                        if(values.length>0){
//                            url=values[0];
//                        }
//                    }
//                    DeleteMapping deleteMapping=AnnotationUtils.findAnnotation(declaredMethod, DeleteMapping.class);
//                    if(deleteMapping!=null){
//                        String[] values=deleteMapping.value();
//                        if(values.length>0){
//                            url=values[0];
//                        }
//                    }
//                    PutMapping putMapping=AnnotationUtils.findAnnotation(declaredMethod, PutMapping.class);
//                    if(putMapping!=null){
//                        String[] values=putMapping.value();
//                        if(values.length>0){
//                            url=values[0];
//                        }
//                    }
//                    if(url==null||url.equals("")){
//                        continue;
//                    }
//                    MenuInfo menuInfoTow=new MenuInfo(ao.value(),url.replaceAll("/",""),url);
//                    menuInfoOne.getMenuInfoList().add(menuInfoTow);
//                }
//                menuInfoList.add(menuInfoOne);
//            }
//            updateMeunInfo();
//        }
    }

    private void updateMeunInfo() {
        List<MenuInfo> sqlMenuInfoList=iMenuInfoService.list();
        List<MenuInfo> menuInfoInsertOneList=new ArrayList<>();
        List<MenuInfo> menuInfoUpdateOneList=new ArrayList<>();
        List<Integer> menuInfoDeleteOneIds=new ArrayList<>();
        //一级菜单新增
        for (MenuInfo aopMenuInfo : menuInfoList) {
            boolean insertFlag=true;
            for (MenuInfo menuInfo : sqlMenuInfoList) {
                if(menuInfo.getUrl().equals(aopMenuInfo.getUrl())){
                    insertFlag=false;
                }
            }
            if(insertFlag){
                menuInfoInsertOneList.add(aopMenuInfo);
            }
        }
        //一级菜单修改
        for (MenuInfo menuInfo : sqlMenuInfoList) {
            boolean updateFlag=false;
            for (MenuInfo aopMenuInfo : menuInfoList) {
                if(menuInfo.getUrl().equals(aopMenuInfo.getUrl())){
                    menuInfo.setName(aopMenuInfo.getName());
                    menuInfo.setType(aopMenuInfo.getType());
                    menuInfo.setMenuInfoList(aopMenuInfo.getMenuInfoList());
                    updateFlag=true;
                }
            }
            if(updateFlag){
                menuInfoUpdateOneList.add(menuInfo);
            }
        }
        //一级菜单删除
        for (MenuInfo menuInfo : sqlMenuInfoList) {
            if(!menuInfo.getMiid().equals(0)){
                continue;
            }
            boolean deleteFlag=true;
            for (MenuInfo aopMenuInfo : menuInfoList) {
                if(menuInfo.getUrl().equals(aopMenuInfo.getUrl())){
                    deleteFlag=false;
                }
            }
            if(deleteFlag){
                menuInfoDeleteOneIds.add(menuInfo.getId());
            }
        }
        //一级菜单入库
        if(menuInfoDeleteOneIds.size()>0){
            iMenuInfoService.removeByIds(menuInfoDeleteOneIds);
            iMenuInfoService.deleteByMiidIn(menuInfoDeleteOneIds);
            iRoleToMenuService.deleteByMiidIn(menuInfoDeleteOneIds);
        }
        iMenuInfoService.saveBatch(menuInfoInsertOneList);
        iMenuInfoService.updateBatchById(menuInfoUpdateOneList);


        List<MenuInfo> menuInfoInsertTowList=new ArrayList<>();
        List<MenuInfo> menuInfoUpdateTowList=new ArrayList<>();
        List<Integer> menuInfoDeleteTowIds=new ArrayList<>();
        //二级菜单新增
        for (MenuInfo menuInfoInsertOne : menuInfoInsertOneList) {
            for (MenuInfo menuInfoInsertTow : menuInfoInsertOne.getMenuInfoList()) {
                boolean insertFlag=true;
                for (MenuInfo sqlMenuInfo : sqlMenuInfoList) {
                    if(menuInfoInsertTow.getUrl().equals(sqlMenuInfo.getUrl())){
                        insertFlag=false;
                    }
                }
                if(insertFlag){
                    menuInfoInsertTow.setMiid(menuInfoInsertOne.getId());
                    menuInfoInsertTowList.add(menuInfoInsertTow);
                }
            }
        }
        for (MenuInfo menuInfoUpdateOne : menuInfoUpdateOneList) {
            for (MenuInfo menuInfoInsertTow : menuInfoUpdateOne.getMenuInfoList()) {
                boolean insertFlag=true;
                for (MenuInfo sqlMenuInfo : sqlMenuInfoList) {
                    if(menuInfoInsertTow.getUrl().equals(sqlMenuInfo.getUrl())){
                        insertFlag=false;
                    }
                }
                if(insertFlag){
                    menuInfoInsertTow.setMiid(menuInfoUpdateOne.getId());
                    menuInfoInsertTowList.add(menuInfoInsertTow);
                }
            }
        }

        //二级菜单修改
        for (MenuInfo menuInfoUpdateOne : menuInfoUpdateOneList) {
            for (MenuInfo sqlMenuInfo : sqlMenuInfoList) {
                boolean updateFlag=false;
                for (MenuInfo menuInfoUpdateTow : menuInfoUpdateOne.getMenuInfoList()) {
                    if(menuInfoUpdateTow.getUrl().equals(sqlMenuInfo.getUrl())){
                        sqlMenuInfo.setName(menuInfoUpdateTow.getName());
                        sqlMenuInfo.setType(menuInfoUpdateTow.getType());
                        updateFlag=true;
                    }
                }
                if(updateFlag){
                    menuInfoUpdateTowList.add(sqlMenuInfo);
                }
            }
        }
        //二级菜单删除
        for (MenuInfo menuInfo : sqlMenuInfoList) {
            if(menuInfo.getMiid().equals(0)){
                continue;
            }
            boolean deleteFlag=true;
            for (MenuInfo aopMenuInfo : menuInfoList) {
                for (MenuInfo aopMenuInfoTow : aopMenuInfo.getMenuInfoList()) {
                    if(menuInfo.getUrl().equals(aopMenuInfoTow.getUrl())){
                        deleteFlag=false;
                    }
                }
            }
            if(deleteFlag){
                menuInfoDeleteTowIds.add(menuInfo.getId());
            }
        }
        //二级菜单入库
        if(menuInfoDeleteTowIds.size()>0){
            iMenuInfoService.removeByIds(menuInfoDeleteTowIds);
            iRoleToMenuService.deleteByMiidIn(menuInfoDeleteTowIds);
        }
        iMenuInfoService.saveBatch(menuInfoInsertTowList);
        iMenuInfoService.updateBatchById(menuInfoUpdateTowList);

    }



}
