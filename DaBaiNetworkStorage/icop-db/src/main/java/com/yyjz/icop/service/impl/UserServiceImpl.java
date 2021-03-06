package com.yyjz.icop.service.impl;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.yyjz.icop.dao.FilesDao;
import com.yyjz.icop.dao.UserDao;
import com.yyjz.icop.dao.impl.FilesDaoImpl;
import com.yyjz.icop.dao.impl.UserDaoImpl;
import com.yyjz.icop.service.UserService;
import com.yyjz.icop.util.FileUtils;
import com.yyjz.icop.vo.UserUpdatePwdVo;
import com.yyjz.icop.vo.UserUpdateVo;
import com.yyjz.icop.vo.UserVo;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDaoImpl();
	FilesDao filesDao = new FilesDaoImpl();

	@Override
	public Document loginUser(UserVo user) {
		Document doc = null;
		Document document = new Document();
		document.append("userName", user.getUserName());
		document.append("userPwd", user.getUserPwd());
		doc = userDao.queryUser(document);
		return doc;
	}

	@Override
	public boolean registerUser(UserVo user) {
		boolean flag = false;
		//编写用户表信息
		Document userDoc = new Document();
		userDoc.append("_id", new ObjectId());
		userDoc.append("userName", user.getUserName());
		userDoc.append("userPwd", user.getUserPwd());
		userDoc.append("name", user.getName());
		userDoc.append("userEmail", user.getUserEmail());
		userDoc.append("userStartTime", new Date());
		userDoc.append("fileSize", "0");
		userDoc.append("maxFileSize", "536870912000");
		userDoc.append("uploadLimit", "2147483648");
		userDoc.append("archivedLimit", 1000);
		userDoc.append("bulkUploadLimit", 100);
		userDoc.append("garbageLimit", 10);
		userDoc.append("limits", 1);
		//编写文件表信息
		Document fileDoc = new Document();
		fileDoc.append("_id", new ObjectId());
		fileDoc.append("userId", userDoc.get("_id"));
		fileDoc.append("foldersName", userDoc.get("name"));
		fileDoc.append("foldersStartTime", new Date());
		fileDoc.append("foldersEndTime", new Date());
		try {
			if(! FileUtils.createFolder("D:/DB_Files/" + userDoc.get("name"))){
				throw new Exception();
			}
			filesDao.saveFiles(fileDoc);
			//将文件信息保存到用户信息里
			Document d = new Document("filesId",fileDoc.get("_id"));
			d.append("filesName", user.getName());
			d.append("files", Arrays.asList(new String[1]));
			d.append("file", Arrays.asList(new String[1]));
			userDoc.append("files", d);
			userDao.saveUser(userDoc);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean verifyUserByUserNameAndName(Document document) {
		boolean flag = false;
		Document queryUser = userDao.queryUser(document);
		if(queryUser != null){
			flag = true;
		}
		return flag;
	}

	@Override
	public Document updateUser(UserUpdateVo user) {
		Document document = new Document("_id",user.getUserId());
		Document queryUser = userDao.queryUser(document);
		queryUser.append("birthday", user.getBirthday());
		queryUser.append("sex", user.getSex());
		queryUser.append("idNumber", user.getIdNumber());
		queryUser.append("phoneNumber", user.getPhoneNumber());
		queryUser.append("age", user.getAge());
		Document update = new Document("$set", queryUser);
		userDao.updateAllFilterUser(document, update);
		return queryUser;
	}

	@Override
	public String alterUserPwd(UserUpdatePwdVo user) {
		String result = "error";
		Document qUserDoc = new Document("_id", user.getUserId());
		Document queryUser = userDao.queryUser(qUserDoc);
		if(queryUser.getString("userPwd").equals(user.getUserpwd())){
			queryUser.append("userPwd", user.getPwd1());
			Document update = new Document("$set",queryUser);
			userDao.updateAllFilterUser(qUserDoc, update );
			result = "OK";
		}else{
			result = "loginError";
		}
		return result;
	}
}
