package com.yyjz.icop.dao.impl;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yyjz.icop.dao.FilesDao;
import com.yyjz.icop.util.MongoDBUtil;

public class FilesDaoImpl implements FilesDao {

	MongoDatabase mdb = MongoDBUtil.instance.getMdb();
	
	@Override
	public void saveFiles(Document fileDoc){
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		docs.insertOne(fileDoc);
	}

	@Override
	public void queryUserFilesById(ObjectId userId) {
		MongoCollection<Document> docs = mdb.getCollection("dbFiles");
		
	}

	@Override
	public void saveFile(Document fileDoc) {
		MongoCollection<Document> docs = mdb.getCollection("dbFile");
		docs.insertOne(fileDoc);
	}
}
