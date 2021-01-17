package com.purchase.controller.admin;


import com.github.pagehelper.PageInfo;
import com.purchase.config.SystemConfig;
import com.purchase.dao.IGoodsCheckTaskDao;
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
import java.math.BigDecimal;
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
@Api(value = "goodsCheckTask", tags="商品盘点任务")
public class AdminGoodsCheckTaskController {

    @Autowired
    IGoodsCheckTaskService iGoodsCheckTaskService;

    @Autowired
    IGoodsCheckTaskDetailService iGoodsCheckTaskDetailService;

    @Autowired
    IGoodsCheckTaskDao iGoodsCheckTaskDao;

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
    IAdminToRoleService iAdminToRoleService;

    @Autowired
    SystemConfig systemConfig;

    @RequestMapping(value = "/goodsCheckTask/index")
    @ApiOperation(value = "查询库存信息")
    public String index(Model model, HttpServletRequest request, GoodsCheckTask goodsCheckTask) {
        if (goodsCheckTask.getPageNum() == null) {
            goodsCheckTask.setPageNum(0);
            goodsCheckTask.setPageSize(15);
        }
        PageInfo<GoodsCheckTask> pageInfos = iGoodsCheckTaskService.selectGoodsCheckTaskPageInfo(goodsCheckTask);
        List<GoodsOneType> goodsOneTypeList = iGoodsOneTypeService.list();
        List<AdminInfo> adminInfoList = iAdminInfoService.list();
        for (GoodsCheckTask goodsCheckTask1 : pageInfos.getList()) {
            for (GoodsOneType goodsOneType : goodsOneTypeList) {
                if (goodsOneType.getId().equals(goodsCheckTask1.getGoid())) {
                    goodsCheckTask1.setGoName(goodsOneType.getName());
                }
            }
            for (AdminInfo adminInfo : adminInfoList) {
                if (goodsCheckTask1.getAiid().equals(adminInfo.getId())) {
                    goodsCheckTask1.setAdminName(adminInfo.getNikeName());
                }
                if (goodsCheckTask1.getReceiveAiid().equals(adminInfo.getId())) {
                    goodsCheckTask1.setReceiveAdminName(adminInfo.getNikeName());
                }
            }
        }
        model.addAttribute("pageInfos", pageInfos);
        model.addAttribute("GoodsCheckTask", goodsCheckTask);
        return "/admin/goodsCheckTask/goods_check_task_index";
    }

    @RequestMapping(value = "/goodsCheckTask/insert")
    @ApiOperation(value = "进入盘点任务派发")
    public String insert(Model model, HttpServletRequest request) {
        List<GoodsOneType> goodsOneTypeList = iGoodsOneTypeService.list();
        List<AdminInfo> adminInfoList = iAdminInfoService.list();
        List<AdminToRole> adminToRoles = iAdminToRoleService.list();
        List<AdminInfo> pdAdminInfoList = new ArrayList<>();
        for (AdminInfo adminInfo : adminInfoList) {
            for (AdminToRole adminToRole : adminToRoles) {
                if (adminInfo.getId() == adminToRole.getAiid() && adminToRole.getRiid() == 14) {
                    pdAdminInfoList.add(adminInfo);
                }
            }
        }

        model.addAttribute("goodsOneTypeList", goodsOneTypeList);
        model.addAttribute("pdAdminInfoList", pdAdminInfoList);
        return "/admin/goodsCheckTask/goods_check_task_insert";
    }

