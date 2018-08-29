package com.trs.service.impl;

import com.trs.dao.ScoreMapper;
import com.trs.model.Score;
import com.trs.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("scoreService")
@Transactional
public class ScoreServiceImpl implements ScoreService {

    @Resource
    ScoreMapper scoreMapper;

    public Score getById(Integer id){
       return scoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public Score loginValidate(String userName, String passWord) {
        return scoreMapper.loginValidate(userName,passWord);
    }

    @Override
    public List<Score> getList(Integer stuId) {
        return scoreMapper.getList(stuId);
    }

    @Override
    public void save(Score score) {
        Integer id = score.getId();
        if(!StringUtils.isEmpty(id)){
            //修改操作
            score.setUpdateTime(new Date());
            scoreMapper.updateByPrimaryKey(score);
        }else {
            //添加操作
            score.setCreateTime(new Date());
            scoreMapper.insert(score);
        }

    }

    @Override
    public void delete(Integer id) {
        scoreMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Score selectById(Integer id) {
        return scoreMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteByYear(Integer year, Integer stuId) {
        scoreMapper.deleteByYear(year,stuId);
    }


}
