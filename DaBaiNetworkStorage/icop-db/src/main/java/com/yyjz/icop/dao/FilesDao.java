package com.yyjz.icop.dao;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public interface FilesDao {
	
	/**
	 * 保存数据到Files表
	 * @param fileDoc 数据
	 * @throws Exception 异常
	 */
	void saveFiles(Document fileDoc);
	/**
	 * 保存数据到File表
	 * @param fileDoc
	 */
	void saveFile(Document fileDoc);
	/**
	 * 通过查询条件查询文件信息
	 * @param doc
	 * @param b 如果为true则为查询文件夹dbFiles表，如果为false则查询文件dbFile表
	 * @return
	 */
	Document queryFiles(Document doc, boolean b);
	/**
	 * 删除文件夹
	 * @param document
	 */
	void deleteFiles(Document document);
	/**
	 * 删除文件
	 * @param document
	 */
	void deleteFile(Document document);
	/**
	 * 通过用户编号过滤出用户的所有文件
	 * @param queryData
	 * @return
	 */
	List<Document> queryAllUserFile(Document queryData);
}
