package com.purchase.controller.admin;


import com.purchase.config.SystemConfig;
import com.purchase.model.AdminInfo;
import com.purchase.model.MerchantInfoAddress;
import com.purchase.service.IAdminInfoService;
import com.purchase.service.IMerchantInfoAddressService;
import com.purchase.utils.AliyunOosUtil;
import com.purchase.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Controller
@RequestMapping("admin")
@Api(value = "merchantInfoAddress", tags="商户配送地址管理权限")
public class AdminMerchantInfoAddressController {

    @Autowired
    IMerchantInfoAddressService iMerchantInfoAddressService;
    @Autowired
    IAdminInfoService iAdminInfoService;
    @Autowired
    SystemConfig systemConfig;
    @RequestMapping(value = "/merchantInfoAddress/index")
    @ApiOperation(value = "查询商户收货地址")
    public String index(Model model, HttpServletRequest request,Integer id){
        List<MerchantInfoAddress> merchantInfoAddressList=iMerchantInfoAddressService.findByMiid(id);
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(id);
        model.addAttribute("merchantInfoAddressList",merchantInfoAddressList);
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        model.addAttribute("aliyunOosUrl",systemConfig.getAliyunOos());
        return "/admin/userAdmin/merchant_address_index_ajax";
    }
    @RequestMapping(value = "/merchantInfoAddress/insert")
    @ApiOperation(value = "进入商户收货地址新增")
    public String insert(Model model, HttpServletRequest request,Integer id){
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(id);
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        return "/admin/userAdmin/merchant_address_insert_ajax";
    }


    @RequestMapping(value = "/merchantInfoAddress/insertIng")
    @ApiOperation(value = "商户收货地址新增")
    @ResponseBody
    public ResponseResult insertIng(Model model, HttpServletRequest request, MerchantInfoAddress merchantInfoAddress,@RequestParam("file") MultipartFile file){
        InputStream inputStream = null;
        try{
            if ((file == null) || (file.getSize() <= 0L)) {
                return ResponseResult.failResult("请选择门市图片");
            }
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            if (!".jpg .png .jpeg".contains(substring)) {
                return ResponseResult.failResult("长传文件格式只支持 JPG PNG JPEG");
            }
            originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
            inputStream = file.getInputStream();
            AliyunOosUtil.uploadFile(inputStream,originalFilename);
            merchantInfoAddress.setImage(originalFilename);
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
            }
        }

        boolean flag=iMerchantInfoAddressService.save(merchantInfoAddress);
        if(flag){
            return ResponseResult.successResult("新增成功",request);
        }
        return ResponseResult.failResult("新增失败");
    }


    @RequestMapping(value = "/merchantInfoAddress/update")
    @ApiOperation(value = "进入商户收货地址修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        MerchantInfoAddress sqlMerchantInfoAddress=iMerchantInfoAddressService.getById(id);
        model.addAttribute("sqlMerchantInfoAddress",sqlMerchantInfoAddress);
        AdminInfo sqlAdminInfo=iAdminInfoService.getById(sqlMerchantInfoAddress.getMiid());
        model.addAttribute("sqlAdminInfo",sqlAdminInfo);
        model.addAttribute("aliyunOosUrl",systemConfig.getAliyunOos());
        return "/admin/userAdmin/merchant_address_update_ajax";
    }


    @RequestMapping(value = "/merchantInfoAddress/updateIng")
    @ApiOperation(value = "商户收货地址修改")
    @ResponseBody
    public ResponseResult updateIng(Model model, HttpServletRequest request,MerchantInfoAddress merchantInfoAddress,@RequestParam(value="file",required=false) MultipartFile file){
        InputStream inputStream = null;
        if ((file != null) && (file.getSize() > 0L)) {
            try{

                String originalFilename = file.getOriginalFilename();
                String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                if (!".jpg .png .jpeg".contains(substring)) {
                    return ResponseResult.failResult("长传文件格式只支持 JPG PNG JPEG");
                }
                originalFilename = UUID.randomUUID().toString().replace("-", "") + substring;
                inputStream = file.getInputStream();
                AliyunOosUtil.uploadFile(inputStream,originalFilename);
                merchantInfoAddress.setImage(originalFilename);
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
                }
            }
        }
        boolean flag=iMerchantInfoAddressService.updateById(merchantInfoAddress);
        if(flag){
            return ResponseResult.successResult("修改成功",request);
        }
        return ResponseResult.failResult("修改失败");
    }

    @RequestMapping(value = "/merchantInfoAddress/delete")
    @ApiOperation(value = "商户收货地址新增删除")
    @ResponseBody
    public ResponseResult delete(Model model, HttpServletRequest request,Integer id){
        boolean flag=iMerchantInfoAddressService.removeById(id);
        if(flag){
            return ResponseResult.successResult("删除成功",request);
        }
        return ResponseResult.failResult("删除失败");
    }
}

