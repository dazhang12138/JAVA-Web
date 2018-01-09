package com.yyjz.icop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
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
	public Document getUserFiles(FilesVo files) {
		filesDao.queryUserFilesById(files.getUserId());
		return null;
	}

	@Override
	public Document saveFile(MultipartFile file, String path, String userName) {
		try {
			Document queryUser = userDao.queryUser(new Document("name",userName));
			String fileName = file.getOriginalFilename();
			Document fileDoc = new Document();
			fileDoc.append("_id", new ObjectId());
			fileDoc.append("userId", queryUser.get("_id"));
			fileDoc.append("fileName", fileName);
			fileDoc.append("fileType", GetFileType.getType(fileName));
			fileDoc.append("filePath", FileUtils.getPath(path,fileName));
			fileDoc.append("fileSize", file.getSize());
			Date d = new Date();
			fileDoc.append("fileStartTime", d);
			fileDoc.append("fileEndTime", d);
			fileDoc.append("MD5", FileUtils.getFileMD5(path,fileName));
			filesDao.saveFile(fileDoc);
			updateUserFiles(queryUser,fileDoc.get("_id"),path,fileName);
			Document filter = new Document("_id",queryUser.get("_id"));
			Document update = new Document("$set",queryUser);
//			userDao.updateAllFilterUser(document, queryUser);
			userDao.updateAllFilterUser(filter, update);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Document updateUserFiles(Document files,Object fileid, String path, String fileName) {
		String[] split = path.split("/");
		int i = 0;
		Document files_new = files;
		while(i < split.length){
			if(i == 0){
				files_new = (Document) files_new.get("files");
				if(files_new.get("filesName").equals(split[i])){
					Document doc = new Document();
					doc.append("_id", fileid);
					doc.append("fileName", fileName);
					((ArrayList) files_new.get("file")).add(doc);
				}
			}else{
				List<Document> list = (ArrayList) files_new.get("files");
				for (Document d : list) {
					if(d.get("filesName").equals(split[i])){
						Document doc = new Document();
						doc.append("_id", fileid);
						doc.append("fileName", fileName);
						((ArrayList) d.get("file")).add(doc);
					}
				}
			}
			i++;
		}
		return files;
	}
	
}
