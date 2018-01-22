package com.yyjz.icop.dao;

import org.bson.Document;

import com.mongodb.client.result.UpdateResult;

public interface UserDao {

	/**
	 * 查询User表数据
	 * @param document 查询条件
	 * @return 查询结果
	 * @throws Exception 异常
	 */
	Document queryUser(Document document);
	
	/**
	 * 保存数据到User表
	 * @param document 数据
	 * @throws Exception 异常
	 */
	void saveUser(Document document);

	/**
	 * 更新用户信息
	 * @param filter 查询条件
	 * @param update 更新文档
	 * @return 更新结果
	 */
	UpdateResult updateAllFilterUser(Document filter, Document update);
}
