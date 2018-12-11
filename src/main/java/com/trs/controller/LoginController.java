package com.trs.controller;

import com.trs.BaseController;
import com.trs.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("login")
    public ModelAndView loginView() {
        return new ModelAndView("/admin/login");
    }

    @PostMapping(value = "login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember",required = false) String remember
                        ,HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        // 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String msg = null;
        try {
                log.info ("rememberMe:{}",remember);
                // rememberme
                if (!StringUtils.isEmpty (remember)){
                    token.setRememberMe(true);
                }
                // 执行登录.
                subject.login(token);
            if (subject.isAuthenticated ()){
                //获取身份信息
                User user = (User) subject.getPrincipal();
                session.setAttribute ("loginUser",user);
                return "redirect:/admin/main.jsp";
            }
            } // 所有认证时异常的父类.
              catch (IncorrectCredentialsException e) {
                msg = "登录密码错误." + token.getPrincipal();
            } catch (LockedAccountException e) {
                msg = "帐号已被锁定." + token.getPrincipal();
            } catch (DisabledAccountException e) {
                msg = "帐号已被禁用." + token.getPrincipal();
            } catch (ExpiredCredentialsException e) {
                msg = "帐号已过期. " + token.getPrincipal();
            } catch (UnknownAccountException e) {
                msg = "帐号不存在." + token.getPrincipal();
            } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！" + e.getMessage();
            }
        request.setAttribute ("msg",msg);
        return "redirect:/admin/login";
    }

}
