package com.yyjz.icop.service;

import java.util.List;
import java.util.Map;

import org.bson.Document;
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
	
}
