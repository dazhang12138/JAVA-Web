package com.yyjz.icop.vo;

import org.bson.types.ObjectId;

public class FilesVo {
	/**
	 * 用户编号
	 */
	private ObjectId userId;
	/**
	 * 文件中间路径
	 */
	private String filePath;
	/**
	 * 文件夹名称
	 */
	private String folderName;
	
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
	public String getFolderName() {
		return folderName;
	}
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
}
