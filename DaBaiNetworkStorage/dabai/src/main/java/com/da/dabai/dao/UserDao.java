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
	 */
	public Document queryUser(Document document) {
		Document d = null;
		MongoDatabase mdb = MongoDBUtil.instance.getMdb();
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		FindIterable<Document> iterable = docs.find(document);
		for (Document document2 : iterable) {
			d = document2;
		}
		return d;
	}

}
