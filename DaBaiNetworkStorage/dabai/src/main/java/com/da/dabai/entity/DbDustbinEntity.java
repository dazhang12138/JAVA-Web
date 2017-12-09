package com.da.dabai.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 回收站库实体类
 * @author Mr.Da
 *
 */
public class DbDustbinEntity {
	/**
	 * 回收站编号
	 */
	private String dustbinId;
	/**
	 * 此回收站所属用户编号
	 */
	private String userId;
	/**
	 * 回收站内文件夹集合
	 */
	private Set<String> files;
	
	
	/**
	 * 无惨构造函数
	 */
	public DbDustbinEntity() {
		files = new HashSet<>();
	}
	
	public String getDustbinId() {
		return dustbinId;
	}
	public void setDustbinId(String dustbinId) {
		this.dustbinId = dustbinId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<String> getFiles() {
		return files;
	}
	public void setFiles(Set<String> files) {
		this.files = files;
	}
	
}
