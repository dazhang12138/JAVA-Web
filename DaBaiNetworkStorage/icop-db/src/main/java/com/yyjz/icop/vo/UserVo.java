package com.yyjz.icop.vo;

/**
 * 用户VO
 * @author Mr.Da
 *
 */
public class UserVo {
	//用户登录名
	private String userName;
	//与用户登密码
	private String userPwd;
	//用户名
	private String name;
	//用户邮箱
	private String userEmail;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}
