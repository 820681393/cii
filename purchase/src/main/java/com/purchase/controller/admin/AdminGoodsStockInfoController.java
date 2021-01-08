package com.purchase.controller.admin;


import com.github.pagehelper.PageInfo;
import com.purchase.model.GoodsInfo;
import com.purchase.model.GoodsStockInfo;
import com.purchase.model.UnitInfo;
import com.purchase.service.IGoodsStockInfoDetailService;
import com.purchase.service.IGoodsStockInfoService;
import com.purchase.service.IUnitInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Api(value = "goodsStockInfo", tags="商品库存信息管理")
public class AdminGoodsStockInfoController {
    @Autowired
    IGoodsStockInfoService iGoodsStockInfoService;

    @Autowired
    IGoodsStockInfoDetailService iGoodsStockInfoDetailService;

    @RequestMapping(value = "/goodsStockInfo/index")
    @ApiOperation(value = "查询库存信息")
    public String index(Model model, HttpServletRequest request, GoodsStockInfo goodsStockInfo){
        if(goodsStockInfo.getPageNum()==null){
            goodsStockInfo.setPageNum(0);
            goodsStockInfo.setPageSize(15);
        }
        PageInfo<GoodsStockInfo> pageInfos = iGoodsStockInfoService.selectGoodsStockInfoPageInfo(goodsStockInfo);
        model.addAttribute("pageInfos",pageInfos);
        return "/admin/goodsStockInfo/goods_stock_record_index";
    }

    @RequestMapping(value = "/goodsStockInfo/insert")
    @ApiOperation(value = "进入库存新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/goodsStockInfo/goods_stock_info_insert";
    }

    @RequestMapping(value = "/goodsStockInfo/insertIng")
    @ApiOperation(value = "库存新增")
    public String insertIng(Model model, HttpServletRequest request,GoodsStockInfo goodsStockInfo){
        iGoodsStockInfoService.save(goodsStockInfo);
        return "redirect:/admin/goodsStockInfo/index";
    }

    @RequestMapping(value = "/goodsStockInfo/update")
    @ApiOperation(value = "进入库存修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        GoodsStockInfo goodsStockInfo=iGoodsStockInfoService.getById(id);
        model.addAttribute("goodsStockInfo",goodsStockInfo);
        return "/admin/goodsStockInfo/goods_stock_info_update";
    }

    @RequestMapping(value = "/goodsStockInfo/updateIng")
    @ApiOperation(value = "库存修改")
    public String updateIng(Model model, HttpServletRequest request,GoodsStockInfo goodsStockInfo){
        iGoodsStockInfoService.updateById(goodsStockInfo);
        return "redirect:/admin/goodsStockInfo/index";
    }

    @RequestMapping(value = "/goodsStockInfo/delete")
    @ApiOperation(value = "库存信息删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iGoodsStockInfoService.removeById(id);
        return "redirect:/admin/goodsStockInfo/index";
    }
}

