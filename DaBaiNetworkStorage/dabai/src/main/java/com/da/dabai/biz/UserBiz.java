package com.da.dabai.biz;

import org.bson.Document;

import com.da.dabai.dao.UserDao;

public class UserBiz {

	public Document login(String userName, String userPwd) {
		UserDao uDao = new UserDao();
		Document document = new Document();
		document.append("userName", userName);
		document.append("userPwd", userPwd);
		Document user = uDao.queryUser(document);
		return user;
	}
	
}
