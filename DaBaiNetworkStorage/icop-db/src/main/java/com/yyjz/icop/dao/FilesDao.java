package com.yyjz.icop.dao;

import org.bson.Document;

public interface FilesDao {
	
	/**
	 * 保存数据到Files表
	 * @param fileDoc 数据
	 * @throws Exception 异常
	 */
	void saveFiles(Document fileDoc) throws Exception;
}
