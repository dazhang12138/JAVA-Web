package com.yyjz.icop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
			updateUserFile(queryUser,fileDoc.get("_id"),path,fileName);
			Document filter = new Document("_id",queryUser.get("_id"));
			int userFileSize = Integer.valueOf((String) queryUser.get("fileSize"));
			userFileSize += fileSize;
			queryUser.append("fileSize", String.valueOf(userFileSize));
			Document update = new Document("$set",queryUser);
			userDao.updateAllFilterUser(filter, update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void saveFolder(ObjectId userId, String filePath, String foldderName) {
		//保存文件夹信息
		Document folderDoc = new Document();
		folderDoc.append("_id", new ObjectId());
		folderDoc.append("userId", userId);
		folderDoc.append("foldersName", foldderName);
		folderDoc.append("foldersStartTime", new Date());
		folderDoc.append("foldersEndTime", new Date());
		filesDao.saveFiles(folderDoc);
		//查询用户信息
		Document queryUser = userDao.queryUser(new Document("_id",userId));
		//更新用户文件夹信息
		updateUserFiles(queryUser,folderDoc.getObjectId("_id"),filePath,foldderName);
		Document filter = new Document("_id",userId);
		Document update = new Document("$set",queryUser);
		userDao.updateAllFilterUser(filter, update);
	}
	
	@Override
	public void deleteFolder(Set<Map<String, Object>> deletefolder,String path, ObjectId objectId) {
		for (Map<String, Object> map : deletefolder) {
			//物理删除
			FileUtils.deleteFiles(FileUtils.getPath(path, (String)map.get("folderName")));
			//删除文件夹库
			ObjectId oid = new ObjectId((String)map.get("_id"));
			filesDao.deleteFiles(new Document("_id",oid));
			//更新用户库
			Document queryUser = userDao.queryUser(new Document("_id",objectId));
			deleteUserFiles(queryUser, path, (String)map.get("folderName"));
			Document filter = new Document("_id",objectId);
			Document update = new Document("$set",queryUser);
			userDao.updateAllFilterUser(filter, update);
		}
	}

	@Override
	public void deleteFile(Set<Map<String, Object>> deletefile,String path, ObjectId objectId) {
		for (Map<String, Object> map : deletefile) {
			//物理删除
			FileUtils.deleteFiles(FileUtils.getPath(path, (String)map.get("fileName")));
			//删除文件夹库
			ObjectId oid = new ObjectId((String)map.get("_id"));
			filesDao.deleteFile(new Document("_id",oid));
			//更新用户库
			Document queryUser = userDao.queryUser(new Document("_id",objectId));
			deleteUserFile(queryUser, path, (String)map.get("fileName"));
			Document filter = new Document("_id",objectId);
			Document update = new Document("$set",queryUser);
			userDao.updateAllFilterUser(filter, update);
		}
	}
	
	/**
	 * 根据位置路径以及文件名称更新用户文档的文件夹存储
	 * @param files
	 * @param path
	 * @param folderName
	 */
	private void deleteUserFiles(Document files, String path, String folderName) {
		path = path + "/" + folderName;
		String[] split = path.split("/");
		int i = 1;
		Document files_new = files;
		files_new = (Document) files_new.get("files");
		while (i < split.length) {
			List<Document> list = (ArrayList) files_new.get("files");
			for (Document d : list) {
				if (d != null && d.get("filesName").equals(split[i])) {
					if (i == split.length - 1) {
						list.remove(d);
					} else {
						files_new = d;
					}
					break;
				}
			}
			i++;
		}
	}
	/**
	 * 根据位置路径以及文件名称更新用户文档的文件存储
	 * @param files
	 * @param path
	 * @param folderName
	 */
	private void deleteUserFile(Document files, String path, String fileName) {
		path = path + "/" + fileName;
		String[] split = path.split("/");
		int i = 1;
		Document files_new = files;
		files_new = (Document) files_new.get("files");
		while (i < split.length) {
			List<Document> list = (ArrayList) files_new.get("file");
			for (Document d : list) {
				if (d != null && d.get("fileName").equals(split[i])) {
					if (i == split.length - 1) {
						list.remove(d);
					} else {
						files_new = d;
					}
					break;
				}
			}
			i++;
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
	private void updateUserFile(Document files,Object fileid, String path, String fileName) {
		String[] split = path.split("/");
		int i = 0;
		Document files_new = files;
		files_new = (Document) files_new.get("files");
		while(i < split.length){
			if(split.length == 1){
				if(files_new.get("filesName").equals(split[i])){
					Document doc = new Document();
					doc.append("fileId", fileid);
					doc.append("fileName", fileName);
					((ArrayList) files_new.get("file")).add(doc);
				}
			}else{
				List<Document> list = (ArrayList) files_new.get("files");
				for (Document d : list) {
					if(d != null && d.get("filesName").equals(split[i])){
						if(i == split.length-1){
							Document doc = new Document();
							doc.append("fileId", fileid);
							doc.append("fileName", fileName);
							((ArrayList) d.get("file")).add(doc);
						}else{
							files_new = d;
						}
						break;
					}
				}
			}
			i++;
		}
	}
	
	/**
	 * 根据位置路径以及文件夹名称更新用户文档的文件夹存储
	 * @param files
	 * @param filesid
	 * @param path
	 * @param folderName
	 * @return
	 */
	private void updateUserFiles(Document files, ObjectId filesid, String path, String folderName){
		String[] split = path.split("/");
		int i = 0;
		Document files_new = files;
		files_new = (Document) files_new.get("files");
		while(i < split.length){
			if(split.length == 1){
				if(files_new.get("filesName").equals(split[i])){
					Document doc = new Document();
					doc.append("filesId", filesid);
					doc.append("filesName", folderName);
					doc.append("files",  Arrays.asList(new String[1]));
					doc.append("file",  Arrays.asList(new String[1]));
					((ArrayList) files_new.get("files")).add(doc);
				}
			}else{
				List<Document> list = (ArrayList) files_new.get("files");
				for (Document d : list) {
					if(d != null && d.get("filesName").equals(split[i])){
						if(i == split.length-1){
							Document doc = new Document();
							doc.append("filesId", filesid);
							doc.append("filesName", folderName);
							doc.append("files",  Arrays.asList(new String[1]));
							doc.append("file",  Arrays.asList(new String[1]));
							((ArrayList) d.get("files")).add(doc);
						}else{
							files_new = d;
						}
						break;
					}
				}
			}
			i++;
		}
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
				if(doc != null && doc.get("filesName").equals(split[i])){
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
