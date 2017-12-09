package com.da.dabai.entity;

import java.math.BigInteger;
import java.util.Date;

import com.da.dabai.util.FileType;
/**
 * 文件库实体类
 * @author Mr.Da
 *
 */
public class DbFileEntity {
	/**
	 * 文件编号
	 */
	private String fileId;
	/**
	 * 用户编号
	 */
	private String userID;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件类型
	 */
	private FileType fileType;
	/**
	 * 文件存储路径
	 */
	private String filePath;
	/**
	 * 文件大小,单位B
	 */
	private BigInteger fileSize;
	/**
	 * 文件创建时间
	 */
	private Date fileStartTime;
	/**
	 * 文件最后修改时间
	 */
	private Date fileEndTime;
	
	/**
	 * 无惨构造函数
	 */
	public DbFileEntity() {
		fileType = FileType.OTHERS;
	}
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public BigInteger getFileSize() {
		return fileSize;
	}
	public void setFileSize(BigInteger fileSize) {
		this.fileSize = fileSize;
	}
	public Date getFileStartTime() {
		return fileStartTime;
	}
	public void setFileStartTime(Date fileStartTime) {
		this.fileStartTime = fileStartTime;
	}
	public Date getFileEndTime() {
		return fileEndTime;
	}
	public void setFileEndTime(Date fileEndTime) {
		this.fileEndTime = fileEndTime;
	}
	
}
