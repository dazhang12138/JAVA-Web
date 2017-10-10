package com.da.Photography.dto;

import java.util.Date;

public class Down {
	private int d_id;
	private User user;
	private Picture pic;
	private Date d_date;
	
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
}
