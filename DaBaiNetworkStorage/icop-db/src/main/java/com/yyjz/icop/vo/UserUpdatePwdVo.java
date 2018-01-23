package com.yyjz.icop.vo;

import org.bson.types.ObjectId;

public class UserUpdatePwdVo {
	//用户编号
	private ObjectId userId;
	//用户密码
	private String userpwd;
	//更新密码
	private String pwd1;
	
	
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getPwd1() {
		return pwd1;
	}
	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}
}
