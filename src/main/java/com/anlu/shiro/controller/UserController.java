package com.anlu.shiro.controller;

import com.anlu.shiro.bean.User;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
@RequiresAuthentication
public class UserController {
    @RequestMapping("/home")
    @RequiresPermissions({"user:*"}) // 如果希望在授权时开启权限检查，必须设置org.apache.shiro.realm.jdbc.JdbcRealm属性permissionsLookupEnabled为true，否则只检查角色
    public ModelAndView home(HttpServletRequest req) {
        //boolean check =	SecurityUtils.getSubject().isPermitted("user:*");
        //System.out.println("用户管理权限检查结果：" + check);
        return new ModelAndView("user");
    }


    @RequestMapping("/addUser")
    @ResponseBody
    @RequiresAuthentication
    @RequiresPermissions({"user:*"}) // 控制到权限级别
    public Object addUser(HttpServletRequest req) {
        System.out.println("验证操作权限");
        return new User();
    }
}
