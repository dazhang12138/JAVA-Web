package com.yyjz.icop.service;

import org.bson.Document;

import com.yyjz.icop.vo.UserVo;

public interface UserService {

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	Document loginUser(UserVo user);

	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	boolean registerUser(UserVo user);

	/**
	 * 校验用户信息
	 * @param document 校验信息
	 * @return
	 */
	boolean verifyUserByUserNameAndName(Document document);
	
}
