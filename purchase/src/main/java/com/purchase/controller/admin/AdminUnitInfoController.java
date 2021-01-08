package com.purchase.controller.admin;


import com.purchase.model.GoodsOneType;
import com.purchase.model.GoodsTowType;
import com.purchase.model.UnitInfo;
import com.purchase.service.IGoodsOneTypeService;
import com.purchase.service.IGoodsTowTypeService;
import com.purchase.service.IUnitInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Miracle
 * @since 2020-11-21
 */
@Controller
@RequestMapping("admin")
@Api(value = "unitInfo", tags="单位管理权限")
public class AdminUnitInfoController {
    @Autowired
    IUnitInfoService iUnitInfoService;

    @RequestMapping(value = "/unitInfo/index")
    @ApiOperation(value = "查询单位")
    public String index(Model model, HttpServletRequest request){
        List<UnitInfo> unitInfoList = iUnitInfoService.list();
        model.addAttribute("unitInfoList",unitInfoList);
        return "/admin/unitInfo/unit_info_index";
    }

    @RequestMapping(value = "/unitInfo/insert")
    @ApiOperation(value = "进入单位新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/unitInfo/unit_info_insert";
    }

    @RequestMapping(value = "/unitInfo/insertIng")
    @ApiOperation(value = "单位新增")
    public String insertIng(Model model, HttpServletRequest request,UnitInfo unitInfo){
        iUnitInfoService.save(unitInfo);
        return "redirect:/admin/unitInfo/index";
    }

    @RequestMapping(value = "/unitInfo/update")
    @ApiOperation(value = "进入单位修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        UnitInfo unitInfo=iUnitInfoService.getById(id);
        model.addAttribute("unitInfo",unitInfo);
        return "/admin/unitInfo/unit_info_update";
    }

    @RequestMapping(value = "/unitInfo/updateIng")
    @ApiOperation(value = "单位修改")
    public String updateIng(Model model, HttpServletRequest request,UnitInfo unitInfo){
        iUnitInfoService.updateById(unitInfo);
        return "redirect:/admin/unitInfo/index";
    }

    @RequestMapping(value = "/unitInfo/delete")
    @ApiOperation(value = "单位删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iUnitInfoService.removeById(id);
        return "redirect:/admin/unitInfo/index";
    }
}

