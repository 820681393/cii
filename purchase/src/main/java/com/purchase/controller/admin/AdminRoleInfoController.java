package com.purchase.controller.admin;

import com.purchase.model.AdminInfo;
import com.purchase.model.MenuInfo;
import com.purchase.model.RoleInfo;
import com.purchase.model.RoleToMenu;
import com.purchase.service.IMenuInfoService;
import com.purchase.service.IRoleInfoService;
import com.purchase.service.IRoleToMenuService;
import com.purchase.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miracle
 * @date 2020/11/3 16:20
 */
@Controller
@RequestMapping("admin")
@Api(value = "roleInfo", tags="角色管理权限")
public class AdminRoleInfoController {

    @Autowired
    IRoleInfoService iRoleInfoService;
    @Autowired
    IRoleToMenuService iRoleToMenuService;
    @Autowired
    IMenuInfoService iMenuInfoService;

    @RequestMapping(value = "/roleInfo/index")
    @ApiOperation(value = "查询角色")
    public String index(Model model, HttpServletRequest request){
        List<RoleInfo> roleInfoList=iRoleInfoService.list();
        model.addAttribute("roleInfoList",roleInfoList);
        return "admin/roleInfo/role_info_index";
    }

    @RequestMapping(value = "/roleInfo/insert")
    @ApiOperation(value = "进入角色新增")
    public String insert(Model model, HttpServletRequest request){
        return "admin/roleInfo/role_info_insert";
    }


    @RequestMapping(value = "/roleInfo/insertIng")
    @ApiOperation(value = "角色新增")
    public String insertIng(Model model, HttpServletRequest request, RoleInfo roleInfo){
        iRoleInfoService.save(roleInfo);
        return "redirect:/admin/roleInfo/index";
    }

    @RequestMapping(value = "/roleInfo/update")
    @ApiOperation(value = "进入角色修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        RoleInfo sqlRoleInfo=iRoleInfoService.getById(id);
        model.addAttribute("sqlRoleInfo",sqlRoleInfo);
        return "admin/roleInfo/role_info_update";
    }
    @RequestMapping(value = "/roleInfo/updateIng")
    @ApiOperation(value = "角色修改")
    public String updateIng(Model model, HttpServletRequest request,RoleInfo roleInfo){
        iRoleInfoService.updateById(roleInfo);
        return "redirect:/admin/roleInfo/index";
    }
    @RequestMapping(value = "/roleInfo/delete")
    @ApiOperation(value = "角色删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iRoleInfoService.removeById(id);
        iRoleToMenuService.deleteByRiid(id);
        return "redirect:/admin/roleInfo/index";
    }

    @RequestMapping(value = "/roleInfo/getEenuAjax")
    @ApiOperation(value = "查询权限列表")
    public String getEenuAjax(Model model, HttpServletRequest request,Integer id){
        List<RoleToMenu> roleToMenuList=iRoleToMenuService.findByRiid(id);
        List<MenuInfo> menuInfos=iMenuInfoService.list();
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
        RoleInfo sqlRoleInfo=iRoleInfoService.getById(id);
        model.addAttribute("menuOneInfos",menuOneInfos);
        model.addAttribute("roleToMenuList",roleToMenuList);
        model.addAttribute("id",id);
        model.addAttribute("sqlRoleInfo",sqlRoleInfo);
        return "admin/roleInfo/role_info_to_menu_ajax";
    }
    @RequestMapping(value = "/roleInfo/addMenuAjax")
    @ResponseBody
    @ApiOperation(value = "添加角色权限")
    public ResponseResult updateIng(Model model, HttpServletRequest request, RoleToMenu roleToMenu){
        boolean flag=iRoleToMenuService.save(roleToMenu);
        if(flag){
            return ResponseResult.successResult("添加成功",request);
        }
        return ResponseResult.successResult("添加失败",request);
    }
    @RequestMapping(value = "/roleInfo/deleteMenuAjax")
    @ResponseBody
    @ApiOperation(value = "删除角色权限")
    public ResponseResult deleteMenuAjax(Model model, HttpServletRequest request,RoleToMenu roleToMenu){
        int flag=iRoleToMenuService.deleteByMiidAndRiid(roleToMenu.getMiid(),roleToMenu.getRiid());
        if(flag>0){
            return ResponseResult.successResult("添加成功",request);
        }
        return ResponseResult.successResult("添加失败",request);
    }
}
