package com.yyjz.icop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yyjz.icop.service.FilesService;
import com.yyjz.icop.service.impl.FilesServiceImpl;
import com.yyjz.icop.util.DateUtils;
import com.yyjz.icop.util.FileUtils;
import com.yyjz.icop.vo.DelFilesVo;
import com.yyjz.icop.vo.FilesVo;

@Controller
@RequestMapping("Files")
public class FilesController {
	
	FilesService filesService = new FilesServiceImpl();
	
	@RequestMapping(value="deleteFiles")
	@ResponseBody
	public boolean deleteFiles(@RequestBody DelFilesVo delFiles){
		filesService.deleteFolder(delFiles.getDeletefolder(),delFiles.getFolderPath(),delFiles.getUserId());
		filesService.deleteFile(delFiles.getDeletefile(),delFiles.getFolderPath(),delFiles.getUserId());
		return true;
	}
	
	@RequestMapping(value="newFolder")
	@ResponseBody
	public boolean newFolder(@RequestBody FilesVo files){
		boolean result = true;
		try {
			//物理创建文件夹
			String path = FileUtils.getPath(files.getFilePath(),files.getFolderName());
			FileUtils.createFolder(path);
			//保存信息到库
			filesService.saveFolder(files.getUserId(),files.getFilePath(),files.getFolderName());
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	@RequestMapping(value="getFiles")
	@ResponseBody
	public List<Map<String, Object>> getFiles(@RequestBody FilesVo files){
		Map<String, List<Document>> map = filesService.getUserFiles(files);
		List<Map<String, Object>> list = new ArrayList<>();
		List<Document> filesList = map.get("files");
		for (Document document : filesList) {
			Map<String, Object> m = new HashMap<>();
			m.put("_id", document.get("_id").toString());
			m.put("fileName", document.get("foldersName"));
			m.put("fileEndTime", DateUtils.DateOfString(document.getDate("foldersEndTime")));
			m.put("type", "0");
			list.add(m);
		}
		List<Document> fileList = map.get("file");
		for (Document document : fileList) {
			Map<String, Object> m = new HashMap<>();
			m.put("_id", document.get("_id").toString());
			m.put("fileName", document.get("fileName"));
			m.put("fileSize", document.get("fileSize"));
			m.put("fileEndTime", DateUtils.DateOfString(document.getDate("fileEndTime")));
			m.put("type", "1");
			list.add(m);
		}
		return list;
	}
	
	@RequestMapping(value="UploadFile")
	@ResponseBody
	public boolean uploadFile(@RequestParam("file") MultipartFile file,String path, String userName){
		boolean result = true;
		try {
			//文件存储到相对物理位置
			FileUtils.saveFile(file, path);
			//存放文件信息到库
			filesService.saveFile(file,path,userName);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
}
