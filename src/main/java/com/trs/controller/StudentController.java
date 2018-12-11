package com.trs.controller;

import com.trs.BaseController;
import com.trs.model.Student;
import com.trs.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/stu/info")
public class StudentController extends BaseController {
    @Resource
    private StudentService studentService;
    //查看
    @GetMapping("/students")
    public String getlist(){
        List<Student> students = studentService.getList();
        request.setAttribute("students",students);
        return "admin/student/list";
    }
    //添加页面
    @GetMapping("/student")
    public String toAddPage(){
        return "admin/student/add";
    }

    //添加
    @PostMapping("/student")
    public String addEmp(Student student){
        /*初始密码为123456*/
        student.setStatus(0);
        student.setPassWord("123456");
        //System.out.println("保存的学生信息："+student);
        studentService.save(student);
        return "redirect:/stu/info/students";
    }

    //修改页面
    @GetMapping("/student/{id}")
    public String toEditPage(@PathVariable("id") Integer id){
        Student student = studentService.selectById(id);
        request.setAttribute("student",student);
        return "admin/student/add";
    }
    //修改操作
    @PutMapping("/student")
    public String updateEmployee(Student student){
        student.setStatus(0);
//        System.out.println("修改的学生数据："+student);
        studentService.save(student);
        return "redirect:/stu/info/students";
    }

    //删除
    @DeleteMapping("/student/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        studentService.delete(id);
        return "redirect:/stu/info/students";
    }
}
