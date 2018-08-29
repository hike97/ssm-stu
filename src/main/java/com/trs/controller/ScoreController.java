package com.trs.controller;

import com.trs.BaseController;
import com.trs.model.Score;
import com.trs.model.Student;
import com.trs.service.ScoreService;
import com.trs.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/score/info")
public class ScoreController extends BaseController {
    @Resource
    private ScoreService scoreService;
    @Resource
    private StudentService studentService;
    //查看
    @GetMapping("/scores/{stuId}")
    public String getlist(@PathVariable Integer stuId){
        System.out.println(">>>>"+stuId);
        List<Score> scores = scoreService.getList(stuId);
        request.setAttribute("scores",scores);
        if (stuId==0){
            return "admin/score/list";
        }else {
            return "admin/score/mylist";
        }

    }
    //添加页面
    @GetMapping("/score")
    public String toAddPage(){
        List<Student> list = studentService.getList();
        if (list.size()>0&&list !=null){
            //System.out.println(list);
            request.setAttribute("stuList",list);
        }
        return "admin/score/add";
    }

    //添加
    @PostMapping("/score")
    public String add(Score score){
        score.setStatus(0);
        System.out.println(score);
        /*添加操作前 先删除 相同年份数据*/
        Integer year = score.getYear();
        Integer stuId = score.getStuId();
        scoreService.deleteByYear(year,stuId);
        scoreService.save(score);
        return "redirect:/score/info/scores/0";
    }

    //修改页面
    @GetMapping("/score/{id}")
    public String toEditPage(@PathVariable("id") Integer id){
        Score score = scoreService.selectById(id);
        request.setAttribute("score",score);
        List<Student> list = studentService.getList();
        if (list.size()>0&&list !=null){
            //System.out.println(list);
            request.setAttribute("stuList",list);
        }
        return "admin/score/add";
    }
    //修改操作
    @PutMapping("/score")
    public String updateEmployee(Score score){
        score.setStatus(0);
        //System.out.println("修改的学生数据："+score);
        scoreService.save(score);
        return "redirect:/score/info/scores/0";
    }

    //删除
    @DeleteMapping("/score/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        scoreService.delete(id);
        return "redirect:/score/info/scores/0";
    }
    //判断学生是否有成绩记录录入
    @PostMapping("isAdd")
    @ResponseBody
    public boolean isAdd(Integer stuId){
        List<Score> scores = scoreService.getList(stuId);
        return scores.size() > 0;
    }
}
