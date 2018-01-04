package com.yyjz.icop.user.entity;


import java.util.Date;

import org.bson.types.ObjectId;
/**
 * 用户库实体类
 * @author Mr.Da
 *
 */
public class DbUserEntity {
	/**
	 * 用户编号
	 */
	private ObjectId userId;
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
	 * 用户邮箱
	 */
	private String userEmail;
	/**
	 * 用户创建时间
	 */
	private Date userStartTime;
	/**
	 * 用户存储文件大小,单位B
	 */
	private String filesSize;
	/**
	 * 用户存储文件最大值,单位B
	 */
	private String maxFilesSize;
	/**
	 * 上传限制
	 */
	private String uploadLimit;
	/**
	 * 转存限制
	 */
	private int archivedLimit;
	
	/**
	 * 上传数量限制
	 */
	private int bulkUploadLimit;
	
	/**
	 * 回收站限制
	 */
	private int garbageLimit;
	
	/**
	 * 账户权限
	 */
	private int limits;
	/**
	 * 用户存储文件夹集合
	 */
	private Object files;
	
	/**
	 * 无惨构造函数
	 */
	public DbUserEntity() {
		files = new Object();
		userId = new ObjectId();
		userStartTime = new Date();
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getUserStartTime() {
		return userStartTime;
	}

	public void setUserStartTime(Date userStartTime) {
		this.userStartTime = userStartTime;
	}

	public String getFilesSize() {
		return filesSize;
	}

	public void setFilesSize(String filesSize) {
		this.filesSize = filesSize;
	}

	public String getMaxFilesSize() {
		return maxFilesSize;
	}

	public void setMaxFilesSize(String maxFilesSize) {
		this.maxFilesSize = maxFilesSize;
	}

	public String getUploadLimit() {
		return uploadLimit;
	}

	public void setUploadLimit(String uploadLimit) {
		this.uploadLimit = uploadLimit;
	}

	public int getArchivedLimit() {
		return archivedLimit;
	}

	public void setArchivedLimit(int archivedLimit) {
		this.archivedLimit = archivedLimit;
	}

	public int getBulkUploadLimit() {
		return bulkUploadLimit;
	}

	public void setBulkUploadLimit(int bulkUploadLimit) {
		this.bulkUploadLimit = bulkUploadLimit;
	}

	public int getGarbageLimit() {
		return garbageLimit;
	}

	public void setGarbageLimit(int garbageLimit) {
		this.garbageLimit = garbageLimit;
	}

	public int getLimits() {
		return limits;
	}

	public void setLimits(int limits) {
		this.limits = limits;
	}

	public Object getFiles() {
		return files;
	}

	public void setFiles(Object files) {
		this.files = files;
	}	
	
}