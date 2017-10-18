package com.da.Photography.dto;

public class Apply {
	private int ad_id;
	private User user;
	public Apply() {
		user = new User();
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
