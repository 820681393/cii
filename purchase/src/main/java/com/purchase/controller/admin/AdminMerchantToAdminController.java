package com.purchase.controller.admin;

import com.purchase.model.*;
import com.purchase.service.*;
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
 * @date 2020/12/13 21:09
 */
@Controller
@RequestMapping("admin")
@Api(value = "merchantToAdmin", tags="设置商户管理员权限")
public class AdminMerchantToAdminController {


    @Autowired
    IMerchantToAdminService iMerchantToAdminService;
    @Autowired
    IAdminInfoService iAdminInfoService;
    @Autowired
    IMerchantInfoService iMerchantInfoService;
    @Autowired
    IMerchantUserInfoService iMerchantUserInfoService;



    @RequestMapping(value = "/merchantToAdmin/indexAjax")
    @ApiOperation(value = "查询商户管理员权限")
    public String indexAjax(Model model, HttpServletRequest request,Integer miid){
        List<MerchantUserInfo> merchantUserInfoList=iMerchantUserInfoService.findByAiid(miid);
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(miid);
        model.addAttribute("merchantUserInfoList",merchantUserInfoList);
        model.addAttribute("miid",miid);
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        return "admin/userAdmin/merchant_to_admin_ajax";
    }

    @RequestMapping(value = "/merchantToAdmin/deleteMerchantToAdminAjax")
    @ApiOperation(value = "删除商户管理员权限")
    @ResponseBody
    public ResponseResult deleteMerchantToAdminAjax(Model model, HttpServletRequest request,Integer id){
        iMerchantUserInfoService.removeById(id);
        return ResponseResult.successResult("删除成功",request);
    }

    @RequestMapping(value = "/merchantToAdmin/insertMerchantToAdminAjax")
    @ApiOperation(value = "进入添加商户管理员页面")
    public String insertMerchantToAdminAjax(Model model, HttpServletRequest request,Integer miid){
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(miid);
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        model.addAttribute("miid",miid);
        return "admin/userAdmin/merchant_to_admin_insert_ajax";
    }
    @RequestMapping(value = "/merchantToAdmin/updateMerchantToAdminAjax")
    @ApiOperation(value = "进入修改商户管理员页面")
    public String updateMerchantToAdminAjax(Model model, HttpServletRequest request,Integer id){
        MerchantUserInfo sqlMerchantUserInfo=iMerchantUserInfoService.getById(id);
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(sqlMerchantUserInfo.getAiid());
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        model.addAttribute("sqlMerchantUserInfo",sqlMerchantUserInfo);
        return "admin/userAdmin/merchant_to_admin_update_ajax";
    }

    @RequestMapping(value = "/merchantToAdmin/insertMerchantToAdminIngAjax")
    @ApiOperation(value = "添加商户管理员页面")
    @ResponseBody
    public ResponseResult insertMerchantToAdminIngAjax(Model model, HttpServletRequest request,MerchantUserInfo merchantToAdmin){
        iMerchantUserInfoService.save(merchantToAdmin);
        return ResponseResult.successResult("添加成功",request);
    }
    @RequestMapping(value = "/merchantToAdmin/updateMerchantToAdminIngAjax")
    @ApiOperation(value = "修改商户管理员页面")
    @ResponseBody
    public ResponseResult updateMerchantToAdminIngAjax(Model model, HttpServletRequest request,MerchantUserInfo merchantToAdmin){
        iMerchantUserInfoService.updateById(merchantToAdmin);
        return ResponseResult.successResult("修改成功",request);
    }


    @RequestMapping(value = "/merchantInfo/getMerchantInfo")
    @ApiOperation(value = "查看商户配置信息")
    public String getMerchantInfo(Model model, HttpServletRequest request,Integer miid){
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(miid);
        MerchantInfo sqlMerchantInfo=iMerchantInfoService.findByAiid(sqlAdminInfo.getId());
        if(sqlMerchantInfo==null){
            sqlMerchantInfo=new MerchantInfo();
        }
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        model.addAttribute("sqlMerchantInfo",sqlMerchantInfo);
        return "admin/userAdmin/merchant_info_settings_ajax";
    }

    @RequestMapping(value = "/merchantInfo/saveMerchantInfo")
    @ApiOperation(value = "保存商户信息")
    public String saveMerchantInfo(Model model, HttpServletRequest request,MerchantInfo merchantInfo){
        if(merchantInfo.getId()==null){
            iMerchantInfoService.save(merchantInfo);
        }else{
            iMerchantInfoService.updateById(merchantInfo);
        }
        AdminInfo adminInfo = iAdminInfoService.getById(merchantInfo.getAiid());
        return "redirect:/admin/adminUser/index?type="+adminInfo.getType();
    }

    @RequestMapping(value = "/merchantInfo/saveMerchantInfoAjax")
    @ResponseBody
    @ApiOperation(value = "保存商户信息ajax")
    public ResponseResult saveMerchantInfoAjax(Model model, HttpServletRequest request,MerchantInfo merchantInfo){
        if(merchantInfo.getId()==null){
            iMerchantInfoService.save(merchantInfo);
        }else{
            iMerchantInfoService.updateById(merchantInfo);
        }
        AdminInfo adminInfo = iAdminInfoService.getById(merchantInfo.getAiid());
        return ResponseResult.successResult(adminInfo,request);
    }

}
