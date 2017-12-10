package com.da.dabai.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 文件夹实体类对应Vo
 * @author Mr.Da
 *
 */
public class DbFilesVo {
	/**
	 * 文件夹编号
	 */
	private String filesId;
	/**
	 * 文件夹所属用户
	 */
	private DbUserVo user;
	/**
	 * 文件夹名称
	 */
	private String foldersName;
	/**
	 * 此文件夹内的文件集合
	 */
	private Set<DbFileVo> file;
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
	public DbFilesVo() {
		user = new DbUserVo();
		file = new HashSet<>();
		foldersEndTime = new Date();
	}

	public String getFilesId() {
		return filesId;
	}

	public void setFilesId(String filesId) {
		this.filesId = filesId;
	}

	public DbUserVo getUser() {
		return user;
	}

	public void setUser(DbUserVo user) {
		this.user = user;
	}

	public String getFoldersName() {
		return foldersName;
	}

	public void setFoldersName(String foldersName) {
		this.foldersName = foldersName;
	}

	public Set<DbFileVo> getFile() {
		return file;
	}

	public void setFile(Set<DbFileVo> file) {
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
