package com.trs.dao;

import com.trs.model.Score;

import java.util.List;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    Score loginValidate(String userName, String passWord);

    List<Score> getList(Integer stuId);

    void deleteByYear(Integer year, Integer stuId);
}