    @RequestMapping(value = "/goodsCheckTask/insertIng")
    @ApiOperation(value = "盘点任务派发")
    public String insertIng(Model model, HttpServletRequest request, GoodsCheckTask goodsCheckTask) {
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        goodsCheckTask.setAiid(userAdmin.getId());

        Integer todayOrderNumber = iGoodsCheckTaskDao.sumTodayGoodsCheckTaskNumber();//获取当天总订单数
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        Integer number = todayOrderNumber + 1;
        String no = number + "";
        if (number < 10) {
            no = "00" + number;
        } else if (number >= 10 && number < 100) {
            no = "0" + number;
        }

        String orderNumber = dateNowStr + "P" + no;//订单编号
        goodsCheckTask.setOrderNumber(orderNumber);
        if (iGoodsCheckTaskService.save(goodsCheckTask)) {
            List<GoodsInfo> goodsInfoList = iGoodsInfoService.findByGoid(goodsCheckTask.getGoid());
            List<UnitInfo> unitInfoList = iUnitInfoService.list();
            for (GoodsInfo gInfo : goodsInfoList) {
                for (UnitInfo unitInfo : unitInfoList) {
                    if (unitInfo.getId().equals(gInfo.getUiidPr())) {
                        gInfo.setUnitPrName(unitInfo.getName());
                    }
                    if (unitInfo.getId().equals(gInfo.getUiidPe())) {
                        gInfo.setUnitPeName(unitInfo.getName());
                    }
                }
            }
            BigDecimal totalAmount = new BigDecimal(0);
            List<GoodsCheckTaskDetail> goodsCheckTaskDetailList = new ArrayList<>();
            for (GoodsInfo goodsInfo : goodsInfoList) {
                GoodsCheckTaskDetail goodsCheckTaskDetail = new GoodsCheckTaskDetail();
                goodsCheckTaskDetail.setGctid(goodsCheckTask.getId());
                goodsCheckTaskDetail.setGiid(goodsInfo.getId());
                goodsCheckTaskDetail.setGoodsName(goodsInfo.getChName() + "<br/>" + goodsInfo.getEnName());
                goodsCheckTaskDetail.setUnit(goodsInfo.getUnitPrName());
                goodsCheckTaskDetail.setUnitSe(goodsInfo.getUnitPeName());
                goodsCheckTaskDetail.setPrice(goodsInfo.getPrice());
                goodsCheckTaskDetail.setPriceSe(goodsInfo.getPriceSe());
                goodsCheckTaskDetail.setGoodsStock(goodsInfo.getStock());
                goodsCheckTaskDetail.setGoodsStockSe(goodsInfo.getStockSe());
                goodsCheckTaskDetailList.add(goodsCheckTaskDetail);
                if (goodsInfo.getPrice() != null) {
                    totalAmount = totalAmount.add(goodsInfo.getPrice().multiply(new BigDecimal(goodsInfo.getStock().toString())));
                }
                if (goodsInfo.getPriceSe() != null) {
                    totalAmount = totalAmount.add(goodsInfo.getPriceSe().multiply(new BigDecimal(goodsInfo.getStockSe().toString())));
                }
            }
            GoodsCheckTask updateGoodsCheckTask = new GoodsCheckTask();
            updateGoodsCheckTask.setId(goodsCheckTask.getId());
            updateGoodsCheckTask.setTotalAmount(totalAmount);
            iGoodsCheckTaskService.updateById(updateGoodsCheckTask);
            iGoodsCheckTaskDetailService.saveBatch(goodsCheckTaskDetailList);
        }
        return "redirect:/admin/goodsCheckTask/index";
    }


