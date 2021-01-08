package com.purchase.controller.admin;

import com.github.pagehelper.PageInfo;
import com.purchase.common.log.MyLogger;
import com.purchase.config.DateConverter;
import com.purchase.config.SystemConfig;
import com.purchase.dao.IOrderInfoDao;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.AliyunOosUtil;
import com.purchase.utils.FileUtil;
import com.purchase.utils.MyDateUtil;
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
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Miracle
 * @date 2020/11/3 18:54
 */
@Controller
@RequestMapping("admin")
@Api(value = "orderInfo", tags="订单信息权限")
public class AdminOrderController {

    @Autowired
    IOrderInfoService iOrderInfoService;

    @Autowired
    IOrderInfoDetailService iOrderInfoDetailService;

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
    IAdminToRoleService iAdminToRoleService;

    @Autowired
    ICarInfoService iCarInfoService;

    @Autowired
    IUnitInfoService iUnitInfoService;

    @Autowired
    IOrderInfoImagesService iOrderInfoImagesService;

    @Autowired
    IOrderInfoDao iOrderInfoDao;

    @Autowired
    SystemConfig systemConfig;

    MyLogger myLogger=new MyLogger(this.getClass());

    @RequestMapping(value = "/orderInfo/submitIndex")
    @ApiOperation(value = "采购下单")
    public String submitIndex(Model model, HttpServletRequest request){
        List<GoodsInfo> goodsInfoList =iGoodsInfoService.list();
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList=iSupplierInfoService.list();
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        List<AdminToRole> adminToRoles = iAdminToRoleService.list();
        List<CarInfo> carInfoList=iCarInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();

        List<AdminInfo> caiGouAdminInfoList = new ArrayList<>();
        for(AdminInfo adminInfo:adminInfoList){
            for(AdminToRole adminToRole:adminToRoles){
                if(adminInfo.getId()==adminToRole.getAiid()&&adminToRole.getRiid()==9){
                    caiGouAdminInfoList.add(adminInfo);
                }
            }
        }

        List<CarInfo> availableCarInfoList = new ArrayList<>();
        String week=MyDateUtil.getWeek();
        for(CarInfo carInfo:carInfoList){
            if(carInfo.getState()==1&&!carInfo.getLimitDay().contains(week)){
                availableCarInfoList.add(carInfo);
            }
        }
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

        List<OrderInfo> orderInfoList = iOrderInfoDao.orderInfoListByMonth();
        List<OrderInfo> orderInfoListTop = new ArrayList<>();
        int index = 0;
        for (OrderInfo orderInfo :orderInfoList){
            if(index>=3){
                break;
            }
            orderInfoListTop.add(orderInfo);
            index++;
        }

        model.addAttribute("goodsInfoList",goodsInfoList);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("adminInfoList",caiGouAdminInfoList);
        model.addAttribute("carInfoList",availableCarInfoList);
        model.addAttribute("orderInfoListTop",orderInfoListTop);

        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        return "/admin/orderInfo/order_info_submit_index";
    }

