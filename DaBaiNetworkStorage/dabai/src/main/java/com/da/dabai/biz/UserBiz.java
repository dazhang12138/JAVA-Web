package com.da.dabai.biz;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.da.dabai.dao.UserDao;
import com.da.dabai.util.Log;

public class UserBiz {

	
	public Document login(Document document) {
		UserDao uDao = new UserDao();
		Document user = null;
		try {
			user = uDao.queryUser(document);
		} catch (Exception e) {
			Log.logger.debug("登录用户失败:" + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

	public boolean register(Document document) {
		boolean flag = false;
		UserDao uDao = new UserDao();
		try {
			Document fileDoc = new Document();
			fileDoc.append("_id", new ObjectId());
			fileDoc.append("userId", document.get("_id"));
			fileDoc.append("foldersName", document.get("name"));
			fileDoc.append("foldersStartTime", new Date());
			fileDoc.append("foldersEndTime", new Date());
			if(! createFolder("D:/DB_Files/" + document.get("name"))){
				throw new Exception();
			}
			uDao.saveFiles(fileDoc);
			Document d = new Document("filesId",fileDoc.get("_id"));
			d.append("files", Arrays.asList(new String[1]));
			d.append("file", Arrays.asList(new String[1]));
			document.append("files", d);
			uDao.saveUser(document);
			flag = true;
		} catch (Exception e) {
			Log.logger.debug("登录注册失败:" + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
	
	private static boolean createFolder(String folderPath) {
		File file = null;  
        try {  
            file = new File(folderPath);  
            if (!file.exists()) {  
                return file.mkdirs();  
            }  
            else{  
                return false;  
            }  
        } catch (Exception e) {
        	
        } finally {  
            file = null;  
        }  
        return false;  
	}

	public boolean VerifyUserMessage(Document document) {
		boolean flag = false;
		UserDao uDao = new UserDao();
		try {
			Document queryUser = uDao.queryUser(document);
			if(queryUser != null){
				flag = true;
			}
		} catch (Exception e) {
			Log.logger.debug("用户信息校验失败--:" + document.get("userName")+ document.get("name") + ":" + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}
}
