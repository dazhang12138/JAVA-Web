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
	
	/**
	 * 保存用户信息
	 * @param document
	 * @throws Exception
	 */
	void saveUser(Document document) throws Exception;
	
	/**
	 * 保存文件夹信息
	 * @param fileDoc
	 * @throws Exception
	 */
	void saveFiles(Document fileDoc) throws Exception;
}
