package com.yyjz.icop.dao;

import org.bson.Document;

public interface UserDao {

	/**
	 * 查询User表数据
	 * @param document 查询条件
	 * @return 查询结果
	 * @throws Exception 异常
	 */
	Document queryUser(Document document) throws Exception;
	
	/**
	 * 保存数据到User表
	 * @param document 数据
	 * @throws Exception 异常
	 */
	void saveUser(Document document) throws Exception;
}
