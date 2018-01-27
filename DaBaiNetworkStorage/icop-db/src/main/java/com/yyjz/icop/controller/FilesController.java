package com.yyjz.icop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yyjz.icop.pubapp.platform.query.QuerySchema;
import com.yyjz.icop.service.FilesService;
import com.yyjz.icop.service.impl.FilesServiceImpl;
import com.yyjz.icop.util.DateUtils;
import com.yyjz.icop.util.FileUtils;
import com.yyjz.icop.vo.DelFilesVo;
import com.yyjz.icop.vo.FilesVo;
import com.yyjz.icop.vo.QueryFilesVo;

/**
 * 处理文件相关的Controller类
 * @author Mr.Da
 */
@Controller
@RequestMapping("Files")
public class FilesController {
	/**
	 * 文件处理Service
	 */
	FilesService filesService = new FilesServiceImpl();
	/**
	 * 删除文件功能
	 * @param delFiles 删除文件Vo，包含需要删除的文件集合、文件夹集合、用户编号及删除路径
	 * @return 返回true
	 */
	@RequestMapping(value="deleteFiles")
	@ResponseBody
	public Document deleteFiles(@RequestBody DelFilesVo delFiles){
		Document result = new Document();
		try {
			filesService.deleteFolder(delFiles.getDeletefolder(),delFiles.getFolderPath(),delFiles.getUserId());
			Document userData = filesService.deleteFile(delFiles.getDeletefile(),delFiles.getFolderPath(),delFiles.getUserId());
			result.append("data", userData);
		} catch (Exception e) {
			result.append("result", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 创建新文件夹
	 * @param files 文件Vo，包含用户编号、文件夹路径及文件夹名称
	 * @return 返回是否成功创建新建文件夹，返回true与false。
	 */
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
	
	/**
	 * 获取文件列表
	 * @param files 文件Vo，包含用户编号、文件夹路径及文件夹名称
	 * @return 返回文件夹路径下的存储文件与文件夹集合
	 */
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
			m.put("fileType", document.get("fileType"));
			list.add(m);
		}
		return list;
	}
	
	/**
	 * 上传文件
	 * @param file 文件
	 * @param path 存储地址
	 * @param userName 用户名
	 * @return 返回是否成功上传,true与false
	 */
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
	
	@RequestMapping(value="getUserFileTypeNumber")
	@ResponseBody
	public List<Map<String, Object>> getUserFileTypeNumber(@RequestBody FilesVo files){
		Map<String, Long> userFileAllTypeSize = filesService.getUserFileAllTypeSize(files.getUserId());
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("name", "图片");
		map.put("size", userFileAllTypeSize.get("picture"));
		list.add(map);
		map = new HashMap<>();
		map.put("name", "视频");
		map.put("size", userFileAllTypeSize.get("video"));
		list.add(map);
		map = new HashMap<>();
		map.put("name", "音频");
		map.put("size", userFileAllTypeSize.get("music"));
		list.add(map);
		map = new HashMap<>();
		map.put("name", "文档");
		map.put("size", userFileAllTypeSize.get("documents"));
		list.add(map);
		map = new HashMap<>();
		map.put("name", "种子");
		map.put("size", userFileAllTypeSize.get("seed"));
		list.add(map);
		map = new HashMap<>();
		map.put("name", "其他");
		map.put("size", userFileAllTypeSize.get("others"));
		list.add(map);
		map = new HashMap<>();
		map.put("name", "未使用");
		map.put("size", userFileAllTypeSize.get("unAll"));
		list.add(map);
		return list;
	}
	
	
	@RequestMapping(value="queryFiles")
	@ResponseBody
	public List<Map<String, Object>> queryFiles(@RequestBody QueryFilesVo files){
		List<Map<String, Object>> list = new ArrayList<>();
		List<Document> queryFiles = filesService.queryFiles(files.getUserId(),files.getCondition());
		for (Document document : queryFiles) {
			Map<String, Object> m = new HashMap<>();
			m.put("_id", document.get("_id").toString());
			m.put("fileName", document.get("fileName"));
			m.put("fileSize", document.get("fileSize"));
			m.put("fileEndTime", DateUtils.DateOfString(document.getDate("fileEndTime")));
			m.put("type", "1");
			m.put("fileType", document.get("fileType"));
			list.add(m);
		}
		return list;
	}
	
}
