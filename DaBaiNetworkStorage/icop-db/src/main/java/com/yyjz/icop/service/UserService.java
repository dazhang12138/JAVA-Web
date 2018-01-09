package com.yyjz.icop.service;

import org.bson.Document;

public interface UserService {
	/**
	 * 条件查询用户
	 * @param document
	 * @return
	 * @throws Exception
	 */
	Document queryUser(Document document) throws Exception;
}
