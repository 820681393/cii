package com.purchase.controller.admin;

import com.purchase.model.*;
import com.purchase.service.IGoodsOneTypeService;
import com.purchase.service.IGoodsToSupplierService;
import com.purchase.service.IGoodsTowTypeService;
import com.purchase.service.ISupplierInfoService;
import com.purchase.utils.ExchangeRate;
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
 * @date 2020/11/3 21:21
 */
@Controller
@RequestMapping("admin")
@Api(value = "supplierInfo", tags="供应商管理权限")
public class AdminSupplierInfoController {

    @Autowired
    ISupplierInfoService iSupplierInfoService;
    @Autowired
    IGoodsOneTypeService iGoodsOneTypeService;

    @Autowired
    IGoodsTowTypeService iGoodsTowTypeService;

    @Autowired
    IGoodsToSupplierService iGoodsToSupplierService;

    @RequestMapping(value = "/supplierInfo/index")
    @ApiOperation(value = "查询供应商")
    public String index(Model model, HttpServletRequest request){
        List<SupplierInfo> supplierInfoList=iSupplierInfoService.list();
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("exchangeRate",ExchangeRate.exchangeRate);
        return "/admin/supplierInfo/supplier_info_index";
    }


    @RequestMapping(value = "/supplierInfo/insert")
    @ApiOperation(value = "进入供应商新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/supplierInfo/supplier_info_insert";
    }

    @RequestMapping(value = "/supplierInfo/insertIng")
    @ApiOperation(value = "供应商新增")
    public String insertIng(Model model, HttpServletRequest request,SupplierInfo supplierInfo){
        iSupplierInfoService.save(supplierInfo);
        return "redirect:/admin/supplierInfo/index";
    }
    @RequestMapping(value = "/supplierInfo/update")
    @ApiOperation(value = "进入供应商修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        SupplierInfo sqlSupplierInfo=iSupplierInfoService.getById(id);
        model.addAttribute("sqlSupplierInfo",sqlSupplierInfo);
        return "/admin/supplierInfo/supplier_info_update";
    }
    @ApiOperation(value = "供应商修改")
    @RequestMapping(value = "/supplierInfo/updateIng")
    public String updateIng(Model model, HttpServletRequest request,SupplierInfo supplierInfo){
        iSupplierInfoService.updateById(supplierInfo);
        return "redirect:/admin/supplierInfo/index";
    }
    @ApiOperation(value = "设置PESO汇率")
    @RequestMapping(value = "/supplierInfo/updateExchangeRate")
    public String updateExchangeRate(Model model, HttpServletRequest request,Float exchangeRate){
        ExchangeRate.exchangeRate=exchangeRate;
        return "redirect:/admin/supplierInfo/index";
    }

    @RequestMapping(value = "/supplierInfo/getGoodsTypeAjax")
    @ApiOperation(value = "新增供应商品")
    public String getGoodsTypeAjax(Model model, HttpServletRequest request,Integer id){
        List<GoodsOneType> goodsOneTypeList=iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList=iGoodsTowTypeService.list();
        List<GoodsToSupplier> goodsToSupplierList=iGoodsToSupplierService.findBySiid(id);
        SupplierInfo sqlSupplierInfo=iSupplierInfoService.getById(id);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("goodsToSupplierList",goodsToSupplierList);
        model.addAttribute("sqlSupplierInfo",sqlSupplierInfo);
        model.addAttribute("exchangeRate",ExchangeRate.exchangeRate);
        return "/admin/supplierInfo/supplier_info_goods_type_ajax";
    }
    @RequestMapping(value = "/supplierInfo/getGoodsTypeInfoAjax")
    @ApiOperation(value = "查询供应商商品分类价格")
    public String getGoodsTypeInfoAjax(Model model, HttpServletRequest request,Integer id){
        List<GoodsOneType> goodsOneTypeList=iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList=iGoodsTowTypeService.list();
        List<GoodsToSupplier> goodsToSupplierList=iGoodsToSupplierService.findBySiid(id);
        SupplierInfo sqlSupplierInfo=iSupplierInfoService.getById(id);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("goodsToSupplierList",goodsToSupplierList);
        model.addAttribute("sqlSupplierInfo",sqlSupplierInfo);
        model.addAttribute("exchangeRate",ExchangeRate.exchangeRate);
        return "/admin/supplierInfo/supplier_info_goods_type_info_ajax";
    }

    @RequestMapping(value = "/supplierInfo/deleteGoodsOneTypeAjax")
    @ResponseBody
    @ApiOperation(value = "删除分类所有商品价格")
    public ResponseResult deleteGoodsOneTypeAjax(Model model, HttpServletRequest request,Integer gotid,Integer siid){
        model.addAttribute("gotid",gotid);
        model.addAttribute("siid",siid);
        int a=iGoodsToSupplierService.deleteByGotidAndSiid(gotid,siid);
        if(a>0){
            return ResponseResult.successResult("删除成功",request);
        }
        return ResponseResult.failResult("删除失败");
    }


    @RequestMapping(value = "/supplierInfo/deleteGoodsTypeAjax")
    @ResponseBody
    @ApiOperation(value = "删除价格")
    public ResponseResult deleteGoodsTypeAjax(Model model, HttpServletRequest request,Integer gotid,Integer gttid,Integer siid){
        model.addAttribute("gotid",gotid);
        model.addAttribute("gttid",gttid);
        model.addAttribute("siid",siid);
        int a=iGoodsToSupplierService.deleteByGotidAndGttidAndSiid(gotid,gttid,siid);
        if(a>0){
            return ResponseResult.successResult("删除成功",request);
        }
        return ResponseResult.failResult("删除失败");
    }

    @RequestMapping(value = "/supplierInfo/addGoodsTypeAjax")
    @ApiOperation(value = "进入新增价格")
    public String addGoodsTypeAjax(Model model, HttpServletRequest request,Integer gotid,Integer gttid,Integer siid){
        model.addAttribute("gotid",gotid);
        model.addAttribute("gttid",gttid);
        model.addAttribute("siid",siid);
        return "/admin/supplierInfo/supplier_info_add_goods_type_ajax";
    }

    @RequestMapping(value = "/supplierInfo/addGoodsTypeIngAjax")
    @ResponseBody
    @ApiOperation(value = "新增价格")
    public ResponseResult addGoodsTypeIngAjax(Model model, HttpServletRequest request, GoodsToSupplier goodsToSupplier){
        boolean flag=iGoodsToSupplierService.save(goodsToSupplier);
        if(flag){
            return ResponseResult.successResult("新增成功",request);
        }
        return ResponseResult.failResult("新增失败");
    }

}
