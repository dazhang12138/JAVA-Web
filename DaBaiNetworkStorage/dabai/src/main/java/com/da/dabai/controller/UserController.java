package com.da.dabai.controller;



import org.bson.Document;
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
		UserBiz uBiz = new UserBiz();
		Document doc = uBiz.login(user.getUserName(),user.getUserPwd());
		return doc;
	}
}

