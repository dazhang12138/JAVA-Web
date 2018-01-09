package com.yyjz.icop.controller;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyjz.icop.service.UserService;
import com.yyjz.icop.service.impl.UserServiceImpl;
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
		if(user == null || user.getUserName() == null || user.getUserName().equals("")
				|| user.getUserPwd() == null || user.getUserPwd().equals("")){
			
		}else{
			document.append("userName", user.getUserName());
			document.append("userPwd", user.getUserPwd());
			try {
				doc = userService.queryUser(document);
				doc.append("result", "ok");
			} catch (Exception e) {
				doc.append("result", "error");
				e.printStackTrace();
			}
		}
		return doc;
	}
	
}
