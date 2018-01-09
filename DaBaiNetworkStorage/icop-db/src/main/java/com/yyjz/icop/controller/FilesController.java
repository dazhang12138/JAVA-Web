package com.yyjz.icop.controller;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yyjz.icop.service.FilesService;
import com.yyjz.icop.service.impl.FilesServiceImpl;
import com.yyjz.icop.util.FileUtils;
import com.yyjz.icop.vo.FilesVo;

@Controller
@RequestMapping("Files")
public class FilesController {
	
	FilesService filesService = new FilesServiceImpl();
	
	@RequestMapping(value="getFiles")
	@ResponseBody
	public Document getFiles(@RequestBody FilesVo files){
		Document filesDoc = filesService.getUserFiles(files);
		return null;
	}
	
	@RequestMapping(value="UploadFile")
	@ResponseBody
	public void uploadFile(@RequestParam("file") MultipartFile file,String path, String userName){
		//文件存储到相对物理位置
		FileUtils.saveFile(file, path);
		//存放文件信息到库
		Document fileDoc = filesService.saveFile(file,path,userName);
	}
	
}
