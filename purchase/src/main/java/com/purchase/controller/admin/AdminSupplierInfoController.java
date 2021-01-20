package com.purchase.controller.admin;

import com.purchase.config.SystemConfig;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.ExchangeRate;
import com.purchase.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    IGoodsInfoService iGoodsInfoService;

    @Autowired
    SystemConfig systemConfig;

    @Autowired
    IUnitInfoService iUnitInfoService;

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

    @RequestMapping(value = "/supplierInfo/updateIngAjax")
    @ResponseBody
    @ApiOperation(value = "供应商修改Ajax")
    public ResponseResult updateIngAjax(Model model, HttpServletRequest request, SupplierInfo supplierInfo){
        boolean flag=iSupplierInfoService.updateById(supplierInfo);
        if(flag){
            return ResponseResult.successResult("更新成功",request);
        }
        return ResponseResult.successResult("更新失败",request);
    }

    @RequestMapping(value = "/supplierInfo/getGoodsInfoAjax")
    @ApiOperation(value = "查询供应商商品价格")
    public String getGoodsInfoAjax(Model model, HttpServletRequest request,Integer id){
        SupplierInfo supplierInfo = iSupplierInfoService.getById(id);
        List<GoodsToSupplier> goodsToSupplierList=iGoodsToSupplierService.findBySiid(id);
        List<Integer> giids = new ArrayList<>();
        for(GoodsToSupplier goodsToSupplier : goodsToSupplierList){
            giids.add(goodsToSupplier.getGiid());
        }
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.listByIds(giids);
        for(GoodsToSupplier goodsToSupplier : goodsToSupplierList){
            for(GoodsInfo goodsInfo : goodsInfoList){
                if(goodsToSupplier.getGiid().equals(goodsInfo.getId())){
                    goodsToSupplier.setGoodsInfo(goodsInfo);
                }
            }
            BigDecimal originPrice = goodsToSupplier.getOriginPrice();
            BigDecimal originLogFee = goodsToSupplier.getOriginLogFee();
            BigDecimal intLogFee = goodsToSupplier.getIntLogFee();
            BigDecimal awbFee = goodsToSupplier.getAwbFee();
            BigDecimal supplierPrice = (originPrice.add(originLogFee).add(intLogFee).add(awbFee)).multiply(supplierInfo.getExchangeRate());
            BigDecimal localLogFee = goodsToSupplier.getLocalLogFee();
            supplierPrice = supplierPrice.add(localLogFee);
            goodsToSupplier.setSupplierPrice(supplierPrice);

            BigDecimal goodsPrice = goodsToSupplier.getGoodsInfo().getPrice();
            if(goodsToSupplier.getUnitType()==2){
                goodsPrice = goodsToSupplier.getGoodsInfo().getPriceSe();
            }
            BigDecimal diifPrice = goodsToSupplier.getSupplierPrice().subtract(goodsPrice);
            goodsToSupplier.setDiifPrice(diifPrice);
        }
        model.addAttribute("supplierInfo",supplierInfo);
        model.addAttribute("goodsToSupplierList",goodsToSupplierList);
        return "/admin/supplierInfo/supplier_goods_info_index";
    }

    @RequestMapping(value = "/supplierInfo/insertGoodsInfoAjax")
    @ApiOperation(value = "进入供应商商品信息新增")
    public String insertGoodsInfoAjax(Model model, HttpServletRequest request,Integer id){
        SupplierInfo supplierInfo = iSupplierInfoService.getById(id);
        List<GoodsInfo> goodsInfoList =iGoodsInfoService.list();
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();
        for (GoodsInfo goodsInfo: goodsInfoList) {
            for(UnitInfo unitInfo : unitInfoList){
                if(unitInfo.getId().equals(goodsInfo.getUiidPr())){
                    goodsInfo.setUnitPrName(unitInfo.getName());
                }
                if(unitInfo.getId().equals(goodsInfo.getUiidPe())){
                    goodsInfo.setUnitPeName(unitInfo.getName());
                }
            }
        }
        model.addAttribute("supplierInfo",supplierInfo);
        model.addAttribute("goodsInfoList",goodsInfoList);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);

        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        return "/admin/supplierInfo/supplier_goods_info_insert";
    }

    @RequestMapping(value = "/supplierInfo/insertGoodsInfoAjaxIng")
    @ResponseBody
    @ApiOperation(value = "供应商商品信息新增")
    public ResponseResult insertGoodsInfoAjaxIng(Model model, HttpServletRequest request, @RequestBody List<GoodsToSupplier> goodsToSupplierList){
        if(iGoodsToSupplierService.saveBatch(goodsToSupplierList)){
            return ResponseResult.successResult(goodsToSupplierList,request);
        }
        return ResponseResult.failResult("新增失败");
    }

    @RequestMapping(value = "/supplierInfo/selGoodsSupplierAjax")
    @ResponseBody
    @ApiOperation(value = "选择商品供应商")
    public ResponseResult selGoodsSupplierAjax(Model model, HttpServletRequest request,Integer id){
        GoodsToSupplier goodsToSupplier = iGoodsToSupplierService.getById(id);
        GoodsInfo updateGoodsInfo = new GoodsInfo();
        updateGoodsInfo.setId(goodsToSupplier.getGiid());
        updateGoodsInfo.setSiid(goodsToSupplier.getSiid());
        if(iGoodsInfoService.updateById(updateGoodsInfo)){
            return ResponseResult.successResult("选择成功",request);
        }
        return ResponseResult.failResult("失败");
    }

    @RequestMapping(value = "/supplierInfo/deleteGoodsAjax")
    @ResponseBody
    @ApiOperation(value = "供应商商品信息删除")
    public ResponseResult deleteGoodsAjax(Model model, HttpServletRequest request,Integer id){
        if(iGoodsToSupplierService.removeById(id)){
            return ResponseResult.successResult("删除成功",request);
        }
        return ResponseResult.failResult("删除失败");
    }

