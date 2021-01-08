package com.purchase.controller.merchants;


import com.purchase.model.AdminInfo;
import com.purchase.model.CarInfo;
import com.purchase.model.MerchantInfoAddress;
import com.purchase.service.IMerchantInfoAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Musa
 * @since 2020-12-12
 */
@Controller
@RequestMapping("merchants")
@Api(value = "merchantInfoAddress", tags="商户系统权限：商户配送地址管理权限")
public class MerchantInfoAddressController {

    @Autowired
    IMerchantInfoAddressService iMerchantInfoAddressService;

    @RequestMapping(value = "/merchantInfoAddress/index")
    @ApiOperation(value = "查询商户收货地址")
    public String index(Model model, HttpServletRequest request){
        AdminInfo userAdmin= (AdminInfo) request.getSession().getAttribute("userAdmin");
        List<MerchantInfoAddress> merchantInfoAddressList=iMerchantInfoAddressService.findByMiid(userAdmin.getId());
        model.addAttribute("merchantInfoAddressList",merchantInfoAddressList);
        return "/merchants/merchantInfoAddress/merchantInfo_address_index";
    }
    @RequestMapping(value = "/merchantInfoAddress/insert")
    @ApiOperation(value = "进入商户收货地址新增")
    public String insert(Model model, HttpServletRequest request){
        return "/merchants/merchantInfoAddress/merchantInfo_address_insert";
    }


    @RequestMapping(value = "/merchantInfoAddress/insertIng")
    @ApiOperation(value = "商户收货地址新增")
    public String insertIng(Model model, HttpServletRequest request,MerchantInfoAddress merchantInfoAddress){
        AdminInfo userAdmin= (AdminInfo) request.getSession().getAttribute("userAdmin");
        model.addAttribute("userAdmin",userAdmin);
        merchantInfoAddress.setMiid(userAdmin.getId());
        iMerchantInfoAddressService.save(merchantInfoAddress);
        return "redirect:/merchants/merchantInfoAddress/index";
    }


    @RequestMapping(value = "/merchantInfoAddress/update")
    @ApiOperation(value = "进入商户收货地址修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        MerchantInfoAddress sqlMerchantInfoAddress=iMerchantInfoAddressService.getById(id);
        model.addAttribute("sqlMerchantInfoAddress",sqlMerchantInfoAddress);
        return "/merchants/merchantInfoAddress/merchantInfo_address_update";
    }


    @RequestMapping(value = "/merchantInfoAddress/updateIng")
    @ApiOperation(value = "商户收货地址新增修改")
    public String updateIng(Model model, HttpServletRequest request,MerchantInfoAddress merchantInfoAddress){
        AdminInfo userAdmin= (AdminInfo) request.getSession().getAttribute("userAdmin");
        model.addAttribute("userAdmin",userAdmin);
        merchantInfoAddress.setMiid(userAdmin.getId());
        iMerchantInfoAddressService.updateById(merchantInfoAddress);
        return "redirect:/merchants/merchantInfoAddress/index";
    }

    @RequestMapping(value = "/merchantInfoAddress/delete")
    @ApiOperation(value = "商户收货地址新增删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iMerchantInfoAddressService.removeById(id);
        return "redirect:/merchants/merchantInfoAddress/index";
    }
}

