package com.purchase.controller.admin;

import com.purchase.model.GoodsOneType;
import com.purchase.model.GoodsTowType;
import com.purchase.model.LanguageInfo;
import com.purchase.service.ILanguageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Miracle
 * @date 2020/12/1 18:19
 */
@Controller
@RequestMapping("admin")
@Api(value = "languageInfo", tags="语言信息权限")
public class AdminLanguageInfoController {

    @Autowired
    ILanguageInfoService iLanguageInfoService;

    @RequestMapping(value = "/languageInfo/index")
    @ApiOperation(value = "查询语言信息")
    public String index(Model model, HttpServletRequest request){
        List<LanguageInfo> languageInfoList =iLanguageInfoService.list();
        model.addAttribute("languageInfoList",languageInfoList);
        return "/admin/languageInfo/language_info_index";
    }

    @RequestMapping(value = "/languageInfo/insert")
    @ApiOperation(value = "进入语言信息新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/languageInfo/language_info_insert_ajax";
    }

    @RequestMapping(value = "/languageInfo/insertIng")
    @ApiOperation(value = "语言信息新增")
    public String insert(Model model, HttpServletRequest request,LanguageInfo languageInfo){
        iLanguageInfoService.save(languageInfo);
        return "redirect:/admin/languageInfo/index";
    }

    @RequestMapping(value = "/languageInfo/update")
    @ApiOperation(value = "进入语言信息修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        LanguageInfo sqlLanguageInfo=iLanguageInfoService.getById(id);
        model.addAttribute("sqlLanguageInfo",sqlLanguageInfo);
        return "/admin/languageInfo/language_info_update_ajax";
    }

    @RequestMapping(value = "/languageInfo/updateIng")
    @ApiOperation(value = "语言信息修改")
    public String updateIng(Model model, HttpServletRequest request,LanguageInfo languageInfo){
        iLanguageInfoService.updateById(languageInfo);
        return "redirect:/admin/languageInfo/index";
    }


    @RequestMapping(value = "/languageInfo/delete")
    @ApiOperation(value = "语言信息删除")
    public String updateIng(Model model, HttpServletRequest request,Integer id){
        iLanguageInfoService.removeById(id);
        return "redirect:/admin/languageInfo/index";
    }


}
