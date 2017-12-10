package com.da.dabai.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * 回收站实体类对应Vo
 * @author Mr.Da
 *
 */
public class DbDustbinVo {
	/**
	 * 回收站编号
	 */
	private String dustbinId;
	/**
	 * 此回收站所属用户
	 */
	private DbUserVo user;
	/**
	 * 回收站内文件夹集合
	 */
	private Set<DbFilesVo> files;
	
	
	/**
	 * 无惨构造函数
	 */
	public DbDustbinVo() {
		user = new DbUserVo();
		files = new HashSet<>();
	}


	public String getDustbinId() {
		return dustbinId;
	}


	public void setDustbinId(String dustbinId) {
		this.dustbinId = dustbinId;
	}


	public DbUserVo getUser() {
		return user;
	}


	public void setUser(DbUserVo user) {
		this.user = user;
	}


	public Set<DbFilesVo> getFiles() {
		return files;
	}


	public void setFiles(Set<DbFilesVo> files) {
		this.files = files;
	}
	
}
