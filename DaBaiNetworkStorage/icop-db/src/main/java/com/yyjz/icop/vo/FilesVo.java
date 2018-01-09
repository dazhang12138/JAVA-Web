package com.yyjz.icop.vo;

import org.bson.types.ObjectId;

public class FilesVo {
	private ObjectId userId;
	private String filePath;
	
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
