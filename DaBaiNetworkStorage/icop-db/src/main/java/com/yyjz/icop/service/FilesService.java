package com.yyjz.icop.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import com.yyjz.icop.vo.FilesVo;

public interface FilesService {

	/**
	 * 查询用户文件夹信息
	 * @param files
	 * @return
	 */
	Map<String, List<Document>> getUserFiles(FilesVo files);

	/**
	 * 上传文件
	 * @param file
	 * @param path
	 * @param userName
	 */
	void saveFile(MultipartFile file, String path, String userName);

	/**
	 * 保存文件夹
	 * @param userId
	 * @param filePath
	 * @param foldderName
	 */
	void saveFolder(ObjectId userId, String filePath, String foldderName);

	/**
	 * 删除文件夹
	 * @param deletefolder
	 * @param objectId 
	 * @param string 
	 * @return
	 */
	void deleteFolder(Set<Map<String, Object>> deletefolder, String path, ObjectId objectId);

	/**
	 * 删除文件
	 * @param deletefile
	 * @param objectId 
	 * @param string 
	 * @return
	 */
	Document deleteFile(Set<Map<String, Object>> deletefile, String path, ObjectId objectId);

	/**
	 * 获取用户的所有文件的不同类型的大小
	 * @param userId
	 * @return
	 */
	Map<String, Long> getUserFileAllTypeSize(ObjectId userId);

	/**
	 * 通过查询条件查询用户下的相关文件信息
	 * @param userId
	 * @param condition
	 * @return
	 */
	List<Document> queryFiles(ObjectId userId, List<Map<String, Object>> condition);
	
}
