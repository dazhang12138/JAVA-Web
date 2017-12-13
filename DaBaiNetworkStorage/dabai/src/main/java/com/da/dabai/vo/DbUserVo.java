package com.da.dabai.vo;


import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
/**
 * 用户实体类对应Vo
 * @author Mr.Da
 *
 */
public class DbUserVo {
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
	private Set<DbFilesVo> files;
	/**
	 * 用户创建时间
	 */
	private Date userStartTime;
	/**
	 * 用户邮箱
	 */
	private String userEmail;
		
	/**
	 * 上传限制
	 */
	private BigInteger uploadLimit;
	
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
	 * 无惨构造函数
	 */
	public DbUserVo() {
		files = new HashSet<>();
		filesSize = new BigInteger("0");
		maxFilesSize = new BigInteger("0");
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
	public Set<DbFilesVo> getFiles() {
		return files;
	}
	public void setFiles(Set<DbFilesVo> files) {
		this.files = files;
	}
	public Date getUserStartTime() {
		return userStartTime;
	}
	public void setUserStartTime(Date userStartTime) {
		this.userStartTime = userStartTime;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public BigInteger getUploadLimit() {
		return uploadLimit;
	}


	public void setUploadLimit(BigInteger uploadLimit) {
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


	@Override
	public String toString() {
		return "DbUserVo [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", name=" + name
				+ ", filesSize=" + filesSize + ", maxFilesSize=" + maxFilesSize + ", files=" + files
				+ ", userStartTime=" + userStartTime + ", userEmail=" + userEmail + "]";
	}
	
	
	
}