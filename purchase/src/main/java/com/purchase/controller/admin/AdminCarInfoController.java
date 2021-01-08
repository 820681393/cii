package com.purchase.controller.admin;

import com.purchase.common.log.MyLogger;
import com.purchase.config.SystemConfig;
import com.purchase.model.CarInfo;
import com.purchase.model.MenuInfo;
import com.purchase.service.ICarInfoService;
import com.purchase.utils.AliyunOosUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author Miracle
 * @date 2020/11/3 20:43
 */
@Controller
@RequestMapping("admin")
@Api(value = "carInfo", tags="车辆信息权限")
public class AdminCarInfoController {

    @Autowired
    ICarInfoService iCarInfoService;
    @Autowired
    SystemConfig systemConfig;

    MyLogger myLogger=new MyLogger(this.getClass());

    @RequestMapping(value = "/carInfo/index")
    @ApiOperation(value = "车辆查询")
    public String index(Model model, HttpServletRequest request){
        List<CarInfo> carInfoList=iCarInfoService.list();
        model.addAttribute("carInfoList",carInfoList);
        model.addAttribute("aliyunOosUrl",systemConfig.getAliyunOos());
        return "/admin/carInfo/car_info_index";
    }

    @RequestMapping(value = "/carInfo/insert")
    @ApiOperation(value = "进入车辆新增")
    public String insert(Model model, HttpServletRequest request){
        return "/admin/carInfo/car_info_insert";
    }

    @RequestMapping(value = "/carInfo/insertIng")
    @ApiOperation(value = "车辆新增")
    public String insertIng(Model model, HttpServletRequest request,CarInfo carInfo,@RequestParam("image1") MultipartFile image1,@RequestParam("carOrImage1") MultipartFile carOrImage1,@RequestParam("carCrImage1") MultipartFile carCrImage1) throws IOException{
        if(carInfo.getLimitDay()!=null){
            carInfo.setLimitDay(carInfo.getLimitDay().replaceAll("，",","));
        }
        InputStream inputStream = null;
        if(image1!=null&&image1.getBytes().length > 0){
            try{
                if ((image1 == null) || (image1.getSize() <= 0L)) {
                    throw new RuntimeException("请选择车辆图片");
                }
                String originalFilename = image1.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    throw new RuntimeException("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = image1.getInputStream();
                AliyunOosUtil.uploadFile(inputStream,originalFilename);
                carInfo.setImage(originalFilename);
            }catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    myLogger.error(e.toString());
                }
            }
        }
        if(carCrImage1!=null&&carCrImage1.getBytes().length > 0){
            try{
                if ((carCrImage1 == null) || (carCrImage1.getSize() <= 0L)) {
                    throw new RuntimeException("请选择车辆CR图片");
                }
                String originalFilename = carCrImage1.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    throw new RuntimeException("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = carCrImage1.getInputStream();
                AliyunOosUtil.uploadFile(inputStream,originalFilename);
                carInfo.setCarCrImage(originalFilename);
            }catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    myLogger.error(e.toString());
                }
            }
        }
        if(carOrImage1!=null&&carOrImage1.getBytes().length > 0){
            try{
                if ((carOrImage1 == null) || (carOrImage1.getSize() <= 0L)) {
                    throw new RuntimeException("请选择车辆OR图片");
                }
                String originalFilename = carOrImage1.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    throw new RuntimeException("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = carOrImage1.getInputStream();
                AliyunOosUtil.uploadFile(inputStream,originalFilename);
                carInfo.setCarOrImage(originalFilename);
            }catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    myLogger.error(e.toString());
                }
            }
        }
        iCarInfoService.save(carInfo);
        return "redirect:/admin/carInfo/index";
    }
    @RequestMapping(value = "/carInfo/update")
    @ApiOperation(value = "进入车辆修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        CarInfo sqlCarInfo=iCarInfoService.getById(id);
        model.addAttribute("sqlCarInfo",sqlCarInfo);
        model.addAttribute("aliyunOosUrl",systemConfig.getAliyunOos());
        return "/admin/carInfo/car_info_update";
    }


    @RequestMapping(value = "/carInfo/delete")
    @ApiOperation(value = "删除车辆")
    public String delete(Model model, HttpServletRequest request,Integer id){
        CarInfo sqlCarInfo=iCarInfoService.getById(id);
        AliyunOosUtil.deleteFile(sqlCarInfo.getImage());
        AliyunOosUtil.deleteFile(sqlCarInfo.getCarCrImage());
        AliyunOosUtil.deleteFile(sqlCarInfo.getCarOrImage());
        iCarInfoService.removeById(id);
        return "redirect:/admin/carInfo/index";
    }

    @RequestMapping(value = "/carInfo/updateIng")
    @ApiOperation(value = "修改车辆")
    public String updateIng(Model model, HttpServletRequest request,CarInfo carInfo,@RequestParam("image1") MultipartFile image1,@RequestParam("carOrImage1") MultipartFile carOrImage1,@RequestParam("carCrImage1") MultipartFile carCrImage1) throws IOException {
        if(carInfo.getLimitDay()!=null){
            carInfo.setLimitDay(carInfo.getLimitDay().replaceAll("，",","));
        }
        InputStream inputStream = null;
        if (image1!=null&&image1.getBytes().length > 0) {
            try {
                String originalFilename = image1.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    throw new RuntimeException("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = image1.getInputStream();
                AliyunOosUtil.uploadFile(inputStream, originalFilename);
                carInfo.setImage(originalFilename);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    myLogger.error(e.toString());
                }
            }
        }

        if (carCrImage1!=null&&carCrImage1.getBytes().length>0) {
            try{

                String originalFilename = carCrImage1.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    throw new RuntimeException("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = carCrImage1.getInputStream();
                AliyunOosUtil.uploadFile(inputStream,originalFilename);
                carInfo.setCarCrImage(originalFilename);
            }catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    myLogger.error(e.toString());
                }
            }
        }

        if (carOrImage1!=null&&carOrImage1.getBytes().length>0) {
            try{
                String originalFilename = carOrImage1.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    throw new RuntimeException("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = carOrImage1.getInputStream();
                AliyunOosUtil.uploadFile(inputStream,originalFilename);
                carInfo.setCarOrImage(originalFilename);
            }catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
                catch (IOException e) {
                    myLogger.error(e.toString());
                }
            }
        }

        iCarInfoService.updateById(carInfo);
        return "redirect:/admin/carInfo/index";
    }
}
