package com.da.Photography.dto;

import java.util.Date;

public class Down {
	private int d_id;   //记录编号
	private User user;  //记录用户 必有
	private Picture pic;//下载图片 type为1的时候没有
	private Date d_date;//记录时间
	private int d_type; //记录类型1-签到，2-下载扣除积分,3-增加积分
	private String d_update;//记录内容 +n\-n
	public Down() {
		user = new User();
		pic = new Picture();
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Picture getPic() {
		return pic;
	}
	public void setPic(Picture pic) {
		this.pic = pic;
	}
	public Date getD_date() {
		return d_date;
	}
	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}
	public int getD_type() {
		return d_type;
	}
	public void setD_type(int d_type) {
		this.d_type = d_type;
	}
	public String getD_update() {
		return d_update;
	}
	public void setD_update(String d_update) {
		this.d_update = d_update;
	}
	
}
