package com.purchase.controller.admin;

import com.github.pagehelper.PageInfo;
import com.purchase.dao.IGoodsInfoDao;
import com.purchase.model.AdminInfo;
import com.purchase.model.GoodsInfo;
import com.purchase.model.OrderInfo;
import com.purchase.service.*;
import com.purchase.utils.ResponseResult;
import com.purchase.utils.StringUilts;
import com.purchase.vo.SumGoodsInfoVO;
import com.purchase.vo.GoodsPriceTrendVO;
import com.purchase.vo.SupplierPriceTrendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
@Api(value = "report", tags="报表信息权限")
public class AdminReportController {
    @Autowired
    IOrderInfoService iOrderInfoService;

    @Autowired
    IOrderInfoDetailService iOrderInfoDetailService;

    @Autowired
    IGoodsInfoService iGoodsInfoService;

    @Autowired
    ISupplierInfoService iSupplierInfoService;

    @Autowired
    IAdminInfoService iAdminInfoService;

    @Autowired
    IGoodsInfoDao iGoodsInfoDao;

    @RequestMapping(value = "/report/orderInfoReport")
    @ApiOperation(value = "查询采购单报表")
    public String orderInfoReport(Model model, HttpServletRequest request,OrderInfo orderInfo){
        if(orderInfo.getPageNum()==null){
            orderInfo.setPageNum(0);
            orderInfo.setPageSize(15);
        }
        orderInfo.setState(3);
        PageInfo<OrderInfo> pageInfos = iOrderInfoService.selectOrderInfoPageInfo(orderInfo);
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        for (OrderInfo oInfo:pageInfos.getList()) {
            for (AdminInfo adminInfo:adminInfoList) {
                if(oInfo.getXuid().equals(adminInfo.getId())){
                    oInfo.setXuName(adminInfo.getNikeName());
                }
                if(oInfo.getSuid().equals(adminInfo.getId())){
                    oInfo.setSuName(adminInfo.getNikeName());
                }
                if(oInfo.getHuid()!=null){
                    if(oInfo.getHuid().equals(adminInfo.getId())){
                        oInfo.setHuName(adminInfo.getNikeName());
                    }
                }
            }
        }
        model.addAttribute("pageInfos",pageInfos);
        return "/admin/report/order_info_report";
    }

    @RequestMapping(value = "/report/goodsTypeSumReport")
    @ApiOperation(value = "查询商品分类报表")
    public String goodsTypeSumReport(Model model, HttpServletRequest request, GoodsInfo goodsInfo,Integer goodsType,Boolean isSumYm){
        if(goodsType==null){
            goodsType = 1;
        }
        List<SumGoodsInfoVO> sumGoodsInfo = iGoodsInfoDao.sumGoodsInfo(goodsInfo,goodsType,isSumYm);
        model.addAttribute("sumGoodsInfo",sumGoodsInfo);
        model.addAttribute("isSumYm",isSumYm);
        model.addAttribute("goodsType",goodsType);
        return "/admin/report/goods_type_sum_report";
    }

    @RequestMapping(value = "/report/goodsPriceTrend")
    @ApiOperation(value = "进入查询商品价格走势")
    public String goodsPriceTrend(Model model, HttpServletRequest request){
        return "/admin/report/goods_price_trend";
    }

    @RequestMapping(value = "/report/goodsPriceTrendData")
    @ResponseBody
    @ApiOperation(value = "查询商品价格走势")
    public ResponseResult goodsPriceTrendData(Model model, HttpServletRequest request, GoodsInfo goodsInfo,Integer[] goodsIds){
        List<GoodsPriceTrendVO> sumGoodsInfo = iGoodsInfoDao.goodsPriceTrend(goodsInfo,goodsIds);
        ArrayList<String> xtext = new ArrayList<String>();
        JSONArray seriesDataArr = new JSONArray();
        for(int i = 0;i<sumGoodsInfo.size();i++){
            GoodsPriceTrendVO goodsPriceTrendVO = sumGoodsInfo.get(i);
            String dateStr = goodsPriceTrendVO.getCtYmd();
            String name = goodsPriceTrendVO.getName();
            if(StringUilts.isEmptyOrNull(name)){
                name = "未知";
            }
            Double realPrice = goodsPriceTrendVO.getRealPrice();
            Boolean isAddx = true;
            for(int j = 0;j<xtext.size();j++){
                if(dateStr.equals(xtext.get(j))){
                    isAddx = false;
                    break;
                }
            }
            if(isAddx){
                xtext.add(dateStr);
            }
            Boolean isAddData = true;
            for(int d = 0;d<seriesDataArr.size();d++){
                JSONObject seriesData = seriesDataArr.getJSONObject(d);
                String sName = seriesData.getString("name");
                if(sName.equals(name)){
                    isAddData = false;
                    JSONArray data = seriesData.getJSONArray("data");
                    data.add(realPrice);
                }
            }
            if(isAddData){
                JSONObject seriesData = new JSONObject();
                seriesData.put("name",name);
                JSONArray data = new JSONArray();
                data.add(realPrice);
                seriesData.put("data",data);
                seriesDataArr.add(seriesData);
            }
        }
        JSONObject result = new JSONObject();
        result.put("xtext",xtext);
        result.put("seriesDataArr",seriesDataArr);
        return ResponseResult.successResult(result,request);
    }

    @RequestMapping(value = "/report/supplierPriceTrend")
    @ApiOperation(value = "进入查询供应商采购汇总")
    public String supplierPriceTrend(Model model, HttpServletRequest request){
        return "/admin/report/goods_price_trend";
    }


    @RequestMapping(value = "/report/supplierPriceTrendData")
    @ResponseBody
    @ApiOperation(value = "查询供应商采购汇总")
    public ResponseResult supplierPriceTrendData(Model model, HttpServletRequest request, GoodsInfo goodsInfo){
        List<SupplierPriceTrendVO> sumGoodsInfo = iGoodsInfoDao.supplierPriceTrend(goodsInfo);
        ArrayList<String> xtext = new ArrayList<String>();
        JSONArray seriesDataArr = new JSONArray();
        for(int i = 0;i<sumGoodsInfo.size();i++){
            SupplierPriceTrendVO supplierPriceTrendVO = sumGoodsInfo.get(i);
            String dateStr = supplierPriceTrendVO.getCtYmd();
            String name = supplierPriceTrendVO.getSupplierName();
            Double totalPrice = supplierPriceTrendVO.getTotalPrice();
            Boolean isAddx = true;
            for(int j = 0;j<xtext.size();j++){
                if(dateStr.equals(xtext.get(j))){
                    isAddx = false;
                    break;
                }
            }
            if(isAddx){
                xtext.add(dateStr);
            }
            Boolean isAddData = true;
            for(int d = 0;d<seriesDataArr.size();d++){
                JSONObject seriesData = seriesDataArr.getJSONObject(d);
                String sName = seriesData.getString("name");
                if(sName.equals(name)){
                    isAddData = false;
                    JSONArray data = seriesData.getJSONArray("data");
                    data.add(totalPrice);
                }
            }
            if(isAddData){
                JSONObject seriesData = new JSONObject();
                seriesData.put("name",name);
                JSONArray data = new JSONArray();
                data.add(totalPrice);
                seriesData.put("data",data);
                seriesDataArr.add(seriesData);
            }
        }
        JSONObject result = new JSONObject();
        result.put("xtext",xtext);
        result.put("seriesDataArr",seriesDataArr);
        return ResponseResult.successResult(result,request);
    }
}
