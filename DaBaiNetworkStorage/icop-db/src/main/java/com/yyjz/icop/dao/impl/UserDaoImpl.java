package com.yyjz.icop.dao.impl;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.yyjz.icop.dao.UserDao;
import com.yyjz.icop.util.MongoDBUtil;

public class UserDaoImpl implements UserDao{

	MongoDatabase mdb = MongoDBUtil.instance.getMdb();

	@Override
	public Document queryUser(Document document){
		Document d = null;
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		FindIterable<Document> iterable = docs.find(document);
		for (Document document2 : iterable) {
			d = document2;
		}
		return d;
	}
	
	@Override
	public void saveUser(Document document){
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		docs.insertOne(document);
	}

	@Override
	public UpdateResult updateAllFilterUser(Document filter, Document update) {
		MongoCollection<Document> docs = mdb.getCollection("dbUser");
		UpdateResult result = docs.updateMany(filter, update);  
		return result;
	}


}
