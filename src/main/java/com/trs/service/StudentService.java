package com.trs.service;

import com.trs.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

    Student getById(Integer id);

    Student loginValidate(String userName, String passWord);

    List<Student> getList();

    void save(Student student);

    void delete(Integer id);

    Student selectById(Integer id);
}
