package com.da.dabai.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 文件夹库实体类
 * @author Mr.Da
 *
 */
public class DbFilesEntity {
	/**
	 * 文件夹编号
	 */
	private String filesId;
	/**
	 * 文件夹所属用户编号
	 */
	private String userId;
	/**
	 * 文件夹名称
	 */
	private String foldersName;
	/**
	 * 此文件夹内的文件集合
	 */
	private Set<String> file;
	/**
	 * 文件夹创建时间
	 */
	private Date foldersStartTime;
	/**
	 * 文件夹最后修改时间
	 */
	private Date foldersEndTime;
	
	/**
	 * 无惨构造函数
	 */
	public DbFilesEntity() {
		file = new HashSet<>();
	}
	
	public String getFilesId() {
		return filesId;
	}
	public void setFilesId(String filesId) {
		this.filesId = filesId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFoldersName() {
		return foldersName;
	}
	public void setFoldersName(String foldersName) {
		this.foldersName = foldersName;
	}
	public Set<String> getFile() {
		return file;
	}
	public void setFile(Set<String> file) {
		this.file = file;
	}
	public Date getFoldersStartTime() {
		return foldersStartTime;
	}
	public void setFoldersStartTime(Date foldersStartTime) {
		this.foldersStartTime = foldersStartTime;
	}
	public Date getFoldersEndTime() {
		return foldersEndTime;
	}
	public void setFoldersEndTime(Date foldersEndTime) {
		this.foldersEndTime = foldersEndTime;
	}
	
}
