package com.yyjz.icop.dao.impl;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yyjz.icop.dao.FilesDao;
import com.yyjz.icop.util.MongoDBUtil;

public class FilesDaoImpl implements FilesDao {

	MongoDatabase mdb = MongoDBUtil.instance.getMdb();
	
	@Override
	public void saveFiles(Document fileDoc) throws Exception {
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		docs.insertOne(fileDoc);
	}
}
