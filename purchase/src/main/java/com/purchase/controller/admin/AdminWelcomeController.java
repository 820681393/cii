package com.purchase.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Miracle
 * @date 2020/11/17 19:40
 */
@Controller
public class AdminWelcomeController {

    @RequestMapping(value = "/")
    public String welcome(Model model, HttpServletRequest request){
        return "admin/login";
    }
}
