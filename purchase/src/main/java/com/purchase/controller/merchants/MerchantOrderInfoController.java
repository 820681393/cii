package com.purchase.controller.merchants;


import com.github.pagehelper.PageInfo;
import com.purchase.config.SystemConfig;
import com.purchase.dao.IMerchantDeliverInfoDao;
import com.purchase.dao.IMerchantOrderInfoDao;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.FileUtil;
import com.purchase.utils.MyDateUtil;
import com.purchase.utils.ResponseResult;
import com.purchase.utils.StringUilts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商户订单信息 前端控制器
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Controller
@RequestMapping("/merchants")
@Api(value = "merchantOrderInfo", tags="商户系统权限：订单信息权限")
public class MerchantOrderInfoController {
    @Autowired
    SystemConfig systemConfig;

    @Autowired
    IGoodsInfoService iGoodsInfoService;

    @Autowired
    IGoodsOneTypeService iGoodsOneTypeService;

    @Autowired
    IGoodsTowTypeService iGoodsTowTypeService;

    @Autowired
    ISupplierInfoService iSupplierInfoService;

    @Autowired
    IAdminInfoService iAdminInfoService;

    @Autowired
    IUnitInfoService iUnitInfoService;

    @Autowired
    IMerchantInfoService iMerchantInfoService;

    @Autowired
    IMerchantToAdminService iMerchantToAdminService;

    @Autowired
    IMerchantInfoAddressService iMerchantInfoAddressService;

    @Autowired
    IMerchantOrderInfoService iMerchantOrderInfoService;

    @Autowired
    IOrderInfoService iOrderInfoService;

    @Autowired
    IMerchantOrderInfoDetailService iMerchantOrderInfoDetailService;

    @Autowired
    IMerchantOrderInfoDao iMerchantOrderInfoDao;

    @Autowired
    IOrderInfoImagesService iOrderInfoImagesService;

    @Autowired
    IOrderInfoDetailService iOrderInfoDetailService;

    @Autowired
    INoticeInfoService iNoticeInfoService;

    @Autowired
    IMerchantUserInfoService iMerchantUserInfoService;

    @Autowired
    IMerchantDeliverInfoService iMerchantDeliverInfoService;

    @Autowired
    IMerchantDeliverInfoDao iMerchantDeliverInfoDao;

