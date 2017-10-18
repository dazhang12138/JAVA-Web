package com.da.Photography.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Albums {
	private int a_id;		//专辑编号
	private String a_name;	//专辑名称
	private Date a_time;	//专辑创建时间
	private User user;	//创建用户
	private byte[] a_pic;//专辑封面
	private String a_profile;	//专辑简介
	private List<Picture> pictures;	//图片集
	public Albums() {
		user = new User();
		pictures = new ArrayList<>();
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	public Date getA_time() {
		return a_time;
	}
	public void setA_time(Date a_time) {
		this.a_time = a_time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public String getA_profile() {
		return a_profile;
	}
	public void setA_profile(String a_profile) {
		this.a_profile = a_profile;
	}
	public byte[] getA_pic() {
		return a_pic;
	}
	public void setA_pic(byte[] a_pic) {
		this.a_pic = a_pic;
	}
}
