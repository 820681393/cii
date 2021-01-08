package com.purchase.controller.admin;

import com.purchase.model.*;
import com.purchase.service.IAdminInfoService;
import com.purchase.service.IGoodsOneTypeService;
import com.purchase.service.IGoodsTowTypeService;
import com.purchase.service.INoticeInfoService;
import com.purchase.utils.ResponseResult;
import com.purchase.utils.StringUilts;
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
 * @date 2020/11/3 18:54
 */
@Controller
@RequestMapping("admin")
@Api(value = "noticeInfo", tags="公告权限")
public class AdminNoticeInfoController {

    @Autowired
    INoticeInfoService iNoticeInfoService;


    @RequestMapping(value = "/noticeInfo/index")
    @ApiOperation(value = "查询公告信息")
    public String index(Model model, HttpServletRequest request){
        List<NoticeInfo> noticeInfoList=iNoticeInfoService.list();
        model.addAttribute("noticeInfoList",noticeInfoList);
        return "/admin/noticeInfo/notice_info_index";
    }

    @RequestMapping(value = "/noticeInfo/noticeListAjax")
    @ResponseBody
    @ApiOperation(value = "ajax查询公告信息列表")
    public ResponseResult noticeListAjax(Model model,HttpServletRequest request){
        List<NoticeInfo> noticeInfoList = iNoticeInfoService.list();
        return ResponseResult.successResult(noticeInfoList,request);
    }

    @RequestMapping(value = "/noticeInfo/insert")
    @ApiOperation(value = "进入公告信息新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/noticeInfo/notice_info_insert";
    }

    @RequestMapping(value = "/noticeInfo/insertIng")
    @ApiOperation(value = "公告信息新增")
    public String insertIng(Model model, HttpServletRequest request,NoticeInfo noticeInfo){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        noticeInfo.setAiid(userAdmin.getId());
        noticeInfo.setNikeName(userAdmin.getNikeName());
        iNoticeInfoService.save(noticeInfo);
        return "redirect:/admin/noticeInfo/index";
    }

    @RequestMapping(value = "/noticeInfo/update")
    @ApiOperation(value = "进入公告信息修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        NoticeInfo noticeInfo= iNoticeInfoService.getById(id);
        model.addAttribute("noticeInfo",noticeInfo);
        return "/admin/noticeInfo/notice_info_update";
    }

    @RequestMapping(value = "/noticeInfo/updateIng")
    @ApiOperation(value = "公告信息修改")
    public String updateIng(Model model, HttpServletRequest request,NoticeInfo noticeInfo){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        noticeInfo.setAiid(userAdmin.getId());
        noticeInfo.setNikeName(userAdmin.getNikeName());
        iNoticeInfoService.updateById(noticeInfo);
        return "redirect:/admin/noticeInfo/index";
    }

    @RequestMapping(value = "/noticeInfo/delete")
    @ApiOperation(value = "公告信息删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iNoticeInfoService.removeById(id);
        return "redirect:/admin/noticeInfo/index";
    }

}
