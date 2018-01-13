package com.yyjz.icop.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.yyjz.icop.vo.FilesVo;

@Controller
@RequestMapping("Files")
public class FilesController {
	
	FilesService filesService = new FilesServiceImpl();
	
	@RequestMapping(value="newFolder")
	@ResponseBody
	public void newFolder(){
		
	}
	
	@RequestMapping(value="getFiles")
	@ResponseBody
	public List<Map<String, Object>> getFiles(@RequestBody FilesVo files){
		Map<String, List<Document>> map = filesService.getUserFiles(files);
		List<Map<String, Object>> list = new ArrayList<>();
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
		List<Document> filesList = map.get("files");
		for (Document document : filesList) {
			Map<String, Object> m = new HashMap<>();
			m.put("_id", document.get("_id").toString());
			m.put("fileName", document.get("foldersName"));
			m.put("fileEndTime", document.get("foldersEndTime"));
			m.put("type", "0");
			list.add(m);
		}
		return list;
	}
	
	@RequestMapping(value="UploadFile")
	@ResponseBody
	public void uploadFile(@RequestParam("file") MultipartFile file,String path, String userName){
		//文件存储到相对物理位置
		FileUtils.saveFile(file, path);
		//存放文件信息到库
		filesService.saveFile(file,path,userName);
	}
	
}
