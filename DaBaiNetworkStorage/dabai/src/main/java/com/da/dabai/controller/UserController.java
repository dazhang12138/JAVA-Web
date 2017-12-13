package com.da.dabai.controller;

import java.util.Date;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.da.dabai.biz.UserBiz;
import com.da.dabai.vo.DbUserVo;

@Controller
@RequestMapping("User")
public class UserController {

	@RequestMapping(value = "Login")
	@ResponseBody
	public Document userLogin(@RequestBody DbUserVo user){
		Document doc = null;
		UserBiz uBiz = new UserBiz();
		Document document = new Document();
		if(user == null || user.getUserName() == null || user.getUserName().equals("")
				|| user.getUserPwd() == null || user.getUserPwd().equals("")){
			
		}else{
			document.append("userName", user.getUserName());
			document.append("userPwd", user.getUserPwd());
			doc = uBiz.login(document);
		}
		return doc;
	}
	
	@RequestMapping(value = "Register")
	@ResponseBody
	public boolean userRegister(@RequestBody DbUserVo user){
		System.out.println(user.toString());
		UserBiz uBiz = new UserBiz();
		boolean reslut = false;
		if(user == null || user.getUserName() == null || user.getUserName().equals("")
				|| user.getUserPwd() == null || user.getUserPwd().equals("") || user.getName() == null 
				|| user.getName().equals("") || user.getUserEmail() == null ||user.getUserEmail().equals("")){
			
		}else{
			Document document = new Document();
			document.append("_id", new ObjectId());
			document.append("userName", user.getUserName());
			document.append("userPwd", user.getUserPwd());
			document.append("name", user.getName());
			document.append("userEmail", user.getUserEmail());
			document.append("userStartTime", new Date());
			document.append("fileSize", "0");
			document.append("maxFileSize", "536870912000");
			document.append("uploadLimit", "2147483648");
			document.append("archivedLimit", 1000);
			document.append("bulkUploadLimit", 100);
			document.append("garbageLimit", 10);
			document.append("limits", 1);
			reslut  = uBiz.register(document );
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
		UserBiz uBiz = new UserBiz();
		result = uBiz.VerifyUserMessage(document);
		return result;
	}
	
}

