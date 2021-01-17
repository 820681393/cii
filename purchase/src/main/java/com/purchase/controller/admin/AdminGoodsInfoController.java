package com.purchase.controller.admin;

import com.github.pagehelper.PageInfo;
import com.purchase.common.log.MyLogger;
import com.purchase.config.SystemConfig;
import com.purchase.model.*;
import com.purchase.service.*;
import com.purchase.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Miracle
 * @date 2020/11/3 18:54
 */
@Controller
@RequestMapping("admin")
@Api(value = "goodsInfo", tags="商品信息权限")
public class AdminGoodsInfoController {

    @Autowired
    IGoodsInfoService iGoodsInfoService;

    @Autowired
    IGoodsOneTypeService iGoodsOneTypeService;

    @Autowired
    IGoodsTowTypeService iGoodsTowTypeService;

    @Autowired
    ISupplierInfoService iSupplierInfoService;

    @Autowired
    IUnitInfoService iUnitInfoService;

    @Autowired
    IAdminInfoService iAdminInfoService;

    @Autowired
    IAdminToRoleService iAdminToRoleService;

    @Autowired
    ICarInfoService iCarInfoService;

    @Autowired
    SystemConfig systemConfig;

    MyLogger myLogger=new MyLogger(this.getClass());

    @RequestMapping(value = "/goodsInfo/index")
    @ApiOperation(value = "查询商品")
    public String index(Model model, HttpServletRequest request,GoodsInfo goodsInfo){
        if(goodsInfo.getPageNum()==null){
            goodsInfo.setPageNum(0);
            goodsInfo.setPageSize(15);
        }
        PageInfo<GoodsInfo> pageInfos = iGoodsInfoService.selectGoodsInfoPageInfo(goodsInfo);
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList =iSupplierInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();
        for(GoodsInfo gInfo : pageInfos.getList()){
            for(GoodsOneType goodsOneType : goodsOneTypeList){
                if(goodsOneType.getId().equals(gInfo.getGoid())){
                    gInfo.setGoName(goodsOneType.getName());
                }
            }
            for(GoodsTowType goodsTowType : goodsTowTypeList){
                if(goodsTowType.getId().equals(gInfo.getGtid())){
                    gInfo.setGtName(goodsTowType.getName());
                }
            }
            for(SupplierInfo supplierInfo : supplierInfoList){
                if(supplierInfo.getId().equals(gInfo.getSiid())){
                    gInfo.setSupplierName(supplierInfo.getName());
                }
            }
            for(UnitInfo unitInfo : unitInfoList){
                if(unitInfo.getId().equals(gInfo.getUiidPr())){
                    gInfo.setUnitPrName(unitInfo.getName());
                }
                if(unitInfo.getId().equals(gInfo.getUiidPe())){
                    gInfo.setUnitPeName(unitInfo.getName());
                }
            }
        }
        model.addAttribute("pageInfos",pageInfos);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("goodsInfo",goodsInfo);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("unitInfoList",unitInfoList);
        return "/admin/goodsInfo/goods_info_index";
    }

