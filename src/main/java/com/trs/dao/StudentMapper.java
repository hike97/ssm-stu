package com.trs.dao;

import com.trs.model.Student;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //登陆验证
    Student loginValidate(String username, String password);
    //获取列表
    List<Student> getList();
}