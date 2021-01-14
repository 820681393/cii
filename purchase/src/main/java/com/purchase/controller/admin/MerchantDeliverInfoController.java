package com.purchase.controller.admin;


import com.github.pagehelper.PageInfo;
import com.purchase.config.SystemConfig;
import com.purchase.dao.IGoodsStockInfoDao;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.FileUtil;
import com.purchase.utils.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商户订单详细信息 前端控制器
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Controller
@RequestMapping("admin")
public class MerchantDeliverInfoController {
    @Autowired
    SystemConfig systemConfig;

    @Autowired
    IMerchantDeliverInfoService iMerchantDeliverInfoService;

    @Autowired
    IMerchantInfoService iMerchantInfoService;

    @Autowired
    IMerchantOrderInfoService iMerchantOrderInfoService;

    @Autowired
    IMerchantOrderInfoDetailService iMerchantOrderInfoDetailService;

    @Autowired
    IGoodsInfoService iGoodsInfoService;

    @Autowired
    IGoodsStockInfoService iGoodsStockInfoService;

    @Autowired
    IGoodsStockInfoDetailService iGoodsStockInfoDetailService;

    @Autowired
    IMerchantInfoAddressService iMerchantInfoAddressService;

    @Autowired
    IGoodsStockInfoDao iGoodsStockInfoDao;

    @RequestMapping(value = "/merchantDeliverInfo/index")
    @ApiOperation(value = "商户配送订单查询")
    public String index(Model model, HttpServletRequest request, MerchantDeliverInfo merchantDeliverInfo) {
        if (merchantDeliverInfo.getPageNum() == null) {
            merchantDeliverInfo.setPageNum(0);
            merchantDeliverInfo.setPageSize(15);
        }
        PageInfo<MerchantDeliverInfo> pageInfos = iMerchantDeliverInfoService.selectMerchantDeliverInfoPageInfo(merchantDeliverInfo);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("pageInfos", pageInfos);
        model.addAttribute("merchantDeliverInfo", merchantDeliverInfo);
        return "/admin/merchantDeliverInfo/merchant_deliver_info_index";
    }

    @RequestMapping(value = "/merchantDeliverInfo/merchantDeliverStockComparison")
    @ApiOperation(value = "商户配送订单库存比对")
    public String merchantDeliverStockComparison(Model model, HttpServletRequest request, Integer id) {
        MerchantDeliverInfo merchantDeliverInfo = iMerchantDeliverInfoService.getById(id);
        List<MerchantOrderInfo> merchantOrderInfoList = iMerchantOrderInfoService.findByMdiid(merchantDeliverInfo.getId());
        List<MerchantOrderInfoDetail> showMerchantOrderInfoDetailList = new ArrayList<>();
        for (MerchantOrderInfo merchantOrderInfo : merchantOrderInfoList) {
            List<MerchantOrderInfoDetail> merchantOrderInfoDetailList = iMerchantOrderInfoDetailService.findByMoiid(merchantOrderInfo.getId());
            for (MerchantOrderInfoDetail merchantOrderInfoDetail : merchantOrderInfoDetailList) {
                boolean addFlag = true;
                //将商品、单位都相同的商品订单合并成一个订单
                for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
                    if (showMerchantOrderInfoDetail.getGiid().equals(merchantOrderInfoDetail.getGiid())
                            && showMerchantOrderInfoDetail.getUnitType().equals(merchantOrderInfoDetail.getUnitType())) {
                        Integer beforeNumber = showMerchantOrderInfoDetail.getNumber();
                        BigDecimal beforeTotalPrice = showMerchantOrderInfoDetail.getTotalPrice();
                        showMerchantOrderInfoDetail.setNumber(beforeNumber+merchantOrderInfoDetail.getNumber());
                        showMerchantOrderInfoDetail.setTotalPrice(beforeTotalPrice.add(merchantOrderInfoDetail.getTotalPrice()));

                        String beforeMergeOrderDetailId = showMerchantOrderInfoDetail.getMergeOrderDetailId();
                        showMerchantOrderInfoDetail.setMergeOrderDetailId(beforeMergeOrderDetailId + "," + merchantOrderInfoDetail.getId());
                        addFlag = false;
                        break;
                    }
                }
                if (addFlag) {
                    merchantOrderInfoDetail.setMergeOrderDetailId(merchantOrderInfoDetail.getId() + "");
                    showMerchantOrderInfoDetailList.add(merchantOrderInfoDetail);
                }
            }
        }

