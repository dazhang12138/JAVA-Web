package com.da.Photography.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private int u_id;		//用户编号
	private String u_name;  //用户姓名
	private String u_uname; //用户名
	private String u_pwd;   //密码
	private String u_phone; //电话
	private String u_email; //邮箱
	private int u_price;    //积分
	private String u_role;    //角色 0-管理员 1-普通用户
	private Double u_balance;//余额
	private int u_signday; //连签天数
	private Date u_signdate; //上次签到日期
	private List<Albums> albums; //专辑
	
	public User() {
		albums = new ArrayList<>();
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_uname() {
		return u_uname;
	}
	public void setU_uname(String u_uname) {
		this.u_uname = u_uname;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public int getU_price() {
		return u_price;
	}
	public void setU_price(int u_price) {
		this.u_price = u_price;
	}
	public String getU_role() {
		return u_role;
	}
	public void setU_role(String u_role) {
		this.u_role = u_role;
	}
	public Double getU_balance() {
		return u_balance;
	}
	public void setU_balance(Double u_balance) {
		this.u_balance = u_balance;
	}
	public List<Albums> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Albums> albums) {
		this.albums = albums;
	}
	public int getU_signday() {
		return u_signday;
	}
	public void setU_signday(int u_signday) {
		this.u_signday = u_signday;
	}
	public Date getU_signdate() {
		return u_signdate;
	}
	public void setU_signdate(Date u_signdate) {
		this.u_signdate = u_signdate;
	}
	
}
