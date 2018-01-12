package com.yyjz.icop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import com.yyjz.icop.dao.FilesDao;
import com.yyjz.icop.dao.UserDao;
import com.yyjz.icop.dao.impl.FilesDaoImpl;
import com.yyjz.icop.dao.impl.UserDaoImpl;
import com.yyjz.icop.service.FilesService;
import com.yyjz.icop.util.FileUtils;
import com.yyjz.icop.util.GetFileType;
import com.yyjz.icop.vo.FilesVo;

public class FilesServiceImpl implements FilesService {
	
	FilesDao filesDao = new FilesDaoImpl();
	UserDao userDao = new UserDaoImpl();

	@Override
	public Map<String, List<Document>> getUserFiles(FilesVo files) {
		Map<String, List<Document>> map = new HashMap<>();
		Document queryUser = userDao.queryUser(new Document("_id",files.getUserId()));
		Document hierarchyFilesByUserDoc = getHierarchyFilesByUserDoc((Document) queryUser.get("files"),files.getFilePath());
		List<Document> appendFileDocFiles = appendFileDoc(hierarchyFilesByUserDoc,true);
		List<Document> appendFileDocFile = appendFileDoc(hierarchyFilesByUserDoc,false);
		map.put("files", appendFileDocFiles);
		map.put("file", appendFileDocFile);
		return map;
	}

	@Override
	public void saveFile(MultipartFile file, String path, String userName) {
		try {
			Document queryUser = userDao.queryUser(new Document("name",userName));
			String fileName = file.getOriginalFilename();
			long fileSize = file.getSize();
			Document fileDoc = new Document();
			fileDoc.append("_id", new ObjectId());
			fileDoc.append("userId", queryUser.get("_id"));
			fileDoc.append("fileName", fileName);
			fileDoc.append("fileType", GetFileType.getType(fileName));
			fileDoc.append("filePath", FileUtils.getPath(path,fileName));
			fileDoc.append("fileSize", String.valueOf(fileSize));
			Date d = new Date();
			fileDoc.append("fileStartTime", d);
			fileDoc.append("fileEndTime", d);
			fileDoc.append("MD5", FileUtils.getFileMD5(path,fileName));
			filesDao.saveFile(fileDoc);
			updateUserFiles(queryUser,fileDoc.get("_id"),path,fileName);
			Document filter = new Document("_id",queryUser.get("_id"));
			int userFileSize = Integer.valueOf((String) queryUser.get("fileSize"));
			userFileSize += fileSize;
			queryUser.append("fileSize", String.valueOf(userFileSize));
			Document update = new Document("$set",queryUser);
//			userDao.updateAllFilterUser(document, queryUser);
			userDao.updateAllFilterUser(filter, update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据位置路径以及文件名称更新用户文档的文件存储
	 * @param files
	 * @param fileid
	 * @param path
	 * @param fileName
	 * @return
	 */
	private Document updateUserFiles(Document files,Object fileid, String path, String fileName) {
		String[] split = path.split("/");
		int i = 0;
		Document files_new = files;
		while(i < split.length){
			if(i == 0){
				files_new = (Document) files_new.get("files");
				if(files_new.get("filesName").equals(split[i])){
					Document doc = new Document();
					doc.append("fileId", fileid);
					doc.append("fileName", fileName);
					((ArrayList) files_new.get("file")).add(doc);
				}
			}else{
				List<Document> list = (ArrayList) files_new.get("files");
				for (Document d : list) {
					if(d.get("filesName").equals(split[i])){
						Document doc = new Document();
						doc.append("fileId", fileid);
						doc.append("fileName", fileName);
						((ArrayList) d.get("file")).add(doc);
					}
				}
			}
			i++;
		}
		return files;
	}
	
	/**
	 * 通过用户文件夹文档和文件夹层级路径获取当前层级路径内的文件夹和文件信息
	 * @param userFiles
	 * @param filePath
	 * @return
	 */
	private Document getHierarchyFilesByUserDoc(Document userFiles, String filePath){
		String[] split = filePath.split("/");
		for (int i = 1; i < split.length; i++) {
			List<Document> files = (ArrayList) userFiles.get("files");
			for (Document doc : files) {
				if(doc.get("").equals(split[i])){
					userFiles = doc;
				}
			}
		}
		return userFiles;
	}
	
	/**
	 * 捡取文档内的文件夹及文件信息，并查询详细信息文档
	 * @param hierarchyFilesByUserDoc
	 * @param b true表示为Files，false表示为file
	 * @return
	 */
	private List<Document> appendFileDoc(Document hierarchyFilesByUserDoc,boolean b){
		List<Document> list=  new ArrayList<>();
		List<Document> object = null;
		if(b){
			object = (ArrayList<Document>) hierarchyFilesByUserDoc.get("files");
		}else{
			object = (ArrayList<Document>) hierarchyFilesByUserDoc.get("file");
		}
		for (Document document : object) {
			if(document != null){
				Document doc = new Document();
				if(b){
					doc.append("_id", document.get("filesId"));
				}else{
					doc.append("_id", document.get("fileId"));
				}
				Document queryFiles = filesDao.queryFiles(doc,b);
				list.add(queryFiles);
			}
		}
		return list;
	}
	
}
