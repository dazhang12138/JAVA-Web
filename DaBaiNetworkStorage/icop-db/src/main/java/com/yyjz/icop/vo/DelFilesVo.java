package com.yyjz.icop.vo;

import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;

public class DelFilesVo {
	//删除文件夹集合
	private Set<Map<String, Object>> deletefolder;
	//删除文件集合
	private Set<Map<String, Object>> deletefile;
	//需要删除的位置
	private String folderPath;
	//用户编号
	private ObjectId userId;
	
	
	public Set<Map<String, Object>> getDeletefolder() {
		return deletefolder;
	}
	public void setDeletefolder(Set<Map<String, Object>> deletefolder) {
		this.deletefolder = deletefolder;
	}
	public Set<Map<String, Object>> getDeletefile() {
		return deletefile;
	}
	public void setDeletefile(Set<Map<String, Object>> deletefile) {
		this.deletefile = deletefile;
	}
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	
}
