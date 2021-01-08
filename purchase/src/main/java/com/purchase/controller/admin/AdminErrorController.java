package com.purchase.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Miracle
 * @date 2020/11/3 18:55
 */
@Controller
@RequestMapping("admin")
public class AdminErrorController {

    @RequestMapping(value = "/error/info")
    public String info(Model model, HttpServletRequest request, String code,String info){
        if(code==null){
            if(request.getAttribute("code1")!=null){
                code= (String) request.getAttribute("code1");
            }
        }
        if(info==null){
            if(request.getAttribute("info1")!=null){
                info= (String) request.getAttribute("info1");
            }
        }
        model.addAttribute("code",code);
        model.addAttribute("info",info);
        return "/admin/error_info";
    }
}