//    @ApiOperation(value = "设置PESO汇率")
//    @RequestMapping(value = "/supplierInfo/updateExchangeRate")
//    public String updateExchangeRate(Model model, HttpServletRequest request,Float exchangeRate){
//        ExchangeRate.exchangeRate=exchangeRate;
//        return "redirect:/admin/supplierInfo/index";
//    }
//
//    @RequestMapping(value = "/supplierInfo/getGoodsTypeAjax")
//    @ApiOperation(value = "新增供应商品")
//    public String getGoodsTypeAjax(Model model, HttpServletRequest request,Integer id){
//        List<GoodsOneType> goodsOneTypeList=iGoodsOneTypeService.list();
//        List<GoodsTowType> goodsTowTypeList=iGoodsTowTypeService.list();
//        List<GoodsToSupplier> goodsToSupplierList=iGoodsToSupplierService.findBySiid(id);
//        SupplierInfo sqlSupplierInfo=iSupplierInfoService.getById(id);
//        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
//        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
//        model.addAttribute("goodsToSupplierList",goodsToSupplierList);
//        model.addAttribute("sqlSupplierInfo",sqlSupplierInfo);
//        model.addAttribute("exchangeRate",ExchangeRate.exchangeRate);
//        return "/admin/supplierInfo/supplier_info_goods_type_ajax";
//    }
//    @RequestMapping(value = "/supplierInfo/getGoodsTypeInfoAjax")
//    @ApiOperation(value = "查询供应商商品分类价格")
//    public String getGoodsTypeInfoAjax(Model model, HttpServletRequest request,Integer id){
//        List<GoodsOneType> goodsOneTypeList=iGoodsOneTypeService.list();
//        List<GoodsTowType> goodsTowTypeList=iGoodsTowTypeService.list();
//        List<GoodsToSupplier> goodsToSupplierList=iGoodsToSupplierService.findBySiid(id);
//        SupplierInfo sqlSupplierInfo=iSupplierInfoService.getById(id);
//        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
//        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
//        model.addAttribute("goodsToSupplierList",goodsToSupplierList);
//        model.addAttribute("sqlSupplierInfo",sqlSupplierInfo);
//        model.addAttribute("exchangeRate",ExchangeRate.exchangeRate);
//        return "/admin/supplierInfo/supplier_info_goods_type_info_ajax";
//    }
//
//    @RequestMapping(value = "/supplierInfo/deleteGoodsOneTypeAjax")
//    @ResponseBody
//    @ApiOperation(value = "删除分类所有商品价格")
//    public ResponseResult deleteGoodsOneTypeAjax(Model model, HttpServletRequest request,Integer gotid,Integer siid){
//        model.addAttribute("gotid",gotid);
//        model.addAttribute("siid",siid);
//        int a=iGoodsToSupplierService.deleteByGotidAndSiid(gotid,siid);
//        if(a>0){
//            return ResponseResult.successResult("删除成功",request);
//        }
//        return ResponseResult.failResult("删除失败");
//    }
//
//
//    @RequestMapping(value = "/supplierInfo/deleteGoodsTypeAjax")
//    @ResponseBody
//    @ApiOperation(value = "删除价格")
//    public ResponseResult deleteGoodsTypeAjax(Model model, HttpServletRequest request,Integer gotid,Integer gttid,Integer siid){
//        model.addAttribute("gotid",gotid);
//        model.addAttribute("gttid",gttid);
//        model.addAttribute("siid",siid);
//        int a=iGoodsToSupplierService.deleteByGotidAndGttidAndSiid(gotid,gttid,siid);
//        if(a>0){
//            return ResponseResult.successResult("删除成功",request);
//        }
//        return ResponseResult.failResult("删除失败");
//    }
//
//    @RequestMapping(value = "/supplierInfo/addGoodsTypeAjax")
//    @ApiOperation(value = "进入新增价格")
//    public String addGoodsTypeAjax(Model model, HttpServletRequest request,Integer gotid,Integer gttid,Integer siid){
//        model.addAttribute("gotid",gotid);
//        model.addAttribute("gttid",gttid);
//        model.addAttribute("siid",siid);
//        return "/admin/supplierInfo/supplier_info_add_goods_type_ajax";
//    }
//
//    @RequestMapping(value = "/supplierInfo/addGoodsTypeIngAjax")
//    @ResponseBody
//    @ApiOperation(value = "新增价格")
//    public ResponseResult addGoodsTypeIngAjax(Model model, HttpServletRequest request, GoodsToSupplier goodsToSupplier){
//        boolean flag=iGoodsToSupplierService.save(goodsToSupplier);
//        if(flag){
//            return ResponseResult.successResult("新增成功",request);
//        }
//        return ResponseResult.failResult("新增失败");
//    }

}
