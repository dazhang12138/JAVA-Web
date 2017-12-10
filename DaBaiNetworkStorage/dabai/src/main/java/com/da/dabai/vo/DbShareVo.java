package com.da.dabai.vo;
/**
 * 分享实体类对应Vo
 * @author Mr.Da
 * @param <T>
 *
 */
public class DbShareVo<T> {
	/**
	 * 分享编号
	 */
	private String shareId;
	/**
	 * 此分享所属用户
	 */
	private DbUserVo user;
	/**
	 * 分享文件的编号，
	 * 取决于shareFileType的值，
	 * shareFileType = true : fileOrFiles = 文件
	 * shareFileType = false : fileOrFiles = 文件夹
	 */
	private T fileOrFiles;
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
	public DbShareVo() {
		user = new DbUserVo();
		shareType = true;
		shareFileType = true;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public DbUserVo getUser() {
		return user;
	}

	public void setUser(DbUserVo user) {
		this.user = user;
	}

	public T getFileOrFiles() {
		return fileOrFiles;
	}

	public void setFileOrFiles(T fileOrFiles) {
		this.fileOrFiles = fileOrFiles;
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
