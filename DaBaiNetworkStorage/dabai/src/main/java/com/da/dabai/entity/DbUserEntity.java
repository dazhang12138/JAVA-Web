package com.da.dabai.entity;


import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 用户库实体类
 * @author Mr.Da
 *
 */
public class DbUserEntity {
	/**
	 * 用户编号
	 */
	private String userId;
	/**
	 * 用户登录名
	 */
	private String userName;
	/**
	 * 用户登录密码
	 */
	private String userPwd;
	/**
	 * 用户昵称
	 */
	private String name;
	/**
	 * 用户存储文件大小,单位B
	 */
	private BigInteger filesSize;
	/**
	 * 用户存储文件最大值,单位B
	 */
	private BigInteger maxFilesSize;
	/**
	 * 用户存储文件夹集合
	 */
	private Set<String> files;
	/**
	 * 用户创建时间
	 */
	private Date userStartTime;
		
	/**
	 * 无惨构造函数
	 */
	public DbUserEntity() {
		files = new HashSet<>();
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getFilesSize() {
		return filesSize;
	}
	public void setFilesSize(BigInteger filesSize) {
		this.filesSize = filesSize;
	}
	public BigInteger getMaxFilesSize() {
		return maxFilesSize;
	}
	public void setMaxFilesSize(BigInteger maxFilesSize) {
		this.maxFilesSize = maxFilesSize;
	}
	public Set<String> getFiles() {
		return files;
	}
	public void setFiles(Set<String> files) {
		this.files = files;
	}
	public Date getUserStartTime() {
		return userStartTime;
	}
	public void setUserStartTime(Date userStartTime) {
		this.userStartTime = userStartTime;
	}
	
	
	
}