    @RequestMapping(value = "/goodsCheckTask/diffInfo")
    @ApiOperation(value = "查看盘点差异信息")
    public String diffInfo(Model model, HttpServletRequest request, Integer id) {
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        GoodsCheckTask goodsCheckTask = iGoodsCheckTaskService.getById(id);
        List<GoodsCheckTaskDetail> goodsCheckTaskDetailList = iGoodsCheckTaskDetailService.findByGctid(goodsCheckTask.getId());
        List<Integer> giids = new ArrayList<>();
        for (GoodsCheckTaskDetail goodsCheckTaskDetail : goodsCheckTaskDetailList) {
            giids.add(goodsCheckTaskDetail.getGiid());
        }
        List<GoodsInfo> goodsInfoList = iGoodsInfoService.listByIds(giids);
        for (GoodsCheckTaskDetail goodsCheckTaskDetail : goodsCheckTaskDetailList) {
            for (GoodsInfo goodsInfo : goodsInfoList) {
                if (goodsCheckTaskDetail.getGiid().equals(goodsInfo.getId())) {
                    goodsCheckTaskDetail.setGoodsStock(goodsInfo.getStock());
                    goodsCheckTaskDetail.setGoodsStockSe(goodsInfo.getStockSe());
                    break;
                }
            }
        }
//        BigDecimal totalAmount = new BigDecimal(0);//商品总库存货值
//        BigDecimal realTotalAmount = new BigDecimal(0);//商品总盘点货值
//        BigDecimal totalDiffAmount = new BigDecimal(0);//商品盘点总库存差值
//        for(GoodsCheckTaskDetail goodsCheckTaskDetail: goodsCheckTaskDetailList){
//            BigDecimal price = goodsCheckTaskDetail.getPrice();
//            BigDecimal priceSe = goodsCheckTaskDetail.getPriceSe();
//            Integer number = goodsCheckTaskDetail.getNumber();
//            Integer numberSe = goodsCheckTaskDetail.getNumberSe();
//            Integer goodsStock = goodsCheckTaskDetail.getGoodsStock();
//            Integer goodsStockSe = goodsCheckTaskDetail.getGoodsStockSe();
//            BigDecimal diffAmount = new BigDecimal(0);
//            if(number!=null&&price!=null){
//                BigDecimal amount = price.multiply(new BigDecimal(number.toString()));
//                BigDecimal realAmount = price.multiply(new BigDecimal(goodsStock.toString()));
//                diffAmount = diffAmount.add(amount.subtract(realAmount));
//                totalAmount = totalAmount.add(amount);
//                realTotalAmount = realTotalAmount.add(realAmount);
//            }
//            if(numberSe!=null&&priceSe!=null){
//                BigDecimal amount = priceSe.multiply(new BigDecimal(numberSe.toString()));
//                BigDecimal realAmount = priceSe.multiply(new BigDecimal(goodsStockSe.toString()));
//                diffAmount = diffAmount.add(amount.subtract(realAmount));
//                totalAmount = totalAmount.add(amount);
//                realTotalAmount = realTotalAmount.add(realAmount);
//            }
//            totalDiffAmount = totalDiffAmount.add(diffAmount);
//            goodsCheckTaskDetail.setDiffAmount(diffAmount);
//        }
        AdminInfo adminInfo = iAdminInfoService.getById(goodsCheckTask.getAiid());
        goodsCheckTask.setAdminName(adminInfo.getNikeName());
        AdminInfo receiveAdminInfo = iAdminInfoService.getById(goodsCheckTask.getReceiveAiid());
        goodsCheckTask.setReceiveAdminName(receiveAdminInfo.getNikeName());

        model.addAttribute("goodsCheckTask", goodsCheckTask);
        model.addAttribute("goodsCheckTaskDetailList", goodsCheckTaskDetailList);
        model.addAttribute("userAdmin",userAdmin);
//        model.addAttribute("totalAmount",totalAmount);
//        model.addAttribute("realTotalAmount",realTotalAmount);
//        model.addAttribute("totalDiffAmount",totalDiffAmount);
        return "/admin/goodsCheckTask/goods_check_task_diffInfo";
    }

    @RequestMapping(value = "/goodsCheckTask/myCheckTask")
    @ApiOperation(value = "我的盘点任务")
    public String myCheckTask(Model model, HttpServletRequest request) {
        AdminInfo userAdmin = (AdminInfo) request.getSession().getAttribute("userAdmin");
        List<GoodsCheckTask> goodsCheckTaskList = iGoodsCheckTaskService.findByStateAndReceiveAiid(1, userAdmin.getId());
        for (GoodsCheckTask goodsCheckTask : goodsCheckTaskList) {
            List<GoodsCheckTaskDetail> goodsCheckTaskDetailList = iGoodsCheckTaskDetailService.findByGctid(goodsCheckTask.getId());
            goodsCheckTask.setGoodsCheckTaskDetailList(goodsCheckTaskDetailList);
        }
        model.addAttribute("goodsCheckTaskList", goodsCheckTaskList);
        return "/admin/goodsCheckTask/my_goods_check_task";
    }

