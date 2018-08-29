package com.trs.service;

import com.trs.model.Score;

import java.util.List;

public interface ScoreService {
    Score loginValidate(String userName, String passWord);

    List<Score> getList(Integer stuId);

    void save(Score student);

    void delete(Integer id);

    Score selectById(Integer id);

    void deleteByYear(Integer year, Integer stuId);
}
