package com.trs.controller;

import com.trs.BaseController;
import com.trs.model.Student;
import com.trs.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController{
    @Resource
    private  StudentService studentService;

    @GetMapping("login")
    public ModelAndView loginView() {
        return new ModelAndView("/admin/login");
    }

    //登陆
    @PostMapping(value = "login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String passWord,
                        HttpSession session){
//        System.out.println("用户名："+userName);
//        System.out.println("密码："+passWord);
        Student student = studentService.loginValidate(userName, passWord);
        if (student!=null){
            session.setAttribute("loginUser",student);
            return "/admin/main";
        }else {
            request.setAttribute("msg","用户名或密码错误");
            return "/admin/login";
        }

    }
    //退出
    @RequestMapping("/logout")
    public String  logout(){
        //移除session
        request.getSession().invalidate();
        return "redirect:/admin/login";
    }
}
