package com.trs.dao;

import com.trs.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hike97
 * @create 2018-12-07 17:25
 * @desc
 **/
@Mapper
public interface UserMapper {

	@Select ("select * from t_user where username = #{username}")
	User selectByUserName(String username);

}