    @RequestMapping(value = "/orderInfo/submitIndex")
    @ApiOperation(value = "商户系统下单")
    public String submitIndex(Model model, HttpServletRequest request){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        List<GoodsInfo> goodsInfoList =iGoodsInfoService.findByState(1);
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.findByState(1);
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList=iSupplierInfoService.list();
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();
        MerchantInfo merchantInfo = iMerchantInfoService.findByAiid(userAdmin.getId());
        if(merchantInfo==null){
            merchantInfo = new MerchantInfo();
        }
        List<MerchantToAdmin> merchantToAdmins = iMerchantToAdminService.findByMiid(userAdmin.getId());
        List<MerchantInfoAddress> merchantInfoAddresses = iMerchantInfoAddressService.findByMiid(userAdmin.getId());
        List<NoticeInfo> noticeInfoList = iNoticeInfoService.list();
        List<MerchantUserInfo> merchantUserInfoList = iMerchantUserInfoService.findByAiid(userAdmin.getId());
        for (GoodsInfo goodsInfo: goodsInfoList) {
            if(goodsInfo.getTradePrice()==null){
                BigDecimal price = new BigDecimal(0);
                goodsInfo.setTradePrice(price);
            }
            if(goodsInfo.getTradePriceSe()==null){
                BigDecimal price = new BigDecimal(0);
                goodsInfo.setTradePriceSe(price);
            }
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
        for (MerchantToAdmin merchantToAdmin : merchantToAdmins){
            for(AdminInfo adminInfo:adminInfoList){
                if(merchantToAdmin.getAiid().equals(adminInfo.getId())){
                    merchantToAdmin.setAdminName(adminInfo.getNikeName());
                    merchantToAdmin.setAdminTel(adminInfo.getTel());
                }
            }
        }

        List<MerchantOrderInfo> orderInfoListTop = new ArrayList<>();
        if(merchantInfo!=null){
            List<MerchantOrderInfo> orderInfoList = iMerchantOrderInfoDao.orderInfoListByMonthAndMiid(merchantInfo.getId());
            int index = 0;
            for (MerchantOrderInfo orderInfo :orderInfoList){
                if(index>=3){
                    break;
                }
                orderInfoListTop.add(orderInfo);
                index++;
            }
        }

        model.addAttribute("goodsInfoList",goodsInfoList);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("adminInfoList",merchantToAdmins);
        model.addAttribute("merchantInfo",merchantInfo);
        model.addAttribute("merchantInfoAddresses",merchantInfoAddresses);
        model.addAttribute("orderInfoListTop",orderInfoListTop);
        model.addAttribute("noticeInfoList",noticeInfoList);
        model.addAttribute("merchantUserInfoList",merchantUserInfoList);

        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        return "/merchants/merchantOrderInfo/merchant_order_submit_index";
    }

    @RequestMapping(value = "/orderInfo/submitIndexIng")
    @ResponseBody
    @ApiOperation(value = "商户系统提交下单")
    public ResponseResult submitIndexIng(Model model, HttpServletRequest request, MerchantOrderInfo merchantOrderInfo) throws ParseException {
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        MerchantInfo merchantInfo = iMerchantInfoService.findByAiid(userAdmin.getId());
        merchantOrderInfo.setMiid(merchantInfo.getId());
        merchantOrderInfo.setMerchantName(userAdmin.getNikeName());
        merchantOrderInfo.setState(1);
        Integer todayOrderNumber = iMerchantOrderInfoDao.sumTodayOrderNumber();//获取当天总订单数
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        Integer number = todayOrderNumber+1;
        String no = number+"";
        if(number<10){
            no = "00"+number;
        }else if(number>=10&&number<100){
            no = "0"+number;
        }

        String orderNumber = dateNowStr+"C"+no;//订单编号
        merchantOrderInfo.setOrderNumber(orderNumber);

        Integer miid = merchantOrderInfo.getMiid();
        Integer miaid = merchantOrderInfo.getMiaid();

        //根据商户ID、商户地址查询商户今日是否生成过配送单，将当前商户订单标记到配送单
        Date date = MyDateUtil.getTodayDate();
        MerchantDeliverInfo merchantDeliverInfo = iMerchantDeliverInfoService.findByMiidAndMiaidAndCreateTimeGreaterThan(miid,miaid,date);
        if(merchantDeliverInfo==null){
            Integer dnumber = iMerchantDeliverInfoDao.sumTodayOrderNumber()+1;
            String dno = dnumber+"";
            if(dnumber<10){
                dno = "00"+dnumber;
            }else if(dnumber>=10&&dnumber<100){
                dno = "0"+dnumber;
            }

            String dorderNumber = dateNowStr+"D"+dno;//订单编号
            MerchantDeliverInfo addMerchantDeliverInfo = new MerchantDeliverInfo();
            addMerchantDeliverInfo.setOrderNumber(dorderNumber);
            addMerchantDeliverInfo.setMiid(merchantOrderInfo.getMiid());
            addMerchantDeliverInfo.setMerchantName(merchantOrderInfo.getMerchantName());
            addMerchantDeliverInfo.setMiaid(merchantOrderInfo.getMiaid());
            addMerchantDeliverInfo.setState(1);
            addMerchantDeliverInfo.setSumPrice(merchantOrderInfo.getSumPrice());
            addMerchantDeliverInfo.setSettleType(merchantInfo.getSettlementMethod());
            if(iMerchantDeliverInfoService.save(addMerchantDeliverInfo)){
                merchantOrderInfo.setMdiid(addMerchantDeliverInfo.getId());
            };
        }else{
            BigDecimal sumPrice = merchantDeliverInfo.getSumPrice();
            MerchantDeliverInfo updateMerchantDeliverInfo = new MerchantDeliverInfo();
            updateMerchantDeliverInfo.setId(merchantDeliverInfo.getId());
            updateMerchantDeliverInfo.setSumPrice(sumPrice.add(merchantOrderInfo.getSumPrice()));
            iMerchantDeliverInfoService.updateById(updateMerchantDeliverInfo);

            merchantOrderInfo.setMdiid(merchantDeliverInfo.getId());
        }
        if(iMerchantOrderInfoService.save(merchantOrderInfo)){
            return ResponseResult.successResult(merchantOrderInfo,request);
        }
        return ResponseResult.failResult("下单失败");
    }

    @RequestMapping(value = "/orderInfo/submitOrderDetailIng")
    @ResponseBody
    @ApiOperation(value = "商户系统提交订单详情")
    public ResponseResult submitOrderDetailIng(Model model,HttpServletRequest request,@RequestBody List<MerchantOrderInfoDetail> merchantOrderInfoDetails){
//        String goodsIds = "";
        List<Integer> goodsIds =new ArrayList<>();
        for(MerchantOrderInfoDetail merchantOrderInfoDetail : merchantOrderInfoDetails){
            Integer goodsId = merchantOrderInfoDetail.getGiid();
            goodsIds.add(goodsId);
        }
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.listByIds(goodsIds);
        //循环查出商品的利润率、额外费用，算出商品零售价
        for(MerchantOrderInfoDetail merchantOrderInfoDetail : merchantOrderInfoDetails){
            for(GoodsInfo goodsInfo : goodsInfoList){
                merchantOrderInfoDetail.setGoodsName(goodsInfo.getChName()+"<br/>"+goodsInfo.getEnName());
                if(merchantOrderInfoDetail.getGiid()==goodsInfo.getId()){
                    merchantOrderInfoDetail.setSellPrice(merchantOrderInfoDetail.getTotalPrice());//默认零售价为商品总价

                    if(goodsInfo.getPercentage()!=null){
                        merchantOrderInfoDetail.setPercentage(goodsInfo.getPercentage());
                    }
                    if(goodsInfo.getExtraCosts()!=null){
                        merchantOrderInfoDetail.setExtraCosts(goodsInfo.getExtraCosts());
                    }
                    if(merchantOrderInfoDetail.getPercentage()!=null){
                        BigDecimal retailPrice = merchantOrderInfoDetail.getSellPrice();
                        BigDecimal percentage = merchantOrderInfoDetail.getPercentage();
                        BigDecimal otherFee = retailPrice.multiply(percentage);
                        merchantOrderInfoDetail.setSellPrice(retailPrice.add(otherFee));
                    }
                    if(merchantOrderInfoDetail.getExtraCosts()!=null){
                        BigDecimal retailPrice = merchantOrderInfoDetail.getSellPrice();
                        BigDecimal extraCosts = merchantOrderInfoDetail.getExtraCosts();
                        merchantOrderInfoDetail.setSellPrice(retailPrice.add(extraCosts));
                    }
                    break;
                }
            }
        }
        if(iMerchantOrderInfoDetailService.saveBatch(merchantOrderInfoDetails)){
            return ResponseResult.successResult(merchantOrderInfoDetails,request);
        };
        return ResponseResult.failResult("下单失败");
    }

    @RequestMapping(value = "/orderInfo/index")
    @ApiOperation(value = "商户系统订单查询")
    public String index(Model model,HttpServletRequest request,MerchantOrderInfo orderInfo){
        if(orderInfo.getPageNum()==null){
            orderInfo.setPageNum(0);
            orderInfo.setPageSize(15);
        }

        PageInfo<MerchantOrderInfo> pageInfos = iMerchantOrderInfoService.selectMerchantOrderInfoPageInfo(orderInfo);
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        List<AdminInfo> adminInfoList=iAdminInfoService.list();

        model.addAttribute("pageInfos",pageInfos);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("adminInfoList",adminInfoList);
        model.addAttribute("userAdmin",userAdmin);
        return "/admin/orderInfo/merchant_order_info_index";
    }

    @RequestMapping(value = "/orderInfo/merchantOrderIndex")
    @ApiOperation(value = "商户：订单查询")
    public String merchantOrderIndex(Model model,HttpServletRequest request,MerchantOrderInfo orderInfo){
        if(orderInfo.getPageNum()==null){
            orderInfo.setPageNum(0);
            orderInfo.setPageSize(15);
        }
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        MerchantInfo merchantInfo = iMerchantInfoService.findByAiid(userAdmin.getId());
        if(merchantInfo!=null){
            orderInfo.setMiid(merchantInfo.getId());
        }
        PageInfo<MerchantOrderInfo> pageInfos = iMerchantOrderInfoService.selectMerchantOrderInfoPageInfo(orderInfo);
        List<AdminInfo> adminInfoList=iAdminInfoService.list();

        model.addAttribute("pageInfos",pageInfos);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("adminInfoList",adminInfoList);
        model.addAttribute("userAdmin",userAdmin);
        return "/merchants/merchantOrderInfo/merchant_order_info_index";
    }

    @RequestMapping(value = "/orderInfo/receiptUpdate")
    @ApiOperation(value = "商户进入订单回执")
    public String receiptUpdate(Model model,HttpServletRequest request,Integer id){
        MerchantOrderInfo orderInfo = iMerchantOrderInfoService.getById(id);
        MerchantInfo merchantInfo = iMerchantInfoService.getById(orderInfo.getMiid());
        List<MerchantOrderInfoDetail> orderInfoDetails = iMerchantOrderInfoDetailService.findByMoiid(id);
        List<GoodsInfo> goodsInfos = iGoodsInfoService.list();
        List<SupplierInfo> supplierInfoList = iSupplierInfoService.list();
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        List<UnitInfo> unitInfoList=iUnitInfoService.list();
        List<OrderInfoImages> orderInfoImagesList = iOrderInfoImagesService.findByOiidAndType(id,2);
        for (MerchantOrderInfoDetail orderInfoDetail: orderInfoDetails) {
            for (GoodsInfo goodsInfo: goodsInfos) {
                if(orderInfoDetail.getGiid().equals(goodsInfo.getId())){
                    orderInfoDetail.setGoodsInfo(goodsInfo);
                    break;
                }
            }
        }
        String rebateType = "无";
        BigDecimal rebateAmount = orderInfo.getSumPrice();
        if(merchantInfo.getRebateMethod()==1){
            rebateAmount = new BigDecimal(0);
        }
        if(merchantInfo.getRebateMethod()==2){
            rebateType = "数值(每单+"+merchantInfo.getRebateNumber()+")";
            rebateAmount = rebateAmount.add(merchantInfo.getRebateNumber());
        }
        if(merchantInfo.getRebateMethod()==3){
            rebateType = "百分比(每单*"+merchantInfo.getRebatePercentage()+")";
            rebateAmount = rebateAmount.add(rebateAmount.multiply(merchantInfo.getRebatePercentage()));
        }
        model.addAttribute("merchantInfo",merchantInfo);
        model.addAttribute("rebateType",rebateType);
        model.addAttribute("rebateAmount",rebateAmount);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("orderInfoDetails",orderInfoDetails);
        model.addAttribute("orderInfoImagesList",orderInfoImagesList);
        model.addAttribute("unitInfoList",unitInfoList);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        return "/admin/orderInfo/merchant_order_info_receipt";
    }

    @RequestMapping(value = "/orderInfo/receiptOrderInfo")
    @ResponseBody
    @ApiOperation(value = "商户订单回执")
    public ResponseResult receiptOrderInfo(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile[] file, MerchantOrderInfo orderInfo){
        if(file.length>0){
            AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
            orderInfo.setState(4);
            for (MultipartFile f:file) {
                String imgName = FileUtil.fileVerify(f,".jpg .png .jpeg");
                OrderInfoImages orderInfoImages = new OrderInfoImages();
                orderInfoImages.setImageName(imgName);
                orderInfoImages.setOiid(orderInfo.getId());
                orderInfoImages.setType(2);
                iOrderInfoImagesService.save(orderInfoImages);
            }
            iMerchantOrderInfoService.updateById(orderInfo);
            return ResponseResult.successResult("录入成功",request);
        }
        return ResponseResult.failResult("录入失败");
    }

    @RequestMapping(value = "/orderInfo/receiptOrderDetail")
    @ResponseBody
    @ApiOperation(value = "商户订单回执详情")
    public ResponseResult receiptOrderDetail(Model model,HttpServletRequest request,@RequestBody List<MerchantOrderInfoDetail> orderInfoDetails){
        if(iMerchantOrderInfoDetailService.updateBatchById(orderInfoDetails)){
            return ResponseResult.successResult(orderInfoDetails,request);
        };
        return ResponseResult.failResult("录入失败");
    }

    @RequestMapping(value = "/orderInfo/orderInfoMerge")
    @ResponseBody
    @ApiOperation(value = "商户订单合并到采购单")
    public ResponseResult orderInfoMerge(Model model,HttpServletRequest request,String orderIds,String orderNumber){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        if(StringUilts.isEmptyOrNull(orderNumber)){
            return ResponseResult.failResult("采购单号不能为空");
        }
        OrderInfo mergeToOrderInfo = iOrderInfoService.findByOrderNumber(orderNumber);
        if(mergeToOrderInfo==null){
            return ResponseResult.failResult("采购单号不存在");
        }
        if(mergeToOrderInfo.getState()!=1){
            return ResponseResult.failResult("只能合并到采购中的订单");
        }
        OrderInfo updateOrderInfo = new OrderInfo();
        updateOrderInfo.setId(mergeToOrderInfo.getId());
        updateOrderInfo.setSumPrice(mergeToOrderInfo.getSumPrice());
        updateOrderInfo.setSumNumber(mergeToOrderInfo.getSumNumber());

        List<OrderInfoDetail> mergeToOrderInfoDetailList = iOrderInfoDetailService.findByOiid(mergeToOrderInfo.getId());

        List<String> orderIdList = Arrays.asList(orderIds.split(","));
        List<MerchantOrderInfo> orderInfos = iMerchantOrderInfoService.listByIds(orderIdList);
        List<MerchantOrderInfo> updateMerchantOrderInfoList = new ArrayList<>();
        List<OrderInfoDetail> addOrderInfoDetailList = new ArrayList<>();
        List<OrderInfoDetail> updateOrderInfoDetailList = new ArrayList<>();
        for (MerchantOrderInfo orderInfo:orderInfos){
            Integer id = orderInfo.getId();
            MerchantOrderInfo updateMerchantOrderInfo = new MerchantOrderInfo();
            updateMerchantOrderInfo.setId(id);
            updateMerchantOrderInfo.setState(2);
//            updateMerchantOrderInfo.setSuid(userAdmin.getId());
//            updateMerchantOrderInfo.setOiOrderNumber(orderNumber);
            updateMerchantOrderInfoList.add(updateMerchantOrderInfo);

            List<MerchantOrderInfoDetail> orderInfoDetailList = iMerchantOrderInfoDetailService.findByMoiid(id);
            for (MerchantOrderInfoDetail orderInfoDetail:orderInfoDetailList) {
                BigDecimal totalPrice = orderInfoDetail.getTotalPrice();
                Integer number = orderInfoDetail.getNumber();

                updateOrderInfo.setSumPrice(updateOrderInfo.getSumPrice().add(totalPrice));
                updateOrderInfo.setSumNumber(updateOrderInfo.getSumNumber()+(number));

                OrderInfoDetail addOrderInfoDetail = new OrderInfoDetail();
                addOrderInfoDetail.setOiid(mergeToOrderInfo.getId());
                addOrderInfoDetail.setGiid(orderInfoDetail.getGiid());
                addOrderInfoDetail.setPrice(orderInfoDetail.getPrice());
                addOrderInfoDetail.setUnit(orderInfoDetail.getUnit());
                addOrderInfoDetail.setUnitType(orderInfoDetail.getUnitType());
                addOrderInfoDetail.setTotalPrice(orderInfoDetail.getTotalPrice());
                addOrderInfoDetail.setNumber(orderInfoDetail.getNumber());

                boolean addFlag = true;
                //如果采购单里没有对应商品则新增，否则累加数量跟价格
                for (OrderInfoDetail orderInfoDetail1 : mergeToOrderInfoDetailList) {
                    if (orderInfoDetail.getGiid().equals(orderInfoDetail1.getGiid())
                            && orderInfoDetail.getUnitType().equals(orderInfoDetail1.getUnitType())) {
                        addFlag = false;
                        Integer addGoodsNumber = orderInfoDetail1.getNumber();
                        BigDecimal addTotalPrice = orderInfoDetail1.getTotalPrice();
                        OrderInfoDetail updateOrderInfoDetail = new OrderInfoDetail();
                        updateOrderInfoDetail.setId(orderInfoDetail1.getId());
                        updateOrderInfoDetail.setNumber(addGoodsNumber+(number));
                        updateOrderInfoDetail.setTotalPrice(addTotalPrice.add(totalPrice));
                        updateOrderInfoDetailList.add(updateOrderInfoDetail);
                        break;
                    }
                }

                if (addFlag) {
                    addOrderInfoDetailList.add(addOrderInfoDetail);
                }
            }
        }

        //更新采购单信息
        if(iOrderInfoService.updateById(updateOrderInfo)){
            iOrderInfoDetailService.saveBatch(addOrderInfoDetailList);
            iOrderInfoDetailService.updateBatchById(updateOrderInfoDetailList);
            iMerchantOrderInfoService.updateBatchById(updateMerchantOrderInfoList);
            return ResponseResult.successResult("合并成功",request);
        }
        return ResponseResult.failResult("合并失败");
    }

    @RequestMapping(value = "/orderInfo/orderInfoDetailByOiid")
    @ResponseBody
    @ApiOperation(value = "根据订单ID获取订单详细")
    public ResponseResult orderInfoDetailByOiid(Model model,HttpServletRequest request,Integer oiid){
        if(oiid==null){
            return ResponseResult.failResult("oiid不能为空");
        }
        List<MerchantOrderInfoDetail> orderInfoDetailList = iMerchantOrderInfoDetailService.findByMoiid(oiid);
        return ResponseResult.successResult(orderInfoDetailList,request);
    }

    @RequestMapping(value = "/orderInfo/onOrderInfoDetail")
    @ApiOperation(value = "进入订单详细")
    public String onOrderInfoDetail(Model model,HttpServletRequest request,Integer id){
        MerchantOrderInfo orderInfo = iMerchantOrderInfoService.getById(id);
        List<MerchantOrderInfoDetail> orderInfoDetails = iMerchantOrderInfoDetailService.findByMoiid(id);
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.list();

        for(MerchantOrderInfoDetail orderInfoDetail:orderInfoDetails){
            for(GoodsInfo goodsInfo:goodsInfoList){
                if(orderInfoDetail.getGiid().equals(goodsInfo.getId())){
                    orderInfoDetail.setGoodsInfo(goodsInfo);
                }
            }
        }

        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("orderInfoDetails",orderInfoDetails);
        return "/merchants/merchantOrderInfo/merchant_order_info_detail";
    }
}

