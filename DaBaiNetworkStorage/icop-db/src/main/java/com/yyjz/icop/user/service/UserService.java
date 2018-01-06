package com.yyjz.icop.user.service;

import org.bson.Document;

public interface UserService {
	/**
	 * 通过条件查询用户信息
	 * @param document
	 * @return
	 * @throws Exception
	 */
	Document queryUser(Document document) throws Exception;
}