    @RequestMapping(value = "/goodsCheckTask/submitMyCheckTask")
    @ResponseBody
    @ApiOperation(value = "提交我的盘点任务")
    public ResponseResult submitMyCheckTask(Model model, HttpServletRequest request, @RequestBody List<GoodsCheckTaskDetail> goodsCheckTaskDetailList, Integer id) {
        List<Integer> ids = new ArrayList<>();
        for(GoodsCheckTaskDetail goodsCheckTaskDetail : goodsCheckTaskDetailList){
            ids.add(goodsCheckTaskDetail.getId());
        }
        List<GoodsCheckTaskDetail> curGoodsCheckTaskDetailList = iGoodsCheckTaskDetailService.listByIds(ids);
        for(GoodsCheckTaskDetail curGoodsCheckTaskDetail :curGoodsCheckTaskDetailList){
            for(GoodsCheckTaskDetail goodsCheckTaskDetail : goodsCheckTaskDetailList){
                if(curGoodsCheckTaskDetail.getId().equals(goodsCheckTaskDetail.getId())){
                    goodsCheckTaskDetail.setPrice(curGoodsCheckTaskDetail.getPrice());
                    goodsCheckTaskDetail.setPriceSe(curGoodsCheckTaskDetail.getPriceSe());
                    goodsCheckTaskDetail.setGoodsStock(curGoodsCheckTaskDetail.getGoodsStock());
                    goodsCheckTaskDetail.setGoodsStockSe(curGoodsCheckTaskDetail.getGoodsStockSe());
                }
            }
        }

        BigDecimal realTotalAmount = new BigDecimal(0);//商品总盘点货值
        BigDecimal totalDiffAmount = new BigDecimal(0);//商品盘点总库存差值
        for(GoodsCheckTaskDetail goodsCheckTaskDetail: goodsCheckTaskDetailList){
            BigDecimal price = goodsCheckTaskDetail.getPrice();
            BigDecimal priceSe = goodsCheckTaskDetail.getPriceSe();
            Integer number = goodsCheckTaskDetail.getNumber();
            Integer numberSe = goodsCheckTaskDetail.getNumberSe();
            Integer goodsStock = goodsCheckTaskDetail.getGoodsStock();
            Integer goodsStockSe = goodsCheckTaskDetail.getGoodsStockSe();
            BigDecimal amount = new BigDecimal(0);
            BigDecimal realAmount = new BigDecimal(0);
            if(number!=null&&price!=null){
                amount = amount.add(price.multiply(new BigDecimal(goodsStock.toString())));
                realAmount = realAmount.add(price.multiply(new BigDecimal(number.toString())));
            }
            if(numberSe!=null&&priceSe!=null){
                amount = amount.add(priceSe.multiply(new BigDecimal(goodsStockSe.toString())));
                realAmount = realAmount.add(priceSe.multiply(new BigDecimal(numberSe.toString())));
            }

            BigDecimal diffAmount = amount.subtract(realAmount);
            goodsCheckTaskDetail.setDiffAmount(diffAmount);

            realTotalAmount = realTotalAmount.add(realAmount);
            totalDiffAmount = totalDiffAmount.add(diffAmount);
        }
        GoodsCheckTask goodsCheckTask = new GoodsCheckTask();
        goodsCheckTask.setId(id);
        goodsCheckTask.setState(2);
        goodsCheckTask.setRealTotalAmount(realTotalAmount);
        goodsCheckTask.setTotalDiffAmount(totalDiffAmount);
        goodsCheckTask.setCheckTime(new Date());
        if(iGoodsCheckTaskService.updateById(goodsCheckTask)){
            if(iGoodsCheckTaskDetailService.updateBatchById(goodsCheckTaskDetailList)){
                return ResponseResult.successResult(goodsCheckTaskDetailList,request);
            }
        }
        return ResponseResult.failResult("提交失败");
    }

