package com.trs.service;

import com.trs.model.User;

/**
 * @author hike97
 * @create 2018-12-10 10:51
 * @desc
 **/
public interface UserService {

	User selectByUserName (String username);
}
