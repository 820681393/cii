package com.purchase.controller.admin;

import com.purchase.model.AdminInfo;
import com.purchase.model.NoticeInfo;
import com.purchase.service.IAdminInfoService;
import com.purchase.service.INoticeInfoService;
import com.purchase.utils.StringUilts;
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
public class AdminLoginController {

    @Autowired
    IAdminInfoService iAdminInfoService;

    @Autowired
    INoticeInfoService iNoticeInfoService;

    @RequestMapping(value = "/login/login")
    public String login(Model model, HttpServletRequest request){
        return "admin/login";
    }
    @RequestMapping(value = "/index/index")
    public String index(Model model, HttpServletRequest request){
        List<NoticeInfo> noticeInfoList=iNoticeInfoService.findByTypeOrderByCreateTimeDesc(2);
        model.addAttribute("noticeInfoList",noticeInfoList);
        return "admin/index";
    }

    @RequestMapping(value = "/login/loginIng")
    public String loginIng(Model model, HttpServletRequest request,String userName,String passWord,String language){
        if(StringUilts.isEmptyOrNull(userName) || StringUilts.isEmptyOrNull(passWord)) {
            return "admin/login";
        }
        AdminInfo userAdmin=iAdminInfoService.findByUserNameAndPassWord(userName,passWord);
        if(userAdmin==null){
            return "admin/login";
        }
        request.getSession().setAttribute("userAdmin",userAdmin);
        request.getSession().setAttribute("language",language);
        if(userAdmin.getType().equals(2)){
            return "redirect:/merchants/index/index";
        }else{
            return "redirect:/admin/index/index";
        }
    }

    @RequestMapping(value = "/login/out")
    public String out(Model model, HttpServletRequest request){
        request.getSession().setAttribute("userAdmin",null);
        return "redirect:/admin/login/login";
    }
}
