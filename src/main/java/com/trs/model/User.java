package com.trs.model;

import java.io.Serializable;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2018-12-07 17:20
 * @desc 用户表
 **/

public class User implements Serializable{

	private Integer id;

	private String userName;

	private String passWord;

	private String realName;

	public String getRealName () {
		return realName;
	}

	public void setRealName (String realName) {
		this.realName = realName;
	}

	public Integer getId () {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getUserName () {
		return userName;
	}

	public void setUserName (String userName) {
		this.userName = userName;
	}

	public String getPassWord () {
		return passWord;
	}

	public void setPassWord (String passWord) {
		this.passWord = passWord;
	}
}
