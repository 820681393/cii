package com.purchase.controller.admin;


import com.github.pagehelper.PageInfo;
import com.purchase.config.SystemConfig;
import com.purchase.dao.IGoodsStockInfoDao;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.FileUtil;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    IAdminInfoService iAdminInfoService;

    @Autowired
    IUnitInfoService iUnitInfoService;

    @Autowired
    IGoodsInfoService iGoodsInfoService;

    @Autowired
    IGoodsOneTypeService iGoodsOneTypeService;

    @Autowired
    IGoodsTowTypeService iGoodsTowTypeService;

    @Autowired
    ISupplierInfoService iSupplierInfoService;

    @Autowired
    IGoodsStockInfoDao iGoodsStockInfoDao;

    @Autowired
    SystemConfig systemConfig;

    @RequestMapping(value = "/goodsStockInfo/index")
    @ApiOperation(value = "查询库存信息")
    public String index(Model model, HttpServletRequest request, GoodsStockInfo goodsStockInfo){
        if(goodsStockInfo.getPageNum()==null){
            goodsStockInfo.setPageNum(0);
            goodsStockInfo.setPageSize(15);
        }
        PageInfo<GoodsStockInfo> pageInfos = iGoodsStockInfoService.selectGoodsStockInfoPageInfo(goodsStockInfo);
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        for (GoodsStockInfo stockInfo:pageInfos.getList()) {
            for (AdminInfo adminInfo:adminInfoList) {
                if(stockInfo.getAiid().equals(adminInfo.getId())){
                    stockInfo.setAdminName(adminInfo.getNikeName());
                }
            }
        }
        model.addAttribute("pageInfos",pageInfos);
        model.addAttribute("goodsStockInfo",goodsStockInfo);
        return "/admin/goodsStockInfo/goods_stock_record_index";
    }

    @RequestMapping(value = "/goodsStockInfo/stockInput")
    @ApiOperation(value = "进入(入库/出库/盘点)")
    public String stockInput(Model model, HttpServletRequest request,@RequestParam(value = "mode", required = false) Integer mode){
        List<GoodsInfo> goodsInfoList =iGoodsInfoService.list();
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList=iSupplierInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();
        for (GoodsInfo goodsInfo: goodsInfoList) {
            for (SupplierInfo supplierInfo: supplierInfoList) {
                if(goodsInfo.getSiid()!=null){
                    if(goodsInfo.getSiid().equals(supplierInfo.getId())){
                        goodsInfo.setSupplierName(supplierInfo.getName());
                        goodsInfo.setSupplierAddress(supplierInfo.getAddress());
                    }
                }
            }
            for(UnitInfo unitInfo : unitInfoList){
                if(unitInfo.getId().equals(goodsInfo.getUiidPr())){
                    goodsInfo.setUnitPrName(unitInfo.getName());
                }
                if(unitInfo.getId().equals(goodsInfo.getUiidPe())){
                    goodsInfo.setUnitPeName(unitInfo.getName());
                }
            }
        }
        model.addAttribute("goodsInfoList",goodsInfoList);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);

        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("mode",mode);
        return "/admin/goodsStockInfo/goods_stock_input";
    }

    @RequestMapping(value = "/goodsStockInfo/stockInputIng")
    @ResponseBody
    @ApiOperation(value = "提交(入库/出库/盘点)")
    public ResponseResult stockInputIng(Model model, HttpServletRequest request, GoodsStockInfo goodsStockInfo,@RequestParam(value = "file", required = false) MultipartFile file){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        goodsStockInfo.setAiid(userAdmin.getId());

        Integer todayOrderNumber = iGoodsStockInfoDao.sumTodayGoodsStockInfoNumber();//获取当天总订单数
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        Integer number = todayOrderNumber+1;
        String no = number+"";
        if(number<10){
            no = "00"+number;
        }else if(number>=10&&number<100){
            no = "0"+number;
        }

        String orderNumber = dateNowStr+"S"+no;//订单编号
        goodsStockInfo.setOrderNumber(orderNumber);
        if((file != null) && (file.getSize() > 0L)){
            String imgName = FileUtil.fileVerify(file,".jpg .png .jpeg");
            goodsStockInfo.setImage(imgName);
        }
        if(iGoodsStockInfoService.save(goodsStockInfo)){
            return ResponseResult.successResult(goodsStockInfo,request);
        }
        return ResponseResult.failResult("提交失败");
    }

    @RequestMapping(value = "/goodsStockInfo/submitGoodsStockInfoDetailIng")
    @ResponseBody
    @ApiOperation(value = "提交(入库/出库/盘点)详情")
    public ResponseResult submitGoodsStockInfoDetailIng(Model model,HttpServletRequest request,@RequestBody List<GoodsStockInfoDetail> goodsStockInfoDetailList,@RequestParam(value = "mode", required = false) Integer mode){
        List<Integer> goodsIds = new ArrayList<>();
        for (GoodsStockInfoDetail goodsStockInfoDetail: goodsStockInfoDetailList) {
            if(!goodsIds.contains(goodsStockInfoDetail.getGiid())){
                goodsIds.add(goodsStockInfoDetail.getGiid());
            }
        }
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.listByIds(goodsIds);
        List<GoodsInfo> updateGoodsInfoList = new ArrayList<>();

        for (GoodsInfo goodsInfo: goodsInfoList) {
            for (GoodsStockInfoDetail goodsStockInfoDetail: goodsStockInfoDetailList) {
                if(goodsInfo.getId()==goodsStockInfoDetail.getGiid()){
                    Integer beforeStock = goodsInfo.getStock();//主单位库存
                    if(goodsStockInfoDetail.getUnitType()==2){
                        beforeStock = goodsInfo.getStockSe();//辅单位库存
                    }
                    goodsStockInfoDetail.setBeforeNumber(beforeStock);
                    Integer curStock = goodsStockInfoDetail.getNumber();//当前录入库存
                    Integer afterStock = 0;//计算之后库存
                    //入库计算
                    if(goodsStockInfoDetail.getType()==1){
                        afterStock = beforeStock+curStock;
                    }
                    //出库计算
                    if(goodsStockInfoDetail.getType()==2){
                        afterStock = beforeStock-curStock;
                    }
                    GoodsInfo afterGoodsInfo = new GoodsInfo();
                    afterGoodsInfo.setId(goodsStockInfoDetail.getGiid());
                    if(goodsStockInfoDetail.getUnitType()==1){
                        afterGoodsInfo.setStock(afterStock);
                    }
                    if(goodsStockInfoDetail.getUnitType()==2){
                        afterGoodsInfo.setStockSe(afterStock);
                    }
                    updateGoodsInfoList.add(afterGoodsInfo);
                }
            }
        }
        if(iGoodsStockInfoDetailService.saveBatch(goodsStockInfoDetailList)){

            //如果是入库、出库则更新商品库存值
            if(mode==1||mode==2){
                if(iGoodsInfoService.updateBatchById(updateGoodsInfoList)){
                    return ResponseResult.successResult(goodsStockInfoDetailList,request);
                };
            }else{
                return ResponseResult.successResult(goodsStockInfoDetailList,request);
            }
        };
        return ResponseResult.failResult("提交失败");
    }

    @RequestMapping(value = "/goodsStockInfo/goodsStockDetail")
    @ApiOperation(value = "商品(入库/出库/盘点)详细")
    public String goodsStockDetail(Model model, HttpServletRequest request,Integer id){
        GoodsStockInfo goodsStockInfo = iGoodsStockInfoService.getById(id);
        List<GoodsStockInfoDetail> goodsStockInfoDetailList = iGoodsStockInfoDetailService.findByGsiid(goodsStockInfo.getId());
        AdminInfo adminInfo = iAdminInfoService.getById(goodsStockInfo.getAiid());
        goodsStockInfo.setAdminName(adminInfo.getNikeName());
        model.addAttribute("goodsStockInfo",goodsStockInfo);
        model.addAttribute("goodsStockInfoDetailList",goodsStockInfoDetailList);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        return "/admin/goodsStockInfo/goods_stock_detail";
    }

}

