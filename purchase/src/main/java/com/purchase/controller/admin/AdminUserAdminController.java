package com.purchase.controller.admin;

import com.github.pagehelper.PageInfo;
import com.purchase.model.*;
import com.purchase.service.IAdminInfoService;
import com.purchase.service.IAdminToRoleService;
import com.purchase.service.IMerchantInfoService;
import com.purchase.service.IRoleInfoService;
import com.purchase.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Miracle
 * @date 2020/11/3 15:27
 */
@Controller
@RequestMapping("admin")
@Api(value = "userAdmin", tags="用户管理权限")
public class AdminUserAdminController {

    @Autowired
    IAdminInfoService iAdminInfoService;
    @Autowired
    IAdminToRoleService iAdminToRoleService;
    @Autowired
    IRoleInfoService iRoleInfoService;
    @Autowired
    IMerchantInfoService iMerchantInfoService;

    @RequestMapping(value = "/adminUser/index")
    @ApiOperation(value = "查询用户")
    public String index(Model model, HttpServletRequest request,AdminInfo adminInfo){
        PageInfo<AdminInfo> pageInfo=iAdminInfoService.selectAdminInfoPageInfo(adminInfo);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("adminInfo",adminInfo);
        return "admin/userAdmin/admin_user_index";
    }

    @RequestMapping(value = "/adminUser/insert")
    @ApiOperation(value = "进入用户新增")
    public String insert(Model model, HttpServletRequest request){

        return "admin/userAdmin/admin_user_insert";
    }
    @RequestMapping(value = "/adminUser/merchantInsert")
    @ApiOperation(value = "进入商户用户新增")
    public String merchantInsert(Model model, HttpServletRequest request){

        return "admin/userAdmin/merchant_to_admin_user_insert";
    }
    @RequestMapping(value = "/adminUser/insertIng")
    @ApiOperation(value = "用户新增")
    public String insertIng(Model model, HttpServletRequest request,AdminInfo adminInfo){
        iAdminInfoService.save(adminInfo);
        return "redirect:/admin/adminUser/index?type="+adminInfo.getType();
    }
    @RequestMapping(value = "/adminUser/insertIngAjax")
    @ResponseBody
    @ApiOperation(value = "用户新增ajax")
    public ResponseResult insertIngAjax(Model model, HttpServletRequest request,AdminInfo adminInfo){
        iAdminInfoService.save(adminInfo);
        return ResponseResult.successResult(adminInfo,request);
    }
    @RequestMapping(value = "/adminUser/merchantUpdate")
    @ApiOperation(value = "进入商户用户修改")
    public String merchantUpdate(Model model, HttpServletRequest request,Integer id){
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(id);
        MerchantInfo sqlMerchantInfo = iMerchantInfoService.findByAiid(id);
        if(sqlMerchantInfo==null){
            sqlMerchantInfo=new MerchantInfo();
        }
        model.addAttribute("adminUser",sqlAdminInfo);
        model.addAttribute("sqlMerchantInfo",sqlMerchantInfo);
        return "admin/userAdmin/merchant_to_admin_user_update";
    }
    @RequestMapping(value = "/adminUser/update")
    @ApiOperation(value = "进入用户修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(id);
        model.addAttribute("adminUser",sqlAdminInfo);
        return "admin/userAdmin/admin_user_update";
    }
    @ApiOperation(value = "用户修改")
    @RequestMapping(value = "/adminUser/updateIng")
    public String updateIng(Model model, HttpServletRequest request,AdminInfo adminInfo){
        iAdminInfoService.updateById(adminInfo);
        return "redirect:/admin/adminUser/index?type="+adminInfo.getType();
    }
    @ApiOperation(value = "用户修改ajax")
    @ResponseBody
    @RequestMapping(value = "/adminUser/updateIngAjax")
    public ResponseResult updateIngAjax(Model model, HttpServletRequest request,AdminInfo adminInfo){
        iAdminInfoService.updateById(adminInfo);
        return ResponseResult.successResult(adminInfo,request);
    }



    @RequestMapping(value = "/adminUser/getUserRoleAjax")
    @ApiOperation(value = "查看用户角色")
    public String getUserRoleAjax(Model model, HttpServletRequest request,Integer id){
        List<RoleInfo> roleInfoList=iRoleInfoService.list();
        List<AdminToRole> adminToRoleList=iAdminToRoleService.findByAiid(id);
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(id);
        model.addAttribute("roleInfoList",roleInfoList);
        model.addAttribute("adminToRoleList",adminToRoleList);
        model.addAttribute("id",id);
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        return "admin/userAdmin/admin_user_role_ajax";
    }

    @RequestMapping(value = "/adminUser/addRoleAjax")
    @ResponseBody
    @ApiOperation(value = "新增用户角色")
    public ResponseResult updateIng(Model model, HttpServletRequest request, AdminToRole adminToRole){
        boolean flag=iAdminToRoleService.save(adminToRole);
        if(flag){
            return ResponseResult.successResult("新增成功",request);
        }
        return ResponseResult.failResult("新增失败");
    }
    @RequestMapping(value = "/adminUser/deleteRoleAjax")
    @ResponseBody
    @ApiOperation(value = "删除用户角色")
    public ResponseResult deleteMenuAjax(Model model, HttpServletRequest request,AdminToRole adminToRole){
        int flag=iAdminToRoleService.deleteByAiidAndRiid(adminToRole.getAiid(),adminToRole.getRiid());
        if(flag>0){
            return ResponseResult.successResult("新增成功",request);
        }
        return ResponseResult.failResult("新增失败");
    }

    @RequestMapping(value = "/adminUser/delete")
    @ApiOperation(value = "删除用户")
    public String delete(Model model, HttpServletRequest request,Integer id){
        AdminInfo adminInfo = iAdminInfoService.getById(id);
        iAdminInfoService.removeById(id);
        iAdminToRoleService.deleteByAiid(id);
        return "redirect:/admin/adminUser/index?type="+adminInfo.getType();
    }
}