    @RequestMapping(value = "/goodsInfo/stockIndex")
    @ApiOperation(value = "查询商品库存")
    public String stockIndex(Model model, HttpServletRequest request,GoodsInfo goodsInfo){
        if(goodsInfo.getPageNum()==null){
            goodsInfo.setPageNum(0);
            goodsInfo.setPageSize(15);
        }
        PageInfo<GoodsInfo> pageInfos = iGoodsInfoService.selectGoodsInfoPageInfo(goodsInfo);
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList =iSupplierInfoService.list();
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
        for(GoodsInfo gInfo : pageInfos.getList()){
            for(GoodsOneType goodsOneType : goodsOneTypeList){
                if(goodsOneType.getId().equals(gInfo.getGoid())){
                    gInfo.setGoName(goodsOneType.getName());
                }
            }
            for(GoodsTowType goodsTowType : goodsTowTypeList){
                if(goodsTowType.getId().equals(gInfo.getGtid())){
                    gInfo.setGtName(goodsTowType.getName());
                }
            }
            for(SupplierInfo supplierInfo : supplierInfoList){
                if(supplierInfo.getId().equals(gInfo.getSiid())){
                    gInfo.setSupplierName(supplierInfo.getName());
                }
            }
            for(UnitInfo unitInfo : unitInfoList){
                if(unitInfo.getId().equals(gInfo.getUiidPr())){
                    gInfo.setUnitPrName(unitInfo.getName());
                }
                if(unitInfo.getId().equals(gInfo.getUiidPe())){
                    gInfo.setUnitPeName(unitInfo.getName());
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

        model.addAttribute("pageInfos",pageInfos);
        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("goodsInfo",goodsInfo);
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("adminInfoList",caiGouAdminInfoList);
        model.addAttribute("carInfoList",availableCarInfoList);
        model.addAttribute("unitInfoList",unitInfoList);
        return "/admin/goodsStockInfo/goods_stock_info_index";
    }

    @RequestMapping(value = "/goodsInfo/insert")
    @ApiOperation(value = "进入新增商品")
    public String insert(Model model, HttpServletRequest request){
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList =iSupplierInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("unitInfoList",unitInfoList);
        return "/admin/goodsInfo/goods_info_insert";
    }

    @RequestMapping(value = "/goodsInfo/insertIng")
    @ApiOperation(value = "新增商品")
    public String insertIng(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file, GoodsInfo goodsInfo){
        if((file != null) && (file.getSize() > 0L)){
            String imgName = FileUtil.fileVerify(file,".jpg .png .jpeg");
            goodsInfo.setImgUrl(imgName);
        }
        iGoodsInfoService.save(goodsInfo);
        Integer code = goodsInfo.getId();
        goodsInfo.setCode("CAIGO"+code);
        iGoodsInfoService.updateById(goodsInfo);
        return "redirect:/admin/goodsInfo/index";
    }

    @RequestMapping(value = "/goodsInfo/update")
    @ApiOperation(value = "进入修改商品")
    public String update(Model model, HttpServletRequest request,Integer id){
        GoodsInfo goodsInfo = iGoodsInfoService.getById(id);
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList =iSupplierInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();

        model.addAttribute("aliyunOos",systemConfig.getAliyunOos());
        model.addAttribute("goodsOneTypeList",goodsOneTypeList);
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        model.addAttribute("goodsInfo",goodsInfo);
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("unitInfoList",unitInfoList);
        return "/admin/goodsInfo/goods_info_update";
    }

    @RequestMapping(value = "/goodsInfo/updateGoodsInfo")
    @ResponseBody
    @ApiOperation(value = "异步修改商品信息")
    public ResponseResult updateGoodsInfo(Model model, HttpServletRequest request,GoodsInfo goodsInfo){
        if(iGoodsInfoService.updateById(goodsInfo)){
            return ResponseResult.successResult(goodsInfo,request);
        };
        return ResponseResult.failResult("修改失败");
    }

    @RequestMapping(value = "/goodsInfo/updateIng")
    @ApiOperation(value = "修改商品")
    public String updateIng(Model model, HttpServletRequest request,@RequestParam("file") MultipartFile file,GoodsInfo goodsInfo,Integer type){
        if((file != null) && (file.getSize() > 0L)){
            String imgName = FileUtil.fileVerify(file,".jpg .png .jpeg");
            goodsInfo.setImgUrl(imgName);
        }
        if(type==1){
            iGoodsInfoService.updateById(goodsInfo);
        }else{
            iGoodsInfoService.save(goodsInfo);
            Integer code = goodsInfo.getId();
            goodsInfo.setCode("CAIGO"+code);
            iGoodsInfoService.updateById(goodsInfo);
        }
        return "redirect:/admin/goodsInfo/index";
    }

    @RequestMapping(value = "/goodsInfo/delete")
    @ApiOperation(value = "删除商品")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iGoodsInfoService.removeById(id);
        return "redirect:/admin/goodsInfo/index";
    }

    @RequestMapping(value = "/goodsInfo/selSupplier")
    @ApiOperation(value = "删除商品")
    public String selSupplier(Model model, HttpServletRequest request,Integer supplierId,Integer goodsId){
        List<SupplierInfo> supplierInfoList = iSupplierInfoService.list();
        model.addAttribute("supplierInfoList",supplierInfoList);
        model.addAttribute("supplierId",supplierId);
        model.addAttribute("goodsId",goodsId);
        return "/admin/goodsInfo/goods_sel_supplier";
    }

    @RequestMapping(value="/goodsInfo/getGoodsInfoExcelTemple",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出商品模板")
    public void getGoodsInfoExcelTemple(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
        String fileName = "商品信息模板.xlsx";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        String[] headList = {"中文名称","英文名称","一级分类","二级分类"};
        String[] fieldList = {"chName","enName","goId","gtId"};
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> gMap = new HashMap<>();
        gMap.put("chName","导入测试");
        gMap.put("enName","import test");
        gMap.put("goId",2);
        gMap.put("gtId",9);
        dataList.add(gMap);
        XSSFWorkbook wb = ExcelUtils.createExcel(headList, fieldList, dataList);
        try {
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
            wb.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/goodsInfo/getGoodsInfoExcel",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "导出商品数据")
    public void getGoodsInfoExcel(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setPageNum(0);
        goodsInfo.setPageSize(1000000);
        PageInfo<GoodsInfo> pageInfo = iGoodsInfoService.selectGoodsInfoPageInfo(goodsInfo);
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<SupplierInfo> supplierInfoList =iSupplierInfoService.list();
        List<UnitInfo> unitInfoList =iUnitInfoService.list();
        for(GoodsInfo gInfo : pageInfo.getList()){
            for(GoodsOneType goodsOneType : goodsOneTypeList){
                if(goodsOneType.getId().equals(gInfo.getGoid())){
                    gInfo.setGoName(goodsOneType.getName());
                }
            }
            for(GoodsTowType goodsTowType : goodsTowTypeList){
                if(goodsTowType.getId().equals(gInfo.getGtid())){
                    gInfo.setGtName(goodsTowType.getName());
                }
            }
            for(SupplierInfo supplierInfo : supplierInfoList){
                if(supplierInfo.getId().equals(gInfo.getSiid())){
                    gInfo.setSupplierName(supplierInfo.getName());
                }
            }
            for(UnitInfo unitInfo : unitInfoList){
                if(unitInfo.getId().equals(gInfo.getUiidPr())){
                    gInfo.setUnitPrName(unitInfo.getName());
                }
                if(unitInfo.getId().equals(gInfo.getUiidPe())){
                    gInfo.setUnitPeName(unitInfo.getName());
                }
            }
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
        String fileName = "商品信息列表.xlsx";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        String[] headList = {"商品编号","中文名称","英文名称","一级分类","二级分类","参考价格","单位","供应商"};
        String[] fieldList = {"code","chName","enName","goName","gtName","price","unit","supplierName"};
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < pageInfo.getList().size(); i++) {
            GoodsInfo gInfo = pageInfo.getList().get(i);
            Map<String, Object> gMap = new HashMap<>();
            gMap.put("code",gInfo.getCode());
            gMap.put("chName",gInfo.getChName());
            gMap.put("enName",gInfo.getEnName());
            gMap.put("goName",gInfo.getGoName());
            gMap.put("gtName",gInfo.getGtName());
            gMap.put("price",gInfo.getPrice());
            gMap.put("unit",gInfo.getUnitPrName());
            gMap.put("supplierName",gInfo.getSupplierName());
            if(gInfo.getUnitType()!=null){
                if(gInfo.getUnitType()==2){
                    gMap.put("price",gInfo.getPriceSe());
                    gMap.put("unit",gInfo.getUnitPeName());
                }
            }
            dataList.add(gMap);
        }
        XSSFWorkbook wb = ExcelUtils.createExcel(headList, fieldList, dataList);
        try {
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
            wb.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/goodsInfo/importGoodsInfoExcel")
    @ResponseBody
    @ApiOperation(value = "导入商品数据")
    public ResponseResult importGoodsInfoExcel(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file){
        try {
            List<List<Object>> exelList = ExcelUtils.readExcel(MultipartFileToFile.multipartFileToFile(file));
            System.out.println(exelList);
            List<GoodsInfo> goodsInfos = new ArrayList<>();
            for (int i = 0;i<exelList.size();i++){
                List<Object> objectList = exelList.get(i);
                GoodsInfo goodsInfo = new GoodsInfo();
                String chName = objectList.get(0)+"";
                String enName = objectList.get(1)+"";
                Integer goId = Double.valueOf(objectList.get(2)+"").intValue();
                Integer gtId = -1;
                if(objectList.size()==4){
                    gtId = Double.valueOf(objectList.get(3)+"").intValue();
                }


                if(!StringUilts.isEmptyOrNull(chName)){
                    goodsInfo.setChName(chName);
                }
                if(!StringUilts.isEmptyOrNull(enName)){
                    goodsInfo.setEnName(enName);
                }
                if(goId!=null){
                    goodsInfo.setGoid(goId);
                }
                if(gtId!=null){
                    goodsInfo.setGtid(gtId);
                }
                goodsInfos.add(goodsInfo);
            }

            iGoodsInfoService.saveBatch(goodsInfos);
            for (int i = 0;i<goodsInfos.size();i++){
                GoodsInfo goodsInfo = goodsInfos.get(i);
                goodsInfo.setCode("CAIGO"+goodsInfo.getId());
            }
            iGoodsInfoService.updateBatchById(goodsInfos);
            return ResponseResult.successResult("导入成功",request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failResult("导入失败");
    }
}
