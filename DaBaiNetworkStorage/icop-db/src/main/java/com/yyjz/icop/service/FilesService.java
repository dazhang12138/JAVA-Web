package com.yyjz.icop.service;

import org.bson.Document;
import org.springframework.web.multipart.MultipartFile;

import com.yyjz.icop.vo.FilesVo;

public interface FilesService {

	/**
	 * 查询用户文件夹信息
	 * @param files
	 * @return
	 */
	Document getUserFiles(FilesVo files);

	Document saveFile(MultipartFile file, String path, String userName);
	
	
}
