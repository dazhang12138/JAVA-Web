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
		Document doc =  userService.loginUser(user);
		if(doc == null){
			doc = new Document("result","error");
		}
		return doc;
	}
	
	@RequestMapping(value = "Register")
	@ResponseBody
	public boolean userRegister(@RequestBody UserVo user){
		return userService.registerUser(user);
	}
	
	@RequestMapping(value = "UserVerify")
	@ResponseBody
	public boolean userVerify(String userName,String name){
		Document document = new Document();
		if(userName != null && !userName.equals("")){
			document.append("userName", userName);
		}else if(name != null && !name.equals("")){
			document.append("name", name);
		}else{
			return false;
		}
		boolean result = userService.verifyUserByUserNameAndName(document);
		return result;
	}
	
}
