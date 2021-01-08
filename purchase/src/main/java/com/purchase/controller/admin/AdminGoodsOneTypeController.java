package com.purchase.controller.admin;

import com.purchase.model.*;
import com.purchase.service.IAdminInfoService;
import com.purchase.service.IGoodsOneTypeService;
import com.purchase.service.IGoodsTowTypeService;
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
@Api(value = "goodsOneType", tags="一级商品分类权限")
public class AdminGoodsOneTypeController {

    @Autowired
    IGoodsOneTypeService iGoodsOneTypeService;

    @Autowired
    IGoodsTowTypeService iGoodsTowTypeService;

    @RequestMapping(value = "/goodsOneType/index")
    @ApiOperation(value = "查询一级商品分类")
    public String index(Model model, HttpServletRequest request){
        List<GoodsOneType> goodsOneTypeList=iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList=iGoodsTowTypeService.list();
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        return "/admin/goodsType/goods_one_type_index";
    }

    @RequestMapping(value = "/goodsOneType/insert")
    @ApiOperation(value = "进入一级商品分类新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/goodsType/goods_one_type_insert";
    }

    @RequestMapping(value = "/goodsOneType/insertIng")
    @ApiOperation(value = "一级商品分类新增")
    public String insertIng(Model model, HttpServletRequest request,GoodsOneType goodsOneType){
        iGoodsOneTypeService.save(goodsOneType);
        return "redirect:/admin/goodsOneType/index";
    }

    @RequestMapping(value = "/goodsOneType/update")
    @ApiOperation(value = "进入一级商品分类修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        GoodsOneType goodsOneType=iGoodsOneTypeService.getById(id);
        model.addAttribute("goodsOneType",goodsOneType);
        return "/admin/goodsType/goods_one_type_update";
    }

    @RequestMapping(value = "/goodsOneType/updateIng")
    @ApiOperation(value = "一级商品分类修改")
    public String updateIng(Model model, HttpServletRequest request,GoodsOneType goodsOneType){
        iGoodsOneTypeService.updateById(goodsOneType);
        return "redirect:/admin/goodsOneType/index";
    }

    @RequestMapping(value = "/goodsOneType/delete")
    @ApiOperation(value = "一级商品分类删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iGoodsOneTypeService.removeById(id);
        iGoodsTowTypeService.deleteByGoid(id);
        return "redirect:/admin/goodsOneType/index";
    }

    @RequestMapping(value = "/goodsOneType/updateGoodsOneType")
    @ResponseBody
    @ApiOperation(value = "异步修改商品一级分类信息")
    public ResponseResult updateGoodsOneType(Model model, HttpServletRequest request, GoodsOneType goodsOneType){
        if(iGoodsOneTypeService.updateById(goodsOneType)){
            return ResponseResult.successResult(goodsOneType,request);
        };
        return ResponseResult.failResult("修改失败");
    }
}
