package com.purchase.controller.admin;

/**
 * @author Miracle
 * @date 2020/11/3 16:16
 */

import com.purchase.model.MenuInfo;
import com.purchase.service.IAdminToRoleService;
import com.purchase.service.IMenuInfoService;
import com.purchase.service.IRoleToMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("admin")
@Api(value = "menuInfo", tags="权限信息权限")
public class AdminMenuInfoController {

    @Autowired
    IMenuInfoService iMenuInfoService;
    @Autowired
    IRoleToMenuService iRoleToMenuService;

    @RequestMapping(value = "/menuInfo/index")
    @ApiOperation(value = "查询权限信息")
    public String index(Model model, HttpServletRequest request){
        List<MenuInfo> menuInfos=iMenuInfoService.list();
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
        model.addAttribute("menuOneInfos",menuOneInfos);
        return "admin/menuInfo/menu_info_index";
    }

    @RequestMapping(value = "/menuInfo/delete")
    @ApiOperation(value = "删除权限信息")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iMenuInfoService.removeById(id);
        iMenuInfoService.deleteByMiid(id);
        iRoleToMenuService.deleteByMiid(id);
        return "redirect:/admin/menuInfo/index";
    }
    @RequestMapping(value = "/menuInfo/insertAjax")
    public String insertAjax(Model model, HttpServletRequest request,Integer miid){
        model.addAttribute("miid",miid);
        return "admin/menuInfo/menu_info_insert_ajax";
    }

    @RequestMapping(value = "/menuInfo/updateAjax")
    public String updateAjax(Model model, HttpServletRequest request,Integer id){
        MenuInfo sqlMenuInfo=iMenuInfoService.getById(id);
        model.addAttribute("sqlMenuInfo",sqlMenuInfo);
        return "admin/menuInfo/menu_info_update_ajax";
    }

    @RequestMapping(value = "/menuInfo/updateIng")
    public String updateIng(Model model, HttpServletRequest request,MenuInfo menuInfo){
        iMenuInfoService.updateById(menuInfo);
        return "redirect:/admin/menuInfo/index";
    }

    @RequestMapping(value = "/menuInfo/insertIng")
    public String insertIng(Model model, HttpServletRequest request,MenuInfo menuInfo){
        iMenuInfoService.save(menuInfo);
        return "redirect:/admin/menuInfo/index";
    }

}
