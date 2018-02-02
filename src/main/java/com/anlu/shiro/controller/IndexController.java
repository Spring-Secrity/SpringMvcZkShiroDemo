package com.anlu.shiro.controller;

import com.anlu.shiro.utils.MyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) {
//        req.getSession().setAttribute("test", new MySessionListener());
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest req, HttpServletResponse resp,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam(value="rememberMe", required=false) boolean rememberMe)throws  Exception{
        Subject currentUser = SecurityUtils.getSubject();
        Exception ex = null;
        if(!currentUser.isAuthenticated()){//如果用户已经验证通过
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                ex = e;
                logger.error(String.format("user not found: %s", username), e);
            } catch(IncorrectCredentialsException e) {
                ex = e;
                logger.error(String.format("user: %s pwd: %s error", username, password), e);
            } catch (ConcurrentAccessException e) {
                ex = e;
                logger.error(String.format("user has been authenticated: %s", username), e);
            } catch (AuthenticationException e) {
                ex = e;
                logger.error(String.format("account except: %s", username), e);
            }

        }
        if(ex != null) {
            req.setAttribute("ex", ex);
            return "index";
        }
        return MyUtil.redirect(req, resp, "/home");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SecurityUtils.getSubject().logout();
        req.getSession().invalidate();
        return MyUtil.redirect(req, resp, "/index");
    }
}