    @RequestMapping(value = "/orderInfo/submitIndexIng")
    @ResponseBody
    @ApiOperation(value = "提交订单")
    public ResponseResult submitIndexIng(Model model,HttpServletRequest request,OrderInfo orderInfo){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        orderInfo.setXuid(userAdmin.getId());//下单人员ID为当前登录人ID
//        orderInfo.setState(1);//默认订单状态
        Integer todayOrderNumber = iOrderInfoDao.sumTodayOrderNumber();//获取当天总订单数
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

        String orderNumber = dateNowStr+"A"+no;//订单编号
        orderInfo.setOrderNumber(orderNumber);
        if(iOrderInfoService.save(orderInfo)){
            CarInfo carInfo = iCarInfoService.getById(orderInfo.getCiid());
            if(carInfo.getType()==null||carInfo.getType()!=2){
                CarInfo changeCarInfo = new CarInfo();
                changeCarInfo.setId(orderInfo.getCiid());
                changeCarInfo.setState(4);
                iCarInfoService.updateById(changeCarInfo);
            }
            try {
                orderInfo.setCreateTime(MyDateUtil.getPatternToDate(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return ResponseResult.successResult(orderInfo,request);
        };
        return ResponseResult.failResult("下单失败");
    }

    @RequestMapping(value = "/orderInfo/submitOrderDetailIng")
    @ResponseBody
    @ApiOperation(value = "提交订单详情")
    public ResponseResult submitOrderDetailIng(Model model,HttpServletRequest request,@RequestBody List<OrderInfoDetail> orderInfoDetails){
        if(iOrderInfoDetailService.saveBatch(orderInfoDetails)){
            return ResponseResult.successResult(orderInfoDetails,request);
        };
        return ResponseResult.failResult("下单失败");
    }

    @RequestMapping(value = "/orderInfo/index")
    @ApiOperation(value = "订单查询")
    public String index(Model model,HttpServletRequest request,OrderInfo orderInfo,@RequestParam(value = "orderState", required = false) Integer orderState){
        if(orderInfo.getPageNum()==null){
            orderInfo.setPageNum(0);
            orderInfo.setPageSize(15);
        }
        if(orderState!=null){
            if(orderState==1||orderState==2){
                orderInfo.setState(orderState);
            }
        }
        PageInfo<OrderInfo> pageInfos = iOrderInfoService.selectOrderInfoPageInfo(orderInfo);
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
//        List<OrderInfo> orderInfoList = iOrderInfoService.list();
//        List<SupplierInfo> supplierInfoList=iSupplierInfoService.list();
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        List<CarInfo> carInfoList=iCarInfoService.list();
        for (OrderInfo oInfo:pageInfos.getList()) {
            for (CarInfo carInfo:carInfoList) {
                if(oInfo.getCiid().equals(carInfo.getId())){
                    oInfo.setCarName(carInfo.getName());
                    break;
                }
            }
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
        List<CarInfo> availableCarInfoList = new ArrayList<>();
        String week=MyDateUtil.getWeek();
        for(CarInfo carInfo:carInfoList){
            if(carInfo.getState()==1&&!carInfo.getLimitDay().contains(week)){
                availableCarInfoList.add(carInfo);
            }
        }

        List<AdminToRole> adminToRoleList = iAdminToRoleService.findByAiid(userAdmin.getId());
        Integer delFlag = 0;
        for (AdminToRole adminToRole:adminToRoleList){
            if(adminToRole.getRiid().equals(1)||adminToRole.getRiid().equals(6)){
                delFlag = 1;
                break;
            }
        }

        model.addAttribute("pageInfos",pageInfos);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("orderInfo",orderInfo);
//        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("adminInfoList",adminInfoList);
        model.addAttribute("carInfoList",carInfoList);
        model.addAttribute("userAdmin",userAdmin);
        model.addAttribute("orderState",orderState);
        model.addAttribute("availableCarInfoList",availableCarInfoList);
        model.addAttribute("delFlag",delFlag);
        return "/admin/orderInfo/order_info_index";
    }

    @RequestMapping(value = "/orderInfo/receiptUpdate")
    @ApiOperation(value = "进入订单回执")
    public String receiptUpdate(Model model,HttpServletRequest request,Integer id){
        OrderInfo orderInfo = iOrderInfoService.getById(id);
        List<OrderInfoDetail> orderInfoDetails = iOrderInfoDetailService.findByOiid(id);
        List<GoodsInfo> goodsInfos = iGoodsInfoService.list();
        List<SupplierInfo> supplierInfoList = iSupplierInfoService.list();
        List<AdminInfo> adminInfoList=iAdminInfoService.list();
        List<UnitInfo> unitInfoList=iUnitInfoService.list();
        List<OrderInfoImages> orderInfoImagesList = iOrderInfoImagesService.findByOiidAndType(id,1);
        for (OrderInfoDetail orderInfoDetail: orderInfoDetails) {
            for (GoodsInfo goodsInfo: goodsInfos) {
                if(orderInfoDetail.getGiid().equals(goodsInfo.getId())){
                    orderInfoDetail.setGoodsInfo(goodsInfo);
                    break;
                }
            }
            for (SupplierInfo supplierInfo: supplierInfoList) {
                if(supplierInfo.getId().equals(orderInfoDetail.getSiid())){
                    orderInfoDetail.setSupplierName(supplierInfo.getName());
                }
            }
        }
        for (AdminInfo adminInfo:adminInfoList) {
            if(orderInfo.getXuid().equals(adminInfo.getId())){
                orderInfo.setXuName(adminInfo.getNikeName());
            }
            if(orderInfo.getSuid().equals(adminInfo.getId())){
                orderInfo.setSuName(adminInfo.getNikeName());
            }
            if(orderInfo.getHuid()!=null){
                if(orderInfo.getHuid().equals(adminInfo.getId())){
                    orderInfo.setHuName(adminInfo.getNikeName());
                }
            }
        }
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("orderInfoDetails",orderInfoDetails);
        model.addAttribute("orderInfoImagesList",orderInfoImagesList);
        model.addAttribute("unitInfoList",unitInfoList);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        return "/admin/orderInfo/order_info_receipt";
    }

    @RequestMapping(value = "/orderInfo/receiptOrderInfo")
    @ResponseBody
    @ApiOperation(value = "订单回执")
    public ResponseResult receiptOrderInfo(Model model, HttpServletRequest request,@RequestParam("file") MultipartFile[] file,OrderInfo orderInfo){
        if(file.length>0){
            AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
            orderInfo.setHuid(userAdmin.getId());
            orderInfo.setState(3);
            orderInfo.setSuccessTime(new Date());
            for (MultipartFile f:file) {
                String imgName = FileUtil.fileVerify(f,".jpg .png .jpeg");
                OrderInfoImages orderInfoImages = new OrderInfoImages();
                orderInfoImages.setImageName(imgName);
                orderInfoImages.setOiid(orderInfo.getId());
                orderInfoImages.setType(1);
                iOrderInfoImagesService.save(orderInfoImages);
            }
            iOrderInfoService.updateById(orderInfo);
            orderInfo = iOrderInfoService.getById(orderInfo.getId());
            CarInfo carInfo = new CarInfo();
            carInfo.setId(orderInfo.getCiid());
            carInfo.setState(1);//选中车辆改为可用;
            iCarInfoService.updateById(carInfo);
            return ResponseResult.successResult("录入成功",request);
        }
        return ResponseResult.failResult("录入失败");
    }

    @RequestMapping(value = "/orderInfo/receiptOrderDetail")
    @ResponseBody
    @ApiOperation(value = "订单回执详情")
    public ResponseResult receiptOrderDetail(Model model,HttpServletRequest request,@RequestBody List<OrderInfoDetail> orderInfoDetails){
        if(iOrderInfoDetailService.updateBatchById(orderInfoDetails)){
            return ResponseResult.successResult(orderInfoDetails,request);
        };
        return ResponseResult.failResult("录入失败");
    }

    @RequestMapping(value = "/orderInfo/todayOrderInfo")
    @ApiOperation(value = "今日采购单查询")
    public String todayOrderInfo(Model model,HttpServletRequest request){
        Calendar cal= Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date startDate = cal.getTime();

        Date endDate = new Date();
        Integer state = 2;
        List<AdminInfo> adminInfoList = iAdminInfoService.list();
        List<SupplierInfo> supplierInfoList = iSupplierInfoService.list();
        List<OrderInfo> orderInfoList = iOrderInfoService.findByCreateTimeGreaterThanAndCreateTimeLessThanAndState(startDate,endDate,state);
        List<OrderInfoDetail> orderInfoDetailList = iOrderInfoDetailService.findByCreateTimeGreaterThanAndCreateTimeLessThan(startDate,endDate);
        List<GoodsInfo> goodsInfos = iGoodsInfoService.list();

        for (OrderInfo orderInfo: orderInfoList) {
            for (OrderInfoDetail orderInfoDetail: orderInfoDetailList) {
                if(orderInfoDetail.getOiid().equals(orderInfo.getId())){
                    for (GoodsInfo goodsInfo: goodsInfos) {
                        if(orderInfoDetail.getGiid().equals(goodsInfo.getId())){
                            for (SupplierInfo supplierInfo: supplierInfoList) {
                                if(supplierInfo.getId().equals(goodsInfo.getSiid())){
                                    goodsInfo.setSupplierName(supplierInfo.getName());
                                    break;
                                }
                            }
                            orderInfoDetail.setGoodsInfo(goodsInfo);
                            break;
                        }
                    }
                    orderInfo.getOrderInfoDetailList().add(orderInfoDetail);
                }
            }
            for (AdminInfo adminInfo: adminInfoList) {
                if(adminInfo.getId().equals(orderInfo.getXuid())){
                    orderInfo.setXuName(adminInfo.getNikeName());
                }
                if(adminInfo.getId().equals(orderInfo.getSuid())){
                    orderInfo.setSuName(adminInfo.getNikeName());
                }
            }
//            for (SupplierInfo supplierInfo: supplierInfoList) {
//                if(supplierInfo.getId().equals(orderInfo.getSiid())){
//                    orderInfo.setSupplierName(supplierInfo.getName());
//                }
//            }
        }
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("orderInfoList",orderInfoList);
        return "/admin/orderInfo/order_info_today";
    }

    @RequestMapping(value = "/orderInfo/updateOrderInfo")
    @ResponseBody
    @ApiOperation(value = "修改订单")
    public ResponseResult updateOrderInfo(Model model, HttpServletRequest request,OrderInfo orderInfo){
        if(iOrderInfoService.updateById(orderInfo)){
            return ResponseResult.successResult(orderInfo,request);
        };
        return ResponseResult.failResult("修改失败");
    }

    @RequestMapping(value = "/orderInfo/delete")
    @ApiOperation(value = "删除订单")
    public String delete(Model model, HttpServletRequest request,Integer id){
        OrderInfo orderInfo = iOrderInfoService.getById(id);
        CarInfo carInfo = new CarInfo();
        carInfo.setId(orderInfo.getCiid());
        carInfo.setState(1);
        iCarInfoService.updateById(carInfo);
        iOrderInfoService.removeById(id);
        iOrderInfoDetailService.deleteByOiid(id);
        return "redirect:/admin/orderInfo/index";
    }

    @RequestMapping(value = "/orderInfo/orderInfoUpdate")
    @ApiOperation(value = "进入订单修改")
    public String orderInfoUpdate(Model model,HttpServletRequest request,Integer id){
        OrderInfo orderInfo = iOrderInfoService.getById(id);
        List<OrderInfoDetail> orderInfoDetails = iOrderInfoDetailService.findByOiid(id);
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.list();
        List<AdminInfo> adminInfoList = iAdminInfoService.list();
        List<SupplierInfo> supplierInfoList = iSupplierInfoService.list();
        List<CarInfo> carInfoList = iCarInfoService.list();
        List<CarInfo> availableCarInfoList = new ArrayList<>();
        String week=MyDateUtil.getWeek();
        for(CarInfo carInfo:carInfoList){
            if(!carInfo.getLimitDay().contains(week)){
                availableCarInfoList.add(carInfo);
            }
        }

        for(OrderInfoDetail orderInfoDetail:orderInfoDetails){
            for(GoodsInfo goodsInfo:goodsInfoList){
                if(orderInfoDetail.getGiid().equals(goodsInfo.getId())){
                    orderInfoDetail.setGoodsInfo(goodsInfo);
                }
            }
            for(SupplierInfo supplierInfo:supplierInfoList){
                if(orderInfoDetail.getSiid().equals(supplierInfo.getId())){
                    orderInfoDetail.setSupplierName(supplierInfo.getName());
                    orderInfoDetail.setSupplierAddress(supplierInfo.getAddress());
                }
            }
        }

        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("orderInfoDetails",orderInfoDetails);
        model.addAttribute("adminInfoList",adminInfoList);
        model.addAttribute("carInfoList",availableCarInfoList);
        return "/admin/orderInfo/order_info_update";
    }

    @RequestMapping(value = "/orderInfo/orderInfoUpdateIng")
    @ResponseBody
    @ApiOperation(value = "提交订单修改")
    public ResponseResult orderInfoUpdateIng(Model model,HttpServletRequest request,OrderInfo orderInfo){
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        orderInfo.setXuid(userAdmin.getId());//下单人员ID为当前登录人ID

        //将未更改之前的订单车辆置为空闲
        OrderInfo curOrderInfo = iOrderInfoService.getById(orderInfo.getId());
        CarInfo availableCarInfo = new CarInfo();
        availableCarInfo.setId(curOrderInfo.getCiid());
        availableCarInfo.setState(1);
        iCarInfoService.updateById(availableCarInfo);

        if(iOrderInfoService.updateById(orderInfo)){
            orderInfo = iOrderInfoService.getById(orderInfo.getId());
            //订单更改后，将现派车辆置为行驶中
            CarInfo carInfo = iCarInfoService.getById(orderInfo.getCiid());
            if(carInfo.getType()==null||carInfo.getType()!=2){
                CarInfo changeCarInfo = new CarInfo();
                changeCarInfo.setId(carInfo.getId());
                changeCarInfo.setState(4);
                iCarInfoService.updateById(changeCarInfo);
            }

            return ResponseResult.successResult(orderInfo,request);
        };
        return ResponseResult.failResult("修改失败");
    }

    @RequestMapping(value = "/orderInfo/orderDetailUpdateIng")
    @ResponseBody
    @ApiOperation(value = "提交订单详情修改")
    public ResponseResult orderDetailUpdateIng(Model model,HttpServletRequest request,@RequestBody List<OrderInfoDetail> orderInfoDetails,Integer oiid){
        iOrderInfoDetailService.deleteByOiid(oiid);
        if(iOrderInfoDetailService.saveBatch(orderInfoDetails)){
            return ResponseResult.successResult(orderInfoDetails,request);
        };
        return ResponseResult.failResult("修改失败");
    }

    @RequestMapping(value = "/orderInfo/orderInfoMerge")
    @ResponseBody
    @ApiOperation(value = "合并采购单")
    public ResponseResult orderInfoMerge(Model model,HttpServletRequest request,String orderIds,Integer ciid,Integer suid){
        BigDecimal sumPrice = new BigDecimal(0);
        BigDecimal sumNumber = new BigDecimal(0);
        OrderInfo addOrderInfo = new OrderInfo();
        addOrderInfo.setSumPrice(sumPrice);
        addOrderInfo.setSumNumber(sumNumber);

        List<CarInfo> availableCarInfos = new ArrayList<>();
        List<String> orderIdList = Arrays.asList(orderIds.split(","));
        List<OrderInfo> orderInfos = iOrderInfoService.listByIds(orderIdList);
        List<OrderInfoDetail> allOrderInfoDetails = new ArrayList<>();
        for (OrderInfo orderInfo:orderInfos){
            Integer id = orderInfo.getId();
            List<OrderInfoDetail> orderInfoDetailList = iOrderInfoDetailService.findByOiid(id);
            for (OrderInfoDetail orderInfoDetail:orderInfoDetailList){
                BigDecimal totalPrice = orderInfoDetail.getTotalPrice();
                BigDecimal number = orderInfoDetail.getNumber();
                addOrderInfo.setSumPrice(addOrderInfo.getSumPrice().add(totalPrice));
                addOrderInfo.setSumNumber(addOrderInfo.getSumNumber().add(number));
                allOrderInfoDetails.add(orderInfoDetail);
            }
            CarInfo carInfo = new CarInfo();
            carInfo.setId(orderInfo.getCiid());
            carInfo.setState(1);
            availableCarInfos.add(carInfo);
        }
        Integer todayOrderNumber = iOrderInfoDao.sumTodayOrderNumber();//获取当天总订单数
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

        String orderNumber = dateNowStr+"B"+no;//订单编号
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        addOrderInfo.setOrderNumber(orderNumber);
        addOrderInfo.setCiid(ciid);
        addOrderInfo.setXuid(userAdmin.getId());//下单人员ID为当前登录人ID
        addOrderInfo.setSuid(suid);
        addOrderInfo.setState(1);
        List<Integer> removeOrderInfoDetailIds = new ArrayList<>();
        if(iOrderInfoService.save(addOrderInfo)){
            iCarInfoService.updateBatchById(availableCarInfos);
            CarInfo carInfo = iCarInfoService.getById(ciid);
            if(carInfo.getType()==null||carInfo.getType()!=2){
                CarInfo changeCarInfo = new CarInfo();
                changeCarInfo.setId(ciid);
                changeCarInfo.setState(4);
                iCarInfoService.updateById(changeCarInfo);
            }
            Integer oiid = addOrderInfo.getId();
            List<OrderInfoDetail> addOrderInfoDetails = new ArrayList<>();
            for (OrderInfoDetail orderInfoDetail:allOrderInfoDetails){
                removeOrderInfoDetailIds.add(orderInfoDetail.getId());
                OrderInfoDetail addOrderInfoDetail = new OrderInfoDetail();
                addOrderInfoDetail.setOiid(oiid);
                addOrderInfoDetail.setGiid(orderInfoDetail.getGiid());
                addOrderInfoDetail.setSiid(orderInfoDetail.getSiid());
                addOrderInfoDetail.setPrice(orderInfoDetail.getPrice());
                addOrderInfoDetail.setPriceSe(orderInfoDetail.getPriceSe());
                addOrderInfoDetail.setUnit(orderInfoDetail.getUnit());
                addOrderInfoDetail.setUnitSe(orderInfoDetail.getUnitSe());
                addOrderInfoDetail.setUnitType(orderInfoDetail.getUnitType());
                addOrderInfoDetail.setTotalPrice(orderInfoDetail.getTotalPrice());
                addOrderInfoDetail.setNumber(orderInfoDetail.getNumber());
                boolean addFlag = true;
                for(OrderInfoDetail addOrderDetail : addOrderInfoDetails){
                    if(orderInfoDetail.getGiid().equals(addOrderDetail.getGiid())
                        &&orderInfoDetail.getSiid().equals(addOrderDetail.getSiid())
                            &&orderInfoDetail.getUnitType().equals(addOrderDetail.getUnitType())){
                        addFlag = false;
                        BigDecimal goodsNumber = orderInfoDetail.getNumber();
                        BigDecimal totalPrice = orderInfoDetail.getTotalPrice();
                        BigDecimal addGoodsNumber = addOrderDetail.getNumber();
                        BigDecimal addTotalPrice = addOrderDetail.getTotalPrice();
                        addOrderDetail.setNumber(addGoodsNumber.add(goodsNumber));
                        addOrderDetail.setTotalPrice(addTotalPrice.add(totalPrice));
                        break;
                    }
                }
                if(addFlag){
                    addOrderInfoDetails.add(addOrderInfoDetail);
                }
            }
            iOrderInfoDetailService.saveBatch(addOrderInfoDetails);
            //合并完成后，将需合并的订单删除
            iOrderInfoService.removeByIds(orderIdList);
            iOrderInfoDetailService.removeByIds(removeOrderInfoDetailIds);
            return ResponseResult.successResult("合并成功",request);
        };
        return ResponseResult.failResult("合并失败");
    }

    @RequestMapping(value = "/orderInfo/orderInfoDetailByOiid")
    @ResponseBody
    @ApiOperation(value = "根据订单ID获取订单详细")
    public ResponseResult orderInfoDetailByOiid(Model model,HttpServletRequest request,Integer oiid){
        if(oiid==null){
            return ResponseResult.failResult("oiid不能为空");
        }
        List<OrderInfoDetail> orderInfoDetailList = iOrderInfoDetailService.findByOiid(oiid);
        return ResponseResult.successResult(orderInfoDetailList,request);
    }

}
