package com.purchase.controller.merchants;

import com.purchase.model.NoticeInfo;
import com.purchase.service.INoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Miracle
 * @date 2020/12/13 22:45
 */
@Controller
@RequestMapping("/merchants")
public class MerchantInfoIndexController {

    @Autowired
    INoticeInfoService iNoticeInfoService;

    @RequestMapping(value = "/index/index")
    public String index(Model model, HttpServletRequest request){
        List<NoticeInfo> noticeInfoList=iNoticeInfoService.findByTypeOrderByCreateTimeDesc(3);
        model.addAttribute("noticeInfoList",noticeInfoList);
        return "merchants/index";
    }


}
