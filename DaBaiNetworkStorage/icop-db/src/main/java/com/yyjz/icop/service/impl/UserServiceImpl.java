package com.yyjz.icop.service.impl;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yyjz.icop.service.UserService;
import com.yyjz.icop.util.MongoDBUtil;

public class UserServiceImpl implements UserService {

	@Override
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
	
}
