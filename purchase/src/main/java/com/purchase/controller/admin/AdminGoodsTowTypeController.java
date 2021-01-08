package com.purchase.controller.admin;

import com.purchase.model.AdminInfo;
import com.purchase.model.CarInfo;
import com.purchase.model.GoodsOneType;
import com.purchase.model.GoodsTowType;
import com.purchase.service.IAdminInfoService;
import com.purchase.service.IGoodsOneTypeService;
import com.purchase.service.IGoodsTowTypeService;
import com.purchase.utils.ResponseResult;
import com.purchase.utils.StringUilts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Miracle
 * @date 2020/11/3 18:54
 */
@Controller
@RequestMapping("admin")
@Api(value = "goodsTowType", tags="二级商品分类权限")
public class AdminGoodsTowTypeController {

    @Autowired
    IGoodsTowTypeService iGoodsTowTypeService;

    @Autowired
    IGoodsOneTypeService iGoodsOneTypeService;

    @RequestMapping(value = "/goodsTowType/index")
    @ApiOperation(value = "查询二级商品分类")
    public String index(Model model, HttpServletRequest request){
        List<GoodsTowType> goodsTowTypeList =iGoodsTowTypeService.list();
        List<GoodsOneType> goodsOneTypeList =iGoodsOneTypeService.list();
        for (GoodsTowType goodsTowType : goodsTowTypeList){
            for (GoodsOneType goodsOneType : goodsOneTypeList){
                if(goodsTowType.getGoid().equals(goodsOneType.getId())){
                    goodsTowType.setGoodsOneTypeName(goodsOneType.getName());
                }
            }
        }
        model.addAttribute("goodsTowTypeList",goodsTowTypeList);
        return "/admin/goodsType/goods_tow_type_index";
    }

    @RequestMapping(value = "/goodsTowType/insert")
    @ApiOperation(value = "进入二级商品新增")
    public String insert(Model model, HttpServletRequest request,Integer goid){
        model.addAttribute("goid",goid);
        return "/admin/goodsType/goods_tow_type_insert";
    }

    @RequestMapping(value = "/goodsTowType/insertIng")
    @ApiOperation(value = "二级商品新增")
    public String insertIng(Model model, HttpServletRequest request,GoodsTowType goodsTowType){
        iGoodsTowTypeService.save(goodsTowType);
        return "redirect:/admin/goodsOneType/index";
    }

    @RequestMapping(value = "/goodsTowType/update")
    @ApiOperation(value = "进入二级商品修改")
    public String update(Model model, HttpServletRequest request,Integer id){
        GoodsTowType goodsTowType=iGoodsTowTypeService.getById(id);
        model.addAttribute("goodsTowType",goodsTowType);
        return "/admin/goodsType/goods_tow_type_update";
    }

    @RequestMapping(value = "/goodsTowType/updateIng")
    @ApiOperation(value = "二级商品修改")
    public String updateIng(Model model, HttpServletRequest request,GoodsTowType goodsTowType){
        iGoodsTowTypeService.updateById(goodsTowType);
        return "redirect:/admin/goodsOneType/index";
    }

    @RequestMapping(value = "/goodsTowType/delete")
    @ApiOperation(value = "二级商品删除")
    public String delete(Model model, HttpServletRequest request,Integer id){
        iGoodsTowTypeService.removeById(id);
        return "redirect:/admin/goodsOneType/index";
    }

}
