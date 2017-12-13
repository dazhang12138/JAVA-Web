package com.da.dabai.dao;

import org.bson.Document;

import com.da.dabai.util.MongoDBUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class UserDao {

	/**
	 * 通过查询信息查询用户是否存在
	 * @param document 查询条件
	 * @return 返回用户信息，如果用户不存在则返回null
	 * @throws Exception
	 */
	public Document queryUser(Document document) throws Exception {
		Document d = null;
		MongoDatabase mdb = MongoDBUtil.instance.getMdb();
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		FindIterable<Document> iterable = docs.find(document);
		for (Document document2 : iterable) {
			d = document2;
		}
		return d;
	}

	/**
	 * 保存用户信息
	 * @param document 用户信息
	 * @throws Exception
	 */
	public void saveUser(Document document) throws Exception {
		MongoDatabase mdb = MongoDBUtil.instance.getMdb();
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		docs.insertOne(document);
	}

	/**
	 * 保存文件夹信息
	 * @param fileDoc 文件夹信息
	 * @throws Exception 
	 */
	public void saveFiles(Document fileDoc) throws Exception {
		MongoDatabase mdb = MongoDBUtil.instance.getMdb();
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		docs.insertOne(fileDoc);
	}

}
