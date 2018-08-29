package com.trs.service.impl;

import com.trs.dao.StudentMapper;
import com.trs.model.Student;
import com.trs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    public Student getById(Integer id){
       return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student loginValidate(String userName, String passWord) {
        return studentMapper.loginValidate(userName,passWord);
    }

    @Override
    public List<Student> getList() {
        return studentMapper.getList();
    }

    @Override
    public void save(Student student) {
        Integer id = student.getId();
        if(!StringUtils.isEmpty(id)){
            studentMapper.updateByPrimaryKey(student);
        }else {
            studentMapper.insert(student);
        }

    }

    @Override
    public void delete(Integer id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Student selectById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }


}
