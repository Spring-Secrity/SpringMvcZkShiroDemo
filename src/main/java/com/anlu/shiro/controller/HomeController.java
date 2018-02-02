package com.anlu.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @RequestMapping("/home")
//	@RequiresPermissions(value={"log:manage:*"})
    public ModelAndView home(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }
}
