package com.yyjz.icop.service.impl;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yyjz.icop.service.UserService;
import com.yyjz.icop.util.MongoDBUtil;

public class UserServiceImpl implements UserService {
	
	MongoDatabase mdb = MongoDBUtil.instance.getMdb();

	@Override
	public Document queryUser(Document document) throws Exception {
		Document d = null;
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		FindIterable<Document> iterable = docs.find(document);
		for (Document document2 : iterable) {
			d = document2;
		}
		return d;
	}
	
	@Override
	public void saveUser(Document document) throws Exception {
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		docs.insertOne(document);
	}
	
	@Override
	public void saveFiles(Document fileDoc) throws Exception {
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		docs.insertOne(fileDoc);
	}
	
}
