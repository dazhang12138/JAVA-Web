package com.da.Photography.dto;

import java.util.Date;

public class Picture {
	private int p_id;		//图片编号
	private String p_name;	//图片名称
	private Date p_time;	//拍摄时间
	private byte[] p_pic;	//图片
	private int p_price;	//积分
	private String p_profile;	//图片简介
	private Albums albums;	//所属专辑
	public Picture() {
		albums = new Albums();
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Date getP_time() {
		return p_time;
	}
	public void setP_time(Date p_time) {
		this.p_time = p_time;
	}
	public byte[] getP_pic() {
		return p_pic;
	}
	public void setP_pic(byte[] p_pic) {
		this.p_pic = p_pic;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public Albums getAlbums() {
		return albums;
	}
	public void setAlbums(Albums albums) {
		this.albums = albums;
	}
	public String getP_profile() {
		return p_profile;
	}
	public void setP_profile(String p_profile) {
		this.p_profile = p_profile;
	}
}
