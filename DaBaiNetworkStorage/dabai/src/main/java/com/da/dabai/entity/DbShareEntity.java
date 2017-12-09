package com.da.dabai.entity;
/**
 * 分享库实体类
 * @author Mr.Da
 *
 */
public class DbShareEntity {
	/**
	 * 分享编号
	 */
	private String shareId;
	/**
	 * 此分享所属用户编号
	 */
	private String userId;
	/**
	 * 分享文件的编号，
	 * 取决于shareFileType的值，
	 * shareFileType = true : fId = 文件编号
	 * shareFileType = false : fId = 文件夹编号
	 */
	private String fId;
	/**
	 * 分享地址
	 */
	private String sharePath;
	/**
	 * 分享密码,两位数字和两位小写字母随机组合
	 */
	private String sharePwd;
	/**
	 * 分享类型,
	 * shareType = true : 私密分享
	 * shareType = false : 公共分享
	 */
	private Boolean shareType;
	/**
	 * 分享文件类型,
	 * shareFileType = true : 分享为文件
	 * shareFileType = false : 分享为文件夹
	 */
	private Boolean shareFileType;
	
	/**
	 * 无惨构造函数
	 */
	public DbShareEntity() {
		
	}
	
	
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getSharePath() {
		return sharePath;
	}
	public void setSharePath(String sharePath) {
		this.sharePath = sharePath;
	}
	public String getSharePwd() {
		return sharePwd;
	}
	public void setSharePwd(String sharePwd) {
		this.sharePwd = sharePwd;
	}
	public Boolean getShareType() {
		return shareType;
	}
	public void setShareType(Boolean shareType) {
		this.shareType = shareType;
	}
	public Boolean getShareFileType() {
		return shareFileType;
	}
	public void setShareFileType(Boolean shareFileType) {
		this.shareFileType = shareFileType;
	}
	
}
