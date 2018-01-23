package com.yyjz.icop.vo;

import java.util.Date;

import org.bson.types.ObjectId;

public class UserUpdateVo {
	//用户编号
	private ObjectId userId;
	//用户性别
	private String sex;
	//用户生日
	private Date birthday;
	//用户证件号
	private String idNumber;
	//用户手机号
    private String phoneNumber;
    //用户年龄
    private String age;
    
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