    @RequestMapping(value = "/goodsCheckTask/confirmGoodsCheckTask")
    @ResponseBody
    @ApiOperation(value = "确认盘点结果")
    public ResponseResult confirmGoodsCheckTask(Model model, HttpServletRequest request, @RequestBody List<GoodsCheckTaskDetail> goodsCheckTaskDetailList, Integer id,String remark) {
        BigDecimal realTotalAmount = new BigDecimal(0);//商品总盘点货值
        BigDecimal totalDiffAmount = new BigDecimal(0);//商品盘点总库存差值
        if(goodsCheckTaskDetailList!=null&&goodsCheckTaskDetailList.size()>0){
            List<Integer> ids = new ArrayList<>();
            for(GoodsCheckTaskDetail goodsCheckTaskDetail : goodsCheckTaskDetailList){
                ids.add(goodsCheckTaskDetail.getId());
            }
            List<GoodsCheckTaskDetail> curGoodsCheckTaskDetailList = iGoodsCheckTaskDetailService.listByIds(ids);
            for(GoodsCheckTaskDetail curGoodsCheckTaskDetail :curGoodsCheckTaskDetailList){
                for(GoodsCheckTaskDetail goodsCheckTaskDetail : goodsCheckTaskDetailList){
                    if(curGoodsCheckTaskDetail.getId().equals(goodsCheckTaskDetail.getId())){
                        goodsCheckTaskDetail.setPrice(curGoodsCheckTaskDetail.getPrice());
                        goodsCheckTaskDetail.setPriceSe(curGoodsCheckTaskDetail.getPriceSe());
                        goodsCheckTaskDetail.setGoodsStock(curGoodsCheckTaskDetail.getGoodsStock());
                        goodsCheckTaskDetail.setGoodsStockSe(curGoodsCheckTaskDetail.getGoodsStockSe());
                    }
                }
            }

            for(GoodsCheckTaskDetail goodsCheckTaskDetail: goodsCheckTaskDetailList){
                BigDecimal price = goodsCheckTaskDetail.getPrice();
                BigDecimal priceSe = goodsCheckTaskDetail.getPriceSe();
                Integer number = goodsCheckTaskDetail.getNumber();
                Integer numberSe = goodsCheckTaskDetail.getNumberSe();
                Integer goodsStock = goodsCheckTaskDetail.getGoodsStock();
                Integer goodsStockSe = goodsCheckTaskDetail.getGoodsStockSe();
                BigDecimal amount = new BigDecimal(0);
                BigDecimal realAmount = new BigDecimal(0);
                if(number!=null&&price!=null){
                    amount = amount.add(price.multiply(new BigDecimal(goodsStock.toString())));
                    realAmount = realAmount.add(price.multiply(new BigDecimal(number.toString())));
                }
                if(numberSe!=null&&priceSe!=null){
                    amount = amount.add(priceSe.multiply(new BigDecimal(goodsStockSe.toString())));
                    realAmount = realAmount.add(priceSe.multiply(new BigDecimal(numberSe.toString())));
                }

                BigDecimal diffAmount = amount.subtract(realAmount);
                goodsCheckTaskDetail.setDiffAmount(diffAmount);

                realTotalAmount = realTotalAmount.add(realAmount);
                totalDiffAmount = totalDiffAmount.add(diffAmount);
            }
        }
        GoodsCheckTask goodsCheckTask = new GoodsCheckTask();
        goodsCheckTask.setId(id);
        goodsCheckTask.setState(3);
        if(goodsCheckTaskDetailList!=null&&goodsCheckTaskDetailList.size()>0) {
            goodsCheckTask.setRealTotalAmount(realTotalAmount);
            goodsCheckTask.setTotalDiffAmount(totalDiffAmount);
        }
        goodsCheckTask.setRemark(remark);
        goodsCheckTask.setFinishTime(new Date());
        if(iGoodsCheckTaskService.updateById(goodsCheckTask)){
            if(iGoodsCheckTaskDetailService.updateBatchById(goodsCheckTaskDetailList)){
                return ResponseResult.successResult(goodsCheckTaskDetailList,request);
            }
        }
        return ResponseResult.failResult("提交失败");
    }
}