        List<Integer> goodsIds = new ArrayList<>();
        for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
            goodsIds.add(showMerchantOrderInfoDetail.getGiid());
        }
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.listByIds(goodsIds);
        for (GoodsInfo goodsInfo : goodsInfoList) {
            for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
                if (goodsInfo.getId() == showMerchantOrderInfoDetail.getGiid()) {
                    if (showMerchantOrderInfoDetail.getUnitType() == 1) {
                        showMerchantOrderInfoDetail.setGoodsStock(goodsInfo.getStock());
                    } else if (showMerchantOrderInfoDetail.getUnitType() == 2) {
                        showMerchantOrderInfoDetail.setGoodsStock(goodsInfo.getStockSe());
                    }
                }
            }
        }


        model.addAttribute("merchantDeliverInfo", merchantDeliverInfo);
        model.addAttribute("showMerchantOrderInfoDetailList", showMerchantOrderInfoDetailList);
        return "/admin/merchantDeliverInfo/merchant_deliver_stock_comparison";
    }

    @RequestMapping(value = "/merchantDeliverInfo/merchantDeliverInfoConfirm")
    @ResponseBody
    @ApiOperation(value = "商户配送订单确认")
    public ResponseResult merchantDeliverInfoConfirm(Model model, HttpServletRequest request, Integer id,String optStr) {
        String[] optStrList = optStr.split(";");
        List<MerchantOrderInfoDetail> merchantOrderInfoDetailList = new ArrayList<>();
        List<Integer> moiidList = new ArrayList<>();
        for(int i = 0;i<optStrList.length;i++){
            String str = optStrList[i];
            String idsStr = str.substring(0,str.indexOf(":"));
            String state = str.substring(str.indexOf(":")+1);

            String[] idList = idsStr.split(",");
            for(int j = 0;j<idList.length;j++){
                MerchantOrderInfoDetail merchantOrderInfoDetail = new MerchantOrderInfoDetail();
                merchantOrderInfoDetail.setId(Integer.valueOf(idList[j]));
                merchantOrderInfoDetail.setState(Integer.valueOf(state));
                merchantOrderInfoDetailList.add(merchantOrderInfoDetail);
                moiidList.add(Integer.valueOf(idList[j]));
            }
        }
        iMerchantOrderInfoDetailService.updateBatchById(merchantOrderInfoDetailList);
        BigDecimal deliverPriceDiff = BigDecimal.ZERO;
        BigDecimal realDeliverPrice = BigDecimal.ZERO;
        List<MerchantOrderInfoDetail> merchantOrderInfoDetailList1 = iMerchantOrderInfoDetailService.listByIds(moiidList);
        for(MerchantOrderInfoDetail merchantOrderInfoDetail:merchantOrderInfoDetailList1){
            //计算配送差额、配送实际金额
            if(merchantOrderInfoDetail.getState()==4){
                deliverPriceDiff = deliverPriceDiff.add(merchantOrderInfoDetail.getSellPrice());
            }else{
                realDeliverPrice = realDeliverPrice.add(merchantOrderInfoDetail.getSellPrice());
            }
        }
        MerchantDeliverInfo merchantDeliverInfo = iMerchantDeliverInfoService.getById(id);
        MerchantDeliverInfo updateMerchantDeliverInfo = new MerchantDeliverInfo();
        updateMerchantDeliverInfo.setId(id);
        if(merchantDeliverInfo.getState()==1){
            updateMerchantDeliverInfo.setState(2);
        }
        updateMerchantDeliverInfo.setDeliverPriceDiff(deliverPriceDiff);
        updateMerchantDeliverInfo.setRealDeliverPrice(realDeliverPrice);
        if(iMerchantDeliverInfoService.updateById(updateMerchantDeliverInfo)){
            return ResponseResult.successResult(updateMerchantDeliverInfo,request);
        }
        return ResponseResult.failResult("操作失败");
    }

    @RequestMapping(value = "/merchantDeliverInfo/merchantDeliverPrintInfo")
    @ApiOperation(value = "商户配送订单打印信息")
    public String merchantDeliverPrintInfo(Model model, HttpServletRequest request, Integer id) {
        MerchantDeliverInfo merchantDeliverInfo = iMerchantDeliverInfoService.getById(id);
        List<MerchantOrderInfo> merchantOrderInfoList = iMerchantOrderInfoService.findByMdiid(merchantDeliverInfo.getId());
        List<MerchantOrderInfoDetail> showMerchantOrderInfoDetailList = new ArrayList<>();
        for (MerchantOrderInfo merchantOrderInfo : merchantOrderInfoList) {
            List<MerchantOrderInfoDetail> merchantOrderInfoDetailList = iMerchantOrderInfoDetailService.findByMoiid(merchantOrderInfo.getId());
            for (MerchantOrderInfoDetail merchantOrderInfoDetail : merchantOrderInfoDetailList) {
                boolean addFlag = true;
                //将商品、单位都相同的商品订单合并成一个订单
                for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
                    if (showMerchantOrderInfoDetail.getGiid().equals(merchantOrderInfoDetail.getGiid())
                            && showMerchantOrderInfoDetail.getUnitType().equals(merchantOrderInfoDetail.getUnitType())) {
                        Integer beforeNumber = showMerchantOrderInfoDetail.getNumber();
                        BigDecimal beforeTotalPrice = showMerchantOrderInfoDetail.getTotalPrice();
                        showMerchantOrderInfoDetail.setNumber(beforeNumber+(merchantOrderInfoDetail.getNumber()));
                        showMerchantOrderInfoDetail.setTotalPrice(beforeTotalPrice.add(merchantOrderInfoDetail.getTotalPrice()));

                        String beforeMergeOrderDetailId = showMerchantOrderInfoDetail.getMergeOrderDetailId();
                        showMerchantOrderInfoDetail.setMergeOrderDetailId(beforeMergeOrderDetailId + "," + merchantOrderInfoDetail.getId());
                        addFlag = false;
                        break;
                    }
                }
                if (addFlag) {
                    merchantOrderInfoDetail.setMergeOrderDetailId(merchantOrderInfoDetail.getId() + "");
                    showMerchantOrderInfoDetailList.add(merchantOrderInfoDetail);
                }
            }
        }


        MerchantInfoAddress merchantInfoAddress = iMerchantInfoAddressService.getById(merchantDeliverInfo.getMiaid());
        model.addAttribute("merchantDeliverInfo", merchantDeliverInfo);
        model.addAttribute("merchantInfoAddress", merchantInfoAddress);
        model.addAttribute("showMerchantOrderInfoDetailList", showMerchantOrderInfoDetailList);
        return "/admin/merchantDeliverInfo/merchant_deliver_print";
    }

    @RequestMapping(value = "/merchantDeliverInfo/merchantDeliverPrintInfoConfirm")
    @ResponseBody
    @ApiOperation(value = "商户配送订单打印确认")
    public ResponseResult merchantDeliverPrintInfoConfirm(Model model, HttpServletRequest request, Integer id) {
        MerchantDeliverInfo merchantDeliverInfo = iMerchantDeliverInfoService.getById(id);
        Integer totalNumber = 0;
        List<MerchantOrderInfo> merchantOrderInfoList = iMerchantOrderInfoService.findByMdiid(merchantDeliverInfo.getId());
        List<MerchantOrderInfoDetail> showMerchantOrderInfoDetailList = new ArrayList<>();
        for (MerchantOrderInfo merchantOrderInfo : merchantOrderInfoList) {
            List<MerchantOrderInfoDetail> merchantOrderInfoDetailList = iMerchantOrderInfoDetailService.findByMoiid(merchantOrderInfo.getId());
            for (MerchantOrderInfoDetail merchantOrderInfoDetail : merchantOrderInfoDetailList) {
                boolean addFlag = true;
                //将商品、单位都相同的商品订单合并成一个订单
                for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
                    if (showMerchantOrderInfoDetail.getGiid().equals(merchantOrderInfoDetail.getGiid())
                            && showMerchantOrderInfoDetail.getUnitType().equals(merchantOrderInfoDetail.getUnitType())) {
                        Integer beforeNumber = showMerchantOrderInfoDetail.getNumber();
                        totalNumber += beforeNumber;
                        BigDecimal beforeTotalPrice = showMerchantOrderInfoDetail.getTotalPrice();
                        showMerchantOrderInfoDetail.setNumber(beforeNumber+(merchantOrderInfoDetail.getNumber()));
                        showMerchantOrderInfoDetail.setTotalPrice(beforeTotalPrice.add(merchantOrderInfoDetail.getTotalPrice()));

                        String beforeMergeOrderDetailId = showMerchantOrderInfoDetail.getMergeOrderDetailId();
                        showMerchantOrderInfoDetail.setMergeOrderDetailId(beforeMergeOrderDetailId + "," + merchantOrderInfoDetail.getId());
                        addFlag = false;
                        break;
                    }
                }
                if (addFlag) {
                    merchantOrderInfoDetail.setMergeOrderDetailId(merchantOrderInfoDetail.getId() + "");
                    showMerchantOrderInfoDetailList.add(merchantOrderInfoDetail);
                }
            }
        }

        List<Integer> goodsIds = new ArrayList<>();
        for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
            goodsIds.add(showMerchantOrderInfoDetail.getGiid());
        }
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.listByIds(goodsIds);
        List<GoodsInfo> updateGoodsInfoList = new ArrayList<>();
        for (GoodsInfo goodsInfo : goodsInfoList) {
            for (MerchantOrderInfoDetail showMerchantOrderInfoDetail : showMerchantOrderInfoDetailList) {
                if (goodsInfo.getId() == showMerchantOrderInfoDetail.getGiid()) {
                    GoodsInfo updateGoodsInfo = new GoodsInfo();
                    updateGoodsInfo.setId(goodsInfo.getId());
                    if (showMerchantOrderInfoDetail.getUnitType() == 1) {
                        Integer curStock = goodsInfo.getStock();
                        Integer subNumber = showMerchantOrderInfoDetail.getNumber();
                        updateGoodsInfo.setStock(curStock-subNumber);

                        showMerchantOrderInfoDetail.setGoodsStock(goodsInfo.getStock());
                    } else if (showMerchantOrderInfoDetail.getUnitType() == 2) {
                        Integer curStock = goodsInfo.getStockSe();
                        Integer subNumber = showMerchantOrderInfoDetail.getNumber();
                        updateGoodsInfo.setStockSe(curStock-subNumber);

                        showMerchantOrderInfoDetail.setGoodsStock(goodsInfo.getStockSe());
                    }
                    updateGoodsInfoList.add(updateGoodsInfo);
                }
            }
        }
        MerchantDeliverInfo updateMerchantDeliverInfo = new MerchantDeliverInfo();
        updateMerchantDeliverInfo.setId(id);
        updateMerchantDeliverInfo.setState(3);
        if(iMerchantDeliverInfoService.updateById(updateMerchantDeliverInfo)){
            GoodsStockInfo goodsStockInfo = new GoodsStockInfo();
            AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
            goodsStockInfo.setAiid(userAdmin.getId());
            goodsStockInfo.setType(2);
            goodsStockInfo.setStockNumber(totalNumber);
            goodsStockInfo.setTypeName("销售出库");
            goodsStockInfo.setTotalPrice(merchantDeliverInfo.getRealDeliverPrice());

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
            if(iGoodsStockInfoService.save(goodsStockInfo)){
                List<GoodsStockInfoDetail> goodsStockInfoDetailList = new ArrayList<>();
                for(MerchantOrderInfoDetail merchantOrderInfoDetail :showMerchantOrderInfoDetailList){
                    GoodsStockInfoDetail goodsStockInfoDetail = new GoodsStockInfoDetail();
                    goodsStockInfoDetail.setGsiid(goodsStockInfo.getId());
                    goodsStockInfoDetail.setGiid(merchantOrderInfoDetail.getGiid());
                    goodsStockInfoDetail.setPrice(merchantOrderInfoDetail.getSellPrice());
                    goodsStockInfoDetail.setUnit(merchantOrderInfoDetail.getUnit());
                    goodsStockInfoDetail.setNumber(merchantOrderInfoDetail.getNumber());
                    goodsStockInfoDetail.setType(2);
                    goodsStockInfoDetail.setGoodsName(merchantOrderInfoDetail.getGoodsName());
                    goodsStockInfoDetail.setUnitType(merchantOrderInfoDetail.getUnitType());
                    goodsStockInfoDetail.setTotalPrice(merchantOrderInfoDetail.getTotalPrice());
                    goodsStockInfoDetail.setBeforeNumber(merchantOrderInfoDetail.getGoodsStock());
                    goodsStockInfoDetailList.add(goodsStockInfoDetail);
                }
                if(iGoodsStockInfoDetailService.saveBatch(goodsStockInfoDetailList)){
                    iGoodsInfoService.updateBatchById(updateGoodsInfoList);
                }
            }
            return ResponseResult.successResult(merchantDeliverInfo,request);
        }
        return ResponseResult.failResult("操作失败");
    }
    @RequestMapping(value = "/merchantDeliverInfo/merchantDeliverSign")
    @ApiOperation(value = "进入配送单签收")
    public String merchantDeliverSign(Model model, HttpServletRequest request,Integer id){
        MerchantDeliverInfo merchantDeliverInfo = iMerchantDeliverInfoService.getById(id);
        model.addAttribute("merchantDeliverInfo",merchantDeliverInfo);
        return "/admin/merchantDeliverInfo/merchant_deliver_sign";
    }

    @RequestMapping(value = "/merchantDeliverInfo/merchantDeliverSignIng")
    @ApiOperation(value = "配送单签收")
    public String merchantDeliverSignIng(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file, MerchantDeliverInfo merchantDeliverInfo){
        if((file != null) && (file.getSize() > 0L)){
            String imgName = FileUtil.fileVerify(file,".jpg .png .jpeg");
            merchantDeliverInfo.setImage(imgName);
        }
        iMerchantDeliverInfoService.updateById(merchantDeliverInfo);
        return "redirect:/admin/merchantDeliverInfo/index";
    }
}

