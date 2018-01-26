package com.yyjz.icop.service;

import org.bson.Document;

import com.yyjz.icop.vo.UserUpdatePwdVo;
import com.yyjz.icop.vo.UserUpdateVo;
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

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	Document updateUser(UserUpdateVo user);

	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	String alterUserPwd(UserUpdatePwdVo user);
	
}
