package com.yyjz.icop.controller;

import java.util.Arrays;
import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyjz.icop.service.UserService;
import com.yyjz.icop.service.impl.UserServiceImpl;
import com.yyjz.icop.util.CreateFiles;
import com.yyjz.icop.vo.UserVo;

@Controller
@RequestMapping("User")
public class UserController {

	UserService userService = new UserServiceImpl();
	
	@RequestMapping(value = "Login")
	@ResponseBody
	public Document userLogin(@RequestBody UserVo user){
		Document doc = new Document();
		Document document = new Document();
		document.append("userName", user.getUserName());
		document.append("userPwd", user.getUserPwd());
		try {
			doc = userService.queryUser(document);
			doc.append("result", "ok");
		} catch (Exception e) {
			doc = new Document();
			doc.append("result", "error");
			e.printStackTrace();
		}
		return doc;
	}
	
	@RequestMapping(value = "Register")
	@ResponseBody
	public boolean userRegister(@RequestBody UserVo user){
		System.out.println(user.toString());
		boolean reslut = false;
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
		
		Document fileDoc = new Document();
		fileDoc.append("_id", new ObjectId());
		fileDoc.append("userId", userDoc.get("_id"));
		fileDoc.append("foldersName", userDoc.get("name"));
		fileDoc.append("foldersStartTime", new Date());
		fileDoc.append("foldersEndTime", new Date());
		try {
			if(! CreateFiles.createFolder("D:/DB_Files/" + userDoc.get("name"))){
				throw new Exception();
			}
			userService.saveFiles(fileDoc);
			Document d = new Document("filesId",fileDoc.get("_id"));
			d.append("files", Arrays.asList(new String[1]));
			d.append("file", Arrays.asList(new String[1]));
			userDoc.append("files", d);
			userService.saveUser(userDoc);
			reslut = true;
		} catch (Exception e) {
			reslut = false;
			e.printStackTrace();
		}
		return reslut;
	}
	
	@RequestMapping(value = "UserVerify")
	@ResponseBody
	public boolean userVerify(String userName,String name){
		boolean result = false;
		Document document = new Document();
		if(userName != null && !userName.equals("")){
			document.append("userName", userName);
		}else if(name != null && !name.equals("")){
			document.append("name", name);
		}else{
			return false;
		}
		try {
			Document queryUser = userService.queryUser(document);
			if(queryUser != null){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
