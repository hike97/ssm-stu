package com.trs.service.impl;

import com.trs.dao.UserMapper;
import com.trs.model.User;
import com.trs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2018-12-10 10:51
 * @desc
 **/
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public User selectByUserName (String username) {
		return userMapper.selectByUserName (username);
	}
